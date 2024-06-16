package hus.vuhso.employeeapp.service.serviceImpl;

import hus.vuhso.employeeapp.dto.response.UserDto;
import hus.vuhso.employeeapp.dto.request.UserCreateForm;
import hus.vuhso.employeeapp.entity.Role;
import hus.vuhso.employeeapp.mapper.UserMapper;
import hus.vuhso.employeeapp.repository.RoleRepository;
import hus.vuhso.employeeapp.repository.UserRepository;
import hus.vuhso.employeeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

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
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



    @Override
    public UserDto createUser(UserCreateForm form) {
        var user = UserMapper.map(form);
        var encodePassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodePassword);
        var role = roleRepository.findByType(Role.Type.EMPLOYEE);
        user.setRoles(Set.of(role));
        var save = userRepository.save(user);
        return UserMapper.map(save);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return UserMapper.map(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        var authorities = new ArrayList<GrantedAuthority>();
        var roles = user.getRoles();
        for (var role : roles) {
            var type = role.getType().toString();
            var authority = new SimpleGrantedAuthority(type);
            authorities.add(authority);
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
