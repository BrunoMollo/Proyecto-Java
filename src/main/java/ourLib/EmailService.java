package ourLib;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class EmailService {

	private Session session;
	
    private String from = "correofarmacia2022@gmail.com";
    private String passwordFrom = System.getenv("pswEmailFarmacia");
	
	
	public EmailService() {
	      Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com.");
		    props.put("mail.smtp.socketFactory.port", "587");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.starttls.required", "true");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.port", "587");
			
			this.session = Session.getDefaultInstance(props);
	}
	
	
	
	public void send(String to, String subject, String text, Csv csv) throws AppException {
		 try {
			 MimeMessage message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(from));
			 message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			 
			 message.setSubject(subject);
			 
			 
			 MimeMultipart multipart = new MimeMultipart("related");
		
			 BodyPart messageBodyPart = new MimeBodyPart();
			 messageBodyPart.setText(text);
			 multipart.addBodyPart(messageBodyPart);
			 
		
		
			 BodyPart fileBodyPart= new MimeBodyPart();
			 DataSource source = new ByteArrayDataSource(csv.getRawData(), "application/csv");
			 fileBodyPart.setDataHandler(new DataHandler(source));
			 fileBodyPart.setFileName(csv.getName());
			 multipart.addBodyPart(fileBodyPart);
			 
			 
			 message.setContent(multipart);
			 
			 Transport tr = session.getTransport("smtp");
			 tr.connect(from, passwordFrom);
			 tr.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			 tr.close();
			 
		} catch (MessagingException|IOException e) {
			throw new AppException("NO se pudo enviar el mail", 500);
		} 
	}
	
}
