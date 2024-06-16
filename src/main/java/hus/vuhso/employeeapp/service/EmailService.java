package hus.vuhso.employeeapp.service;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import java.time.YearMonth;

public interface EmailService {
    void sendEmail(String to, String subject, String text);

    ResponseEntity<String> sendAttendanceEmail(Long id, @DateTimeFormat(pattern = "yyyy-MM") YearMonth month);
}
