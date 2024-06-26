package hus.vuhso.employeeapp.service.serviceImpl;

import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.excel.CSVHelper;
import hus.vuhso.employeeapp.excel.ExcelGenerator;
import hus.vuhso.employeeapp.service.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
@Service
@AllArgsConstructor
public class ExcelImpl implements ExcelService {
    private final EmployeeServiceImpl employeeService;
    @Override
    public ResponseEntity<InputStreamResource> exportLateEmployees() throws IOException {
        List<EmployeeDto> employees = employeeService.findLateEmployees();

        ByteArrayInputStream in = ExcelGenerator.employeesToExcel(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=late_employees.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }

    @Override
    public ResponseEntity<String> importEmployees(MultipartFile file) {
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                List<EmployeeDto> employees = CSVHelper.csvToEmployees(file.getInputStream());
                employeeService.saveAll(employees);
                return ResponseEntity.status(HttpStatus.OK).body("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a csv file!");
    }

    @Override
    public ResponseEntity<Resource> exportEmployees() {
        String filename = "employees.csv";
        InputStreamResource file = new InputStreamResource(CSVHelper.employeesToCSV(employeeService.findAll()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
