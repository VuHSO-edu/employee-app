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

import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.dto.request.EmployeeCreateForm;
import hus.vuhso.employeeapp.dto.request.EmployeeUpdateForm;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
