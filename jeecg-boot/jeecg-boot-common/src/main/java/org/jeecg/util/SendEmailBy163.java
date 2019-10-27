package org.jeecg.util;//package org.jeecg.util;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Date;
//import java.util.Properties;
//
///**
// *使用163邮箱发送邮件demo
// */
//public class SendEmailBy163 {
//
//	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
//	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
//	// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
//	public static String myEmailAccount = "a1154534746@163.com";
//	public static String myEmailPassword = "sqm8793134267657";
//
//	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
//	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
//	public static String myEmailSMTPHost = "smtp.163.com";
//
//	// 收件人邮箱（替换为自己知道的有效邮箱）
//	// public static String receiveMailAccount = "1154534746@qq.com";
//
//	public static void main(String[] args) throws Exception {
//		sendEmail("a1154534746@163.com", "我是发送方的昵称", "我是收件方的昵称", "我是消息标题", "我是消息内容");
//	}
//
//	public static void sendEmail(String toEmail, String fromNickname, String toNickname, String messageTitleText,
//			String messageContentText) throws Exception {
//		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
//		Properties props = new Properties(); // 参数配置
//		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
//		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP
//																// 服务器地址
//		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//
//		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
//		Session session = Session.getInstance(props);
//		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
//
//		// 3. 创建一封邮件
//		MimeMessage message = createMimeMessage(session, myEmailAccount, toEmail, fromNickname, toNickname,
//				messageTitleText, messageContentText);
//
//		// 4. 根据 Session 获取邮件传输对象
//		Transport transport = session.getTransport();
//
//		//
//		// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
//		transport.connect(myEmailAccount, myEmailPassword);
//
//		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
//		// 抄送人, 密送人
//		transport.sendMessage(message, message.getAllRecipients());
//
//		// 7. 关闭连接
//		transport.close();
//	}
//
//
//	/**
//	 * @param session
//	 * @param sendMail
//	 * @param toEmail
//	 * @param fromNickname
//	 * @param toNickname
//	 * @param messageTitleText
//	 * @param messageContentText
//	 * @return
//	 * @throws Exception
//	 */
//	public static MimeMessage createMimeMessage(Session session, String sendMail, String toEmail, String fromNickname,
//			String toNickname, String messageTitleText, String messageContentText) throws Exception {
//		// 1. 创建一封邮件
//		MimeMessage message = new MimeMessage(session);
//
//		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//		message.setFrom(new InternetAddress(sendMail, fromNickname, "UTF-8"));
//
//		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
//		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail, toNickname, "UTF-8"));
//
//		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
//		message.setSubject(messageTitleText, "UTF-8");
//
//		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
//		message.setContent(messageContentText, "text/html;charset=UTF-8");
//
//		// 6. 设置发件时间
//		message.setSentDate(new Date());
//
//		// 7. 保存设置
//		message.saveChanges();
//
//		return message;
//	}
//
//}
