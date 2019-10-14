package springboot2.SpringBoot2.Service;

public interface EmailService {
    public void sendSimpleMail(String to, String subject, String text);
}
