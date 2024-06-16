package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.response.AttendanceDto;
import hus.vuhso.employeeapp.dto.request.AttendanceCreateForm;
import hus.vuhso.employeeapp.service.AttendanceService;
import hus.vuhso.employeeapp.utils.RequestMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping(RequestMappingUtils.ATTENDANCE)
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(RequestMappingUtils.CREATE)
    public AttendanceDto create(@RequestBody AttendanceCreateForm form) {
        return attendanceService.create(form);
    }

    @GetMapping(RequestMappingUtils.FIND_BY_EMPLOYEE_ID)
    public List<AttendanceDto> findByEmployeeId(@PathVariable(RequestMappingUtils.EMPLOYEE_ID) Long employeeId) {
        return attendanceService.findByEmployeeId(employeeId);
    }
}
