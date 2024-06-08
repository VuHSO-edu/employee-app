package hus.vuhso.employeeapp.service;

import hus.vuhso.employeeapp.dto.EmployeeDto;
import hus.vuhso.employeeapp.form.EmployeeCreateForm;
import hus.vuhso.employeeapp.form.EmployeeUpdateForm;
import hus.vuhso.employeeapp.mapper.EmployeeMapper;
import hus.vuhso.employeeapp.repository.AttendanceRepository;
import hus.vuhso.employeeapp.repository.EmployeeRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    private AttendanceRepository attendanceRepository;



    @Override
    public EmployeeDto create(EmployeeCreateForm form) {
        var employee = EmployeeMapper.map(form);
        var savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.map(savedEmployee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::map)
                .toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        var employee = employeeRepository.findById(id).get();
        return EmployeeMapper.map(employee);
    }


    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto update(EmployeeUpdateForm form, Long id) {
        var employee = employeeRepository.findById(id).get();
        EmployeeMapper.map(form, employee);
        var savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.map(savedEmployee);
    }

    @Override
    public void saveAll(List<EmployeeDto> employees) {
        employeeRepository.saveAll(employees.stream().map(EmployeeMapper::map).toList());
    }

    @Override
    public List<EmployeeDto> findLateEmployees() {
        return attendanceRepository.findLateEmployees()
                .stream()
                .map(EmployeeMapper::map)
                .toList();
    }
}
