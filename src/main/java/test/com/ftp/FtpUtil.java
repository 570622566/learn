package test.com.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.google.common.collect.Ordering;

public class FtpUtil {

	private static FTPClient ftp;

	/**
	 * 获取ftp连接
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static boolean connectFtp(Ftp f) throws Exception {
		ftp = new FTPClient();
		boolean flag = false;
		int reply;
		if (f.getPort() == null) {
			ftp.connect(f.getIpAddr(), 21);
		} else {
			ftp.connect(f.getIpAddr(), f.getPort());
		}
		ftp.login(f.getUserName(), f.getPwd());
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return flag;
		}
		ftp.changeWorkingDirectory(f.getPath());
		flag = true;
		return flag;
	}

	/**
	 * 关闭ftp连接
	 */
	public void closeFtp() {
		if (ftp != null && ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param path
	 * @return function:读取指定目录下的文件名
	 * @throws IOException
	 */
	public List<String> getFileList(String path) throws ParseException {
		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftp.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				// System.out.println("文件夹下面的文件=====" + file.getName());
				fileLists.add(file.getName());
			} else if (file.isDirectory()) {
				// System.out.println("文件夹名称为=====" + file.getName());
				fileLists.add(file.getName());
				// 下面的方法是递归迭代方式进行遍历
				/*
				 * List<String> childLists = getFileList(path + file.getName() +
				 * "/"); for (String childFileName : childLists) {
				 * fileLists.add(childFileName); String fileType =
				 * childFileName.substring(childFileName.lastIndexOf(".") + 1,
				 * childFileName.length()); System.out.println("文件类型为：" +
				 * fileType); FtpUtil ftp = new FtpUtil(); if
				 * (fileType.equals("txt")) { System.out.println("文件名为：" +
				 * childFileName); String content = ""; content =
				 * ftp.readFile(path + file.getName() + "/" + childFileName);
				 * System.out.println("文件内容为：" + content); } }
				 */
			}
		}
		return fileLists;
	}

	// 通过路径获得路径下所有文件 输出文件名
	public static void List(String pathName) throws IOException {
		if (pathName.startsWith("/") && pathName.endsWith("/")) {
			String directory = pathName;
			ftp.changeWorkingDirectory(directory);
			FTPFile[] files = ftp.listFiles();
			for (int i = 0; i < files.length; i++) {
				FTPFile ftpFile = 	files[i];
				
				System.out.println("parent:"+directory);
				System.out.println("fileName:" + ftpFile.getName());
				System.out.println("lastModify:"+ftpFile.getTimestamp().getTimeInMillis());
				if (files[i].isFile()) {
				} else if (files[i].isDirectory()) {
					List(directory + files[i].getName() + "/");
				}
			}
		}
	}

	/**
	 * @param fileName
	 * @return function:从服务器上读取指定的文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public String readFile(String fileName) throws ParseException {
		InputStream ins = null;
		StringBuilder builder = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftp.retrieveFileStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "GBK"));
			String line;
			builder = new StringBuilder(150);
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				builder.append(line);
			}
			reader.close();
			if (ins != null) {
				ins.close();
			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftp.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public static void main(String[] args) throws Exception {

		/*
		 * Cache<String,String> cache =
		 * CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(450,
		 * TimeUnit.MINUTES).build();
		 * 
		 * String resultVal = cache.get("version", new Callable<String>() {
		 * 
		 * @Override public String call() throws Exception {
		 * 
		 * return generateLastVersion(); } });
		 * 
		 * System.out.println("version value : " + resultVal);
		 */

		Ftp f = new Ftp();
		f.setIpAddr("192.168.3.199");
		f.setUserName("ftpadmin");
		f.setPort(21);
		f.setPwd("wangzhijie_er3456t8");

		boolean connectFtp = FtpUtil.connectFtp(f);
		if(connectFtp){
			FtpUtil.List("/update/release/");
		}
		FtpUtil.connectFtp(f);
	}

	/**
	 * @throws Exception
	 * @throws ParseException
	 */
	private static String generateLastVersion() throws Exception, ParseException {
		FtpUtil f1 = new FtpUtil();

		Ftp f = new Ftp();
		f.setIpAddr("192.168.3.199");
		f.setUserName("ftpadmin");
		f.setPort(21);
		f.setPwd("wangzhijie_er3456t8");

		String verison = org.apache.commons.lang3.StringUtils.EMPTY;
		boolean connectFtp = FtpUtil.connectFtp(f);
		if (connectFtp) {
			List<String> fileList = f1.getFileList("update/release/");
			f1.closeFtp();
			List<Double> numbers = new ArrayList<Double>();
			for (String s : fileList) {
				// System.out.println( Double.valueOf(s.replace("v", "")));
				numbers.add(Double.valueOf(s.replace("v", "").replace("V", "")));
			}

			@SuppressWarnings("rawtypes")
			Ordering ordering = Ordering.natural();
			Collections.sort(numbers, ordering);
			// System.out.println("Maximum: " + ordering.max(numbers));
			verison = ordering.max(numbers) + "";
		}

		return verison;
	}

}
