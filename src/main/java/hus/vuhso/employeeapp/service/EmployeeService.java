package hus.vuhso.employeeapp.service;

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

import hus.vuhso.employeeapp.dto.EmployeeDto;
import hus.vuhso.employeeapp.form.EmployeeCreateForm;
import hus.vuhso.employeeapp.form.EmployeeUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeCreateForm form);

    EmployeeDto findById(Long id);

    void deleteById(Long id);

    List<EmployeeDto> findAll();

    EmployeeDto update(EmployeeUpdateForm form, Long id);

    public void saveAll(List<EmployeeDto> employees);

    List<EmployeeDto> findLateEmployees();
}
