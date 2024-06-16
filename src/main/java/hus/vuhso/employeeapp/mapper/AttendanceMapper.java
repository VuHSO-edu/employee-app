package hus.vuhso.employeeapp.mapper;

import hus.vuhso.employeeapp.dto.response.AttendanceDto;
import hus.vuhso.employeeapp.entity.Attendance;
import hus.vuhso.employeeapp.dto.request.AttendanceCreateForm;

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
public class AttendanceMapper {
    public static Attendance map(AttendanceCreateForm form) {
        var attendance = new Attendance();
        attendance.setCheckinTime(form.getCheckinTime());
        attendance.setCheckoutTime(form.getCheckoutTime());
        return attendance;
    }

    public static AttendanceDto map(Attendance attendance) {
        var dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setEmployee(attendance.getEmployee());
        dto.setCheckinTime(attendance.getCheckinTime());
        dto.setCheckoutTime(attendance.getCheckoutTime());
        return dto;
    }

    public static List<AttendanceDto> map(List<Attendance> attendances) {
        return attendances.stream().map(AttendanceMapper::map).toList();
    }


}
