package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.EmployeeDto;
import hus.vuhso.employeeapp.form.EmployeeCreateForm;
import hus.vuhso.employeeapp.form.EmployeeUpdateForm;
import hus.vuhso.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public EmployeeDto create(@RequestBody EmployeeCreateForm form) {
        return employeeService.create(form);
    }
    @GetMapping("/")
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
    @PutMapping("/{id}")
    public EmployeeDto update(@RequestBody EmployeeUpdateForm form, @PathVariable("id") Long id) {
        return employeeService.update(form, id);
    }
}
