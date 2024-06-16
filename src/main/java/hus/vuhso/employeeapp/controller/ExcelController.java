package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.excel.CSVHelper;
import hus.vuhso.employeeapp.excel.ExcelGenerator;
import hus.vuhso.employeeapp.service.EmployeeService;
import hus.vuhso.employeeapp.service.ExcelService;
import hus.vuhso.employeeapp.utils.RequestMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
@RequestMapping(RequestMappingUtils.EXCEL)
@AllArgsConstructor
public class ExcelController {
    private final ExcelService excelService;

    @GetMapping(RequestMappingUtils.EXPORT_LAST_EMPLOYEE)
    public ResponseEntity<InputStreamResource> exportLateEmployees() throws IOException {
        return excelService.exportLateEmployees();
    }

    @PostMapping(RequestMappingUtils.IMPORT)
    public ResponseEntity<String> importEmployees(@RequestParam("file") MultipartFile file) {
        return excelService.importEmployees(file);
    }

    @GetMapping(RequestMappingUtils.EXPORT)
    public ResponseEntity<Resource> exportEmployees() {
        return excelService.exportEmployees();
    }

}
