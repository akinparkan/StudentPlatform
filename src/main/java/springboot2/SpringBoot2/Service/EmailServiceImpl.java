package springboot2.SpringBoot2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Email.EmailMan;

import javax.validation.constraints.Email;

@Service
public class EmailServiceImpl implements EmailService  {
    public EmailMan emailSender;
    @Autowired
    public EmailServiceImpl(EmailMan emailSender)
    {
        this.emailSender = emailSender;
    }
    public EmailServiceImpl()
    {
        emailSender = new EmailMan();
    }
    @Override
    public void sendSimpleMail(String to, String subject, String text)
    {
        System.out.println("here: " + to);
        JavaMailSender em = emailSender.getJavaMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);
        em.send(message);
    }

}
