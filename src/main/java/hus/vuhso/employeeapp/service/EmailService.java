package hus.vuhso.employeeapp.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
