package com.email.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 日志记录：
 * 版本   日期       修改者    更新内容
 * 1.0  2016-3-5    程文健   创建项目
 * 1.1  2016-3-7    程文健   更新：添加判断文件所在目录是否存在，若不存在则自动创建
 * 1.2  2016-4-1    程文健   更新：文件写入方法 writeContentToFile 移到 ioManager 类中
 * 1.3  2016-8-30   周晓明   更新：增加手机文件管理&PC端文件管理
 */

/**
 * 用于文件管理，向文档进行增删改等操作
 * 
 * @author 程文健
 * @version 1.2 2016-4-1
 */

public class FileManager {
	// 判断文件是否存在
	protected static boolean isFileExists(File file) {
		if (file.getPath().endsWith(File.separator)) {
			System.out.println("创建单个文件" + file.getName() + "失败，目标文件不能为目录！");
			return false;
		}
		if (createDir(file.getParentFile().getPath())) { // 判断目录是否存在，不存在则自动创建
			if (!file.exists()) { // 如果文件不存在则先创建该文件
				try {
					file.createNewFile();
					System.out.println("创建文件" + file.getPath() + "成功！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("创建文件" + file.getName() + "失败！失败信息：" + e.getMessage());
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	// 创建目录
	protected static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
				System.out.println("创建目录" + dir.getPath() + "成功！");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("创建目录" + dir.getPath() + "失败！失败信息：" + e.getMessage());
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断手机中目标文件是否存在
	 * @param path  目标文件所在的路径
	 * @param filename 目标文件的文件名（ 不用后缀，例如：test2M）
	 *@return	存在，则返回true；否则，返回false；
	 */
	public static boolean adbFindFile(String path,String filename) {
		ProcessBuilder pb1 = new ProcessBuilder("adb", "shell", "ls", path);
		StringBuffer result = new StringBuffer();//StringBuffer: 可以一直加载字符串
		boolean isexists = false;
		try {
			Process process =pb1.start();
			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine()) {//判断是否有下一行
				result.append(scanner.nextLine());//把输出的全部加载到StringBuffer中
			}
			scanner.close();
			if((!result.toString().contains("No such file or directory")) && result.toString().contains(filename)){
				isexists=true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isexists;
	}
	/**
	 * 判断手机中目标文件是否存在
	 * @param path  目标文件所在的路径
	 * @return	存在，则返回true；否则，返回false；
	 */
	public static boolean adbFindFile(String path) {
		ProcessBuilder pb1 = new ProcessBuilder("adb", "shell", "ls", path);
		StringBuffer result = new StringBuffer();//StringBuffer: 可以一直加载字符串
		boolean isexists = false;;
		try {
			Process process =pb1.start();
			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine()) {//判断是否有下一行
				result.append(scanner.nextLine());//把输出的全部加载到StringBuffer中
			}
			scanner.close();
			if((!result.toString().contains("No such file or directory")) ){
				isexists=true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isexists;
	}

	/**
	 * 获取手机中文件大小
	 * @param path  目标文件路径
	 * @return  返回目标文件大小（单位：KB）
	 */
	public static double adbGetFileSize(String path) {
		ProcessBuilder pb1 = new ProcessBuilder("adb", "shell", "du", "-k", path);
		StringBuffer result = new StringBuffer();
		String strResult = null;
		Double size = 0.0;
		try {
			Process process =pb1.start();
			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine()) {
				result.append(scanner.nextLine());
			}
			scanner.close();
			strResult = result.toString().replace(path, "").trim();
			size = Double.parseDouble(strResult);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * 删除手机中的目标文件
	 * @param path 目标文件所在的路径
	 * @return  删除成功，返回true；否则返回false；
	 */
	public static boolean adbDeleteFile(String path) {
		ProcessBuilder pb1 = new ProcessBuilder("adb","shell","rm",path);
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
		try {
			Process process = pb1.start();
			Scanner scanner = new Scanner(process.getInputStream());
			while (scanner.hasNextLine()) {
				result.append(scanner.nextLine());
			}
			scanner.close();
			if(!result.toString().contains("No such file or directory")){
				isexists=true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isexists;
	}

	/**
	 * 等待手机中目标文件出现
	 * @param path	目标文件路径
	 * @param timeoutMillis  超时时间
	 * @return	目标文件出现，返回true；超时未出现，返回false；
	 */
	public static boolean adbwaitforfile(String path,String filename, long timeoutMillis){
		boolean isTimeOut = false;
		long timeout = System.currentTimeMillis() + timeoutMillis;//超时时间
		while (System.currentTimeMillis() < timeout) {
			try {
				if(adbFindFile(path,filename)) {
					return isTimeOut = true;
				}
			} catch (Exception e) {
				System.out.println("wait for \" " + path + " \" error!");
				return false;
			}
		}
		if(!isTimeOut){
			System.out.println("wait for \" " + path + " \" error!");
		}
		return isTimeOut;
	}
	/**
	 * 等待手机中目标文件出现
	 * @param path	目标文件路径
	 * @param timeoutMillis  超时时间
	 * @param size	 用于判断目标文件是否和期望大小一致
	 * @return	目标文件出现，返回true；超时未出现，返回false；
	 */
	public static boolean adbwaitforfile(String path, long timeoutMillis,double size){
		boolean isTimeOut = false;
		long timeout = System.currentTimeMillis() + timeoutMillis;
		while (System.currentTimeMillis() < timeout) {
			try {
				if(adbFindFile(path)&&adbGetFileSize(path)==size) {
					return isTimeOut = true;
				}
			} catch (Exception e) {
				System.out.println("wait for \" " + path + " \" error!");
				return false;
			}
		}
		if(!isTimeOut){
			System.out.println("wait for \" " + path + " \" error!");
		}
		return isTimeOut;
	}

	/**
	 *判断电脑本地是否存在某文件，存在则删除
	 * @param path 文件路径
	 * @return
	 */
	public static boolean isexists(String path){
		boolean issuccess = true;
		try {
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		} catch (Exception e) {
			return false;
		}
		return issuccess;
	}


	/**
	 * 判断电脑本地文件是否在规定时间内出现
	 * @param path 文件路径	
	 * @param timeout 超时时间
	 * @return
	 */
	public static boolean waitforfile(String path, long timeout){
		boolean isTimeOut = false;
		long time = timeout*1000;
		long timeout1 = System.currentTimeMillis() + time;
		while (System.currentTimeMillis() < timeout1) {
			try {
				File file = new File(path);
				if(file.exists()) {
					return isTimeOut = true;
				}
			} catch (Exception e) {
				System.out.println("wait for \" " + path + " \" error!");
				return false;
			}
		}
		return isTimeOut;
	}
}
