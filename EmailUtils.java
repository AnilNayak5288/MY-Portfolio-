package in.anil.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body,String to) {
		
		boolean isSent = false;
		
		try {
			
			MimeMessage mimeMgs = mailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimeMgs);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailSender.send(mimeMgs);
			
			isSent = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
}
