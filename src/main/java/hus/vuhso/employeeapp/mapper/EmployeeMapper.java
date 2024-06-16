package hus.vuhso.employeeapp.mapper;

import hus.vuhso.employeeapp.dto.response.EmployeeDto;
import hus.vuhso.employeeapp.entity.Employee;
import hus.vuhso.employeeapp.dto.request.EmployeeCreateForm;
import hus.vuhso.employeeapp.dto.request.EmployeeUpdateForm;

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
public class EmployeeMapper {
    public static Employee map(EmployeeCreateForm form) {
        var employee = new Employee();
        employee.setName(form.getName());
        employee.setDateOfBirth(form.getDateOfBirth());
        employee.setEmail(form.getEmail());
        return employee;
    }

    public static EmployeeDto map(Employee employee) {
        var dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setEmail(employee.getEmail());
        return dto;
    }

    public static void map(EmployeeUpdateForm form, Employee employee) {
        employee.setName(form.getName());
        employee.setDateOfBirth(form.getDateOfBirth());
        employee.setEmail(form.getEmail());
    }

  public static Employee map(EmployeeDto employeeDto) {
    var employee = new Employee();
    employee.setId(employeeDto.getId());
    employee.setName(employeeDto.getName());
    employee.setDateOfBirth(employeeDto.getDateOfBirth());
    employee.setEmail(employeeDto.getEmail());
    return employee;
  }
}
