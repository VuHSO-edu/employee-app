package hus.vuhso.employeeapp.service.serviceImpl;

import hus.vuhso.employeeapp.dto.response.AttendanceDto;
import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.service.AttendanceService;
import hus.vuhso.employeeapp.service.EmailService;
import hus.vuhso.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

//AUTHOR:VuHSO
//                           _
//                        _ooOoo_
//                       o8888888o
//                       88" . "88
//                       (| -_- |)
//                       O\  =  /O
//                    ____/`---'\____
//                  .'  \\|     |//  `.
//                 /  \\|||  :  |||//  \
//                /  _||||| -:- |||||_  \
//                |   | \\\  -  /'| |   |
//                | \_|  `\`---'//  |_/ |
//                \  .-\__ `-. -'__/-.  /
//              ___`. .'  /--.--\  `. .'___
//           ."" '<  `.___\_<|>_/___.' _> \"".
//          | | :  `- \`. ;`. _/; .'/ /  .' ; |
//          \  \ `-.   \_\_`. _.'_/_/  -' _.' /
//===========`-.`___`-.__\ \___  /__.-'_.'_.-'================
//                        `=--=-'
//=========== Phật phù hộ không bao giờ BUG ===================
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    private EmployeeService employeeService;
    private AttendanceService attendanceService;
    private EmailService emailService;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    private String buildAttendanceEmailContent(EmployeeDto employee, List<AttendanceDto> attendances, YearMonth month) {
        StringBuilder sb = new StringBuilder();
        sb.append("Attendance Report for ").append(employee.getName()).append(" for the month of ").append(month).append("\n\n");

        for (AttendanceDto attendance : attendances) {
            sb.append("Check-in: ").append(attendance.getCheckinTime()).append(", Check-out: ").append(attendance.getCheckoutTime()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public ResponseEntity<String> sendAttendanceEmail( Long id, @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        Optional<EmployeeDto> employeeOptional = Optional.ofNullable(employeeService.findById(id));

        if (employeeOptional.isPresent()) {
            EmployeeDto employee = employeeOptional.get();
            List<AttendanceDto> attendances = attendanceService.findByEmployeeIdAndCheckinTimeBetween(id, month);

            String emailContent = buildAttendanceEmailContent(employee, attendances, month);
            emailService.sendEmail(employee.getEmail(), "Monthly Attendance Report", emailContent);

            return ResponseEntity.ok("Attendance report sent successfully to " + employee.getEmail());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
