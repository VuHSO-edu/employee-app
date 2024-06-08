package hus.vuhso.employeeapp.excel;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import hus.vuhso.employeeapp.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "id", "name", "dateOfBirth", "email" };

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<EmployeeDto> csvToEmployees(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVReader csvReader = new CSVReader(fileReader)) {

            List<EmployeeDto> employees = new ArrayList<>();
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                EmployeeDto employee = new EmployeeDto();
                employee.setId(Long.parseLong(values[0]));
                employee.setName(values[1]);
                employee.setDateOfBirth(LocalDate.parse(values[2]));
                employee.setEmail(values[3]);
                employees.add(employee);
            }
            return employees;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }


    public static ByteArrayInputStream employeesToCSV(List<EmployeeDto> employees) {
        final CSVWriter writer;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(out);
             CSVWriter csvWriter = new CSVWriter(osw)) {

            String[] header = { "id", "name", "dateOfBirth", "email" };
            csvWriter.writeNext(header);

            for (EmployeeDto employee : employees) {
                String[] data = {
                        String.valueOf(employee.getId()),
                        employee.getName(),
                        employee.getDateOfBirth().toString(),
                        employee.getEmail()
                };
                csvWriter.writeNext(data);
            }

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV file: " + e.getMessage());
        }
    }


}

