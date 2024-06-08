package hus.vuhso.employeeapp.service;

import hus.vuhso.employeeapp.dto.AttendanceDto;
import hus.vuhso.employeeapp.dto.EmployeeDto;
import hus.vuhso.employeeapp.entity.Attendance;
import hus.vuhso.employeeapp.form.AttendanceCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceDto create(AttendanceCreateForm form);

    List<AttendanceDto> findByEmployeeId(Long employeeId);


}
