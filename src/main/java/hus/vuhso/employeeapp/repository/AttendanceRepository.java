package hus.vuhso.employeeapp.repository;

import hus.vuhso.employeeapp.dto.AttendanceDto;
import hus.vuhso.employeeapp.entity.Attendance;
import hus.vuhso.employeeapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeId(Long employeeId);

    @Query("SELECT e FROM Employee e WHERE e.id IN (" +
            "SELECT a.employee.id FROM Attendance a WHERE " +
            "MONTH(a.checkinTime) = MONTH(CURRENT_DATE) AND " +
            "YEAR(a.checkinTime) = YEAR(CURRENT_DATE) " +
            "GROUP BY a.employee.id " +
            "HAVING MIN(HOUR(a.checkinTime) * 60 + MINUTE(a.checkinTime)) > 9 * 60 " +
            "AND MAX(HOUR(a.checkoutTime) * 60 + MINUTE(a.checkoutTime)) < 17 * 60 + 30)")
    List<Employee> findLateEmployees();


    List<Attendance> findByEmployeeIdAAndCheckinTimeBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
