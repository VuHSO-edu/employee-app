package hus.vuhso.employeeapp.service;

import hus.vuhso.employeeapp.dto.AttendanceDto;
import hus.vuhso.employeeapp.dto.EmployeeDto;
import hus.vuhso.employeeapp.entity.Attendance;
import hus.vuhso.employeeapp.form.AttendanceCreateForm;
import hus.vuhso.employeeapp.mapper.AttendanceMapper;
import hus.vuhso.employeeapp.mapper.EmployeeMapper;
import hus.vuhso.employeeapp.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
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
@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceDto create(AttendanceCreateForm form) {
        var attendance = AttendanceMapper.map(form);
        var savedAttendance = attendanceRepository.save(attendance);
        return AttendanceMapper.map(savedAttendance);
    }

    @Override
    public List<AttendanceDto> findByEmployeeId(Long employeeId) {
        var attendances = attendanceRepository.findByEmployeeId(employeeId);
        return AttendanceMapper.map(attendances);
    }

    @Override
    public List<AttendanceDto> findByEmployeeIdAndCheckinTimeBetween(Long employeeId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        return attendanceRepository.findByEmployeeIdAAndCheckinTimeBetween(employeeId, startDate.atStartOfDay(), endDate.atTime(23, 59, 59))
                .stream()
                .map(AttendanceMapper::map)
                .toList();
    }
}

