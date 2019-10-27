package org.jeecg.util;

/**
 *系统属性工具类
 */
public class SystemUtils {
	/*
	 * os.name 操作系统的名称 os.arch 操作系统的架构 os.version 操作系统的版本 java.home Java 安装目录
	 * java.version Java 运行时环境版本
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

	public static String getJavaHome() {
		return System.getProperty("java.home");
	}

	public static String getjavaVersion() {
		return System.getProperty("java.version");
	}

//	public static void main(String[] args) {
//		System.out.println("系统名称: " + getOsName());
//		System.out.println("系统架构: " + getOsArch());
//		System.out.println("系统版本: " + getOsVersion());
//		System.out.println("javaHome: " + getJavaHome());
//		System.out.println("javaVersion: " + getjavaVersion());
//	}
}
