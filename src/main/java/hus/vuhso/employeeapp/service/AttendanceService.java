package hus.vuhso.employeeapp.service;

import hus.vuhso.employeeapp.dto.response.AttendanceDto;
import hus.vuhso.employeeapp.dto.request.AttendanceCreateForm;

import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {
    AttendanceDto create(AttendanceCreateForm form);

    List<AttendanceDto> findByEmployeeId(Long employeeId);

    List<AttendanceDto> findByEmployeeIdAndCheckinTimeBetween(Long employeeId, YearMonth month);
}
