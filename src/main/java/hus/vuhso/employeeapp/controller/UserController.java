package hus.vuhso.employeeapp.controller;

import hus.vuhso.employeeapp.dto.response.UserDto;
import hus.vuhso.employeeapp.dto.request.UserCreateForm;
import hus.vuhso.employeeapp.service.UserService;
import hus.vuhso.employeeapp.utils.RequestMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(RequestMappingUtils.USER)
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(RequestMappingUtils.CREATE)
    public UserDto createUser(UserCreateForm form) {
        return userService.createUser(form);
    }
    @DeleteMapping(RequestMappingUtils.DELETE_BY_ID)
    public void deleteUser(@PathVariable(RequestMappingUtils.ID) Long id) {
        userService.deleteUser(id);
    }
    @GetMapping(RequestMappingUtils.FIND_BY_USERNAME)
    public UserDto findByUsername(@PathVariable(RequestMappingUtils.USERNAME) String username) {
        return userService.findByUsername(username);
    }
}
