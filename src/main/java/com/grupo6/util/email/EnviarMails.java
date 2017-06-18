package com.grupo6.util.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

@Component
public class EnviarMails {

	public void EnviarCooreoConQR(String coorreoDestino, String asunto, String mensaje, String pathQR) {

		final String username = "tecnoinfgrupo6@gmail.com";
		final String password = "ADMINadmin1234";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tecnoinfgrupo6@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(coorreoDestino));
			message.setSubject(asunto);
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(mensaje);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String filename = pathQR;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Entrada.png");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
