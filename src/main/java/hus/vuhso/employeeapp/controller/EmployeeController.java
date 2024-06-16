package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.response.AttendanceDto;
import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.dto.request.EmployeeCreateForm;
import hus.vuhso.employeeapp.dto.request.EmployeeUpdateForm;
import hus.vuhso.employeeapp.service.EmailService;
import hus.vuhso.employeeapp.service.EmployeeService;
import hus.vuhso.employeeapp.utils.RequestMappingUtils;
import hus.vuhso.employeeapp.utils.validation.EmployeeIdExists;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RestController
@AllArgsConstructor
@RequestMapping(RequestMappingUtils.EMPLOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmailService emailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(RequestMappingUtils.CREATE)
    public EmployeeDto create(@RequestBody EmployeeCreateForm form) {
        return employeeService.create(form);
    }
    @GetMapping(RequestMappingUtils.FIND_ALL)
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(RequestMappingUtils.GET_BY_ID)
    public EmployeeDto findById(@PathVariable(RequestMappingUtils.ID) @EmployeeIdExists Long id) {
        return employeeService.findById(id);
    }
    @DeleteMapping(RequestMappingUtils.DELETE_BY_ID)
    public void deleteById(@PathVariable(RequestMappingUtils.ID) Long id) {
        employeeService.deleteById(id);
    }
    @PutMapping(RequestMappingUtils.UPDATE_BY_ID)
    public EmployeeDto update(@RequestBody EmployeeUpdateForm form, @PathVariable(RequestMappingUtils.ID) @EmployeeIdExists Long id) {
        return employeeService.update(form, id);
    }


    @PostMapping(RequestMappingUtils.SEND_ATTENDANCE_EMAIL)
    public ResponseEntity<String> sendAttendanceEmail(@PathVariable(RequestMappingUtils.ID) Long id,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return emailService.sendAttendanceEmail(id, month);
    }
}
