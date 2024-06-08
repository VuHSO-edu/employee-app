package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.AttendanceDto;
import hus.vuhso.employeeapp.entity.Attendance;
import hus.vuhso.employeeapp.form.AttendanceCreateForm;
import hus.vuhso.employeeapp.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RestController
@RequestMapping("/api/attendances")
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public AttendanceDto create(@RequestBody AttendanceCreateForm form) {
        return attendanceService.create(form);
    }

    @GetMapping("/employee/{employeeId}")
    public List<AttendanceDto> findByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        return attendanceService.findByEmployeeId(employeeId);
    }
}
