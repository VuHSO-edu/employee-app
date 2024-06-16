package hus.vuhso.employeeapp;

import hus.vuhso.employeeapp.entity.Role;
import hus.vuhso.employeeapp.entity.User;
import hus.vuhso.employeeapp.repository.RoleRepository;
import hus.vuhso.employeeapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@SpringBootApplication
@EnableTransactionManagement
@AllArgsConstructor
public class EmployeeAppApplication implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user = new User();

        user.setUsername("VuHSO");
        var encodedPassword = passwordEncoder.encode("123456"); // mã hóa mật khẩu
        user.setPassword(encodedPassword);
        var admin = roleRepository.findByType(Role.Type.ADMIN);
        var employee = roleRepository.findByType(Role.Type.EMPLOYEE);
        var roles = Set.of(admin, employee);
        user.setRoles(roles);
        userRepository.save(user);

    }
}
