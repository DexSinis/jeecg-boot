package org.jeecg.util;//package org.jeecg.util;
//
//import com.sun.mail.util.MailSSLSocketFactory;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.security.GeneralSecurityException;
//import java.util.Properties;
//
///**
// *使用qq邮箱发送邮件demo
// */
//public class SendEmailByQQ {
//
//	public static void sendEmail(String toEmail, String fromNickname, String toNickname, String messageTitleText,
//			String messageContentText) throws GeneralSecurityException, UnsupportedEncodingException {
//		// 收件人电子邮箱
//		String to = toEmail;
//
//		// 发件人电子邮箱
//		String from = "1154534746@qq.com";
//
//		// 指定发送邮件的主机为 smtp.qq.com
//		String host = "smtp.qq.com"; // QQ 邮件服务器
//
//		// 获取系统属性
//		Properties properties = System.getProperties();
//
//		// 设置邮件服务器
//		properties.setProperty("mail.smtp.host", host);
//
//		properties.put("mail.smtp.auth", "true");
//		MailSSLSocketFactory sf = new MailSSLSocketFactory();
//		sf.setTrustAllHosts(true);
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.ssl.socketFactory", sf);
//		// 获取默认session对象
//		Session session = Session.getDefaultInstance(properties, new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("1154534746@qq.com", "rayguddabynafjhj11111111"); // 发件人邮件用户名、密码
//			}
//		});
//
//		try {
//			// 创建默认的 MimeMessage 对象
//			MimeMessage message = new MimeMessage(session);
//
//
//			// Set From: 头部头字段
//			message.setFrom(new InternetAddress(from, fromNickname, "UTF-8"));
//
//			// Set To: 头部头字段
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to, toNickname, "UTF-8"));
//
//			// Set Subject: 头部头字段
//			message.setSubject(messageTitleText);
//
//			// 设置消息体
//			message.setText(messageContentText);
//
//			// 发送消息
//			Transport.send(message);
//			System.out.println("发送成功");
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {
//		sendEmail("a1154534746@163.com", "我是发送方的昵称", "我是收件方的昵称", "我是消息标题", "我是消息内容");
//	}
//}
