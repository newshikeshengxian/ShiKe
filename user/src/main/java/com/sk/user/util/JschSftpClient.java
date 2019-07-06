package com.sk.user.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.springframework.web.multipart.MultipartFile;

/*
		<dependency>
			<groupId>com.jcraft</groupId>
			<artifactId>jsch</artifactId>
			<version>0.1.54</version>
		</dependency>
*/

/**
 * sftp工具
 * @author yaoleiming
 */
public class JschSftpClient {
	private Logger logger = LoggerFactory.getLogger(JschSftpClient.class);
	private static final int BUFF_SIZE = 1024 * 10;
	
	private boolean keyConnect;//是否用密钥连接sftp, true:密钥连接, false:密码连接， 默认:false
    private String userName;
    private String host;
    private int port;
    private String password;
    private String priKeyFile;//密钥路径

    private Session session = null;
    private ChannelSftp sftp = null;
    
    public JschSftpClient(String host, int port, String userName, String password) {
        this.host = host;
        this.password = password;
        this.userName = userName;
        this.port = port;
    }
    
    /**
     * @param pwdOrFilePath: 密码或者密钥路径
     */
    public JschSftpClient(boolean keyConnect, String host, int port, String userName, String pwdOrFilePath) {
        this.keyConnect = keyConnect;
        this.host = host;
        this.userName = userName;
        this.port = port;
        if(keyConnect) {
        	priKeyFile = pwdOrFilePath;
        } else {
        	this.password = pwdOrFilePath;
        }
    }
    
    /**
     * sftp连接
     */
    public void connect() throws JSchException {
    	JSch jsch = new JSch();
		session = getSession(jsch);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect();
        if (logger.isDebugEnabled()) {
            logger.debug("Session connected.");
        }
        Channel channel = session.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;
    }
    
    /**
     * 关闭sftp连接
     */
    public void disconnect() {
    	if(session != null){
    		session.disconnect();
    		session = null;
        }
        if(sftp != null){
            sftp.disconnect();
            sftp = null;
        }
    }
    
    /** 得到远程文件大小
     * @see   返回文件大小 remote
     * @return 返回文件大小，如返回-2 文件不存在，-1文件读取异常
     */
    public long getFileSize(String remotePath) {
    	long filesize = 0;//文件大于等于0则存在
    	try {
    		SftpATTRS sftpATTRS = sftp.lstat(remotePath);
            filesize = sftpATTRS.getSize();
    	} catch (Exception e) {
        	logger.error("获取文件大小异常, remotePath:{}", remotePath);
            filesize = -1;//获取文件大小异常
            if (e.getMessage().toLowerCase().equals("no such file")) {
                filesize = -2;//文件不存在
            }
        }
    	return filesize;
    }
    
    /**
     * 判断远程文件是否存在
     */
    public boolean isFileExist(String remotePath) {
        // 文件大于等于0则存在文件
        if (getFileSize(remotePath) >= 0) {
            return true;
        } else {
        	return false;
        }
    }
    
    /**
	 * 创建目录, 如果上层目录不存在，递归创建上层目录
	 */
	public void iterateMkDir(String directory) throws SftpException {
		logger.info("iterateMkDir directory:{}", directory);
		String[] paths = directory.split("/");
		String need = "/";
		for (int i = 0; i < paths.length; i++) {
			if ("".equals(paths[i])) {//
				continue;
			}
			need += paths[i];
			try {
				if(!isDirExist(need)) {
					sftp.mkdir(need);
				}
			} catch (SftpException e) {
				logger.info("iterateMkDir fail now start mkdir[{}].", need);
				sftp.mkdir(need);
			}
			need += "/";
		}
	}
	
	/**
	 * 文件上传<br/>
	 * remoteDirectory: 远程目录<br/>
	 * localPath: 本地路径(本地目录+文件名)<br/>
	 * ftpFileName: sftp文件名
	 */
	public boolean uploadFile(String remoteDirectory, MultipartFile file, String ftpFileName) throws SftpException, IOException {
        BufferedInputStream bis = null;
        try {
        	iterateMkDir(remoteDirectory);
            sftp.cd(remoteDirectory);
//            File file = new File(localPath);
//            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(file.getInputStream(), BUFF_SIZE);
            sftp.put(bis, ftpFileName);
            String remotPath;
            if(remoteDirectory.endsWith("/")) {
            	remotPath = remoteDirectory + ftpFileName;
            } else {
            	remotPath = remoteDirectory + "/" + ftpFileName;
            }
            return isFileExist(remotPath);
        } finally{
        	if(bis != null) {
        		bis.close();
        	}
        }
    }
	
	/**
	 * 文件下载<br/>
	 * remoteDirectory: 远程目录<br/>
	 * downloadFile: 要下载文件的文件名<br/>
	 * saveToPath: 要保存的本地文件路径<br/>
	 */
    public boolean downloadFile(String remoteDirectory, String downloadFile, String saveToPath) throws SftpException, IOException {
		OutputStream outputStream = null;
		try {
			String path = saveToPath.substring(0, saveToPath.lastIndexOf("/"));
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if(!isFileExist(remoteDirectory + "/" +  downloadFile)){
				return false;
			}
			File file = new File(saveToPath);
			if(!file.exists()) {
				file.createNewFile();
			}
			
			sftp.cd(remoteDirectory);
			outputStream = new BufferedOutputStream(new FileOutputStream(file));
			sftp.get(downloadFile, outputStream);
			if (logger.isDebugEnabled()) {
				logger.debug("download successed:" + saveToPath);
			}
		} finally {
			if(outputStream != null){
				outputStream.close();
			}
		}
		return true;
    }
    
    /**
     * 判断目录是否存在
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (SftpException e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getAllFileName(String directory) throws SftpException {
    	List<String> list = new ArrayList<>();
    	logger.info("getAllFileName directory:{}", directory);
    	Vector<LsEntry> v = sftp.ls(directory);
        for (LsEntry obj : v) {
        	if (null == obj.getFilename() || ".".equals(obj.getFilename()) || "..".equals(obj.getFilename())) {
        		continue;
        	}
        	if (obj.getAttrs().isDir()) {
				continue;
			} else {
				list.add(obj.getFilename());
			}
        }
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public List<String> getAllDirName(String directory) throws SftpException {
    	List<String> list = new ArrayList<>();
    	logger.info("getAllFileName directory:{}", directory);
    	Vector<LsEntry> v = sftp.ls(directory);
        for (LsEntry obj : v) {
        	if (null == obj.getFilename() || ".".equals(obj.getFilename()) || "..".equals(obj.getFilename())) {
        		continue;
        	}
        	if (obj.getAttrs().isDir()) {
        		list.add(obj.getFilename());
			} else {
				continue;
			}
        }
    	return list;
    }
	
	private Session getSession(JSch jsch) throws JSchException {
    	if(keyConnect) {
    		jsch.addIdentity(priKeyFile);
    		Session sshSession = jsch.getSession(userName, host, port);
    		return sshSession;
    	} else {
    		Session sshSession = jsch.getSession(userName, host, port);
            sshSession.setPassword(password);
            return sshSession;
    	}
    }
	
//	public static void main(String[] args) throws Exception {
////		testPsw(MultipartFile file);
////		testPriKey();
//	}
	
    public static String testPsw(MultipartFile file) throws Exception {
    	JschSftpClient client = null;
		try {
			client = new JschSftpClient(false, "139.196.142.228", 22, "root", "Xu719608");
	    	client.connect();
//	    	String localPath = "D:\\test\\testUpload.zip";
	    	String path = "/usr/local/nginx/html/images";
	    	long s = System.currentTimeMillis();
	    	client.iterateMkDir(path);
//	    	client.isDirExist(path);
			String jpg = new Date().getTime()+".jpg";
	    	client.uploadFile(path,file,jpg);
//	    	for(int i = 1; i <= 10; i++) {
//	    		client.uploadFile(path, localPath, "testUpload_" + i + ".zip");
//	    	}
	    	long e = System.currentTimeMillis();
	    	System.out.println("有缓存用时:" + (e - s) + "ms");

	    	return "http://139.196.142.228/images/"+jpg;
		} catch (Exception e){
			e.printStackTrace();
			return "1";
		}
		finally {
			if(client != null) {
				client.disconnect();
			}
		}
    }
    
    private static void testPriKey() throws Exception {
    	JschSftpClient client = null;
		try {
			client = new JschSftpClient(true, "106.37.75.41", 22, "360", "D:\\笔记\\项目\\基金\\信托\\文档\\360接入\\sftp接口rsa密钥\\id_rsa_360.pem");
	    	client.connect();
	    	String path = "/upload/information/FBS20181210114145LAO100057/";
	    	System.out.println(client.getAllDirName(path));
		} finally {
			if(client != null) {
				client.disconnect();
			}
		}
    }
}
