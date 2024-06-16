package hus.vuhso.employeeapp.utils;

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
public class RequestMappingUtils {
    public static final String USER = "/api/user";
    public static final String EMPLOYEE = "/api/employee";
    public static final String ATTENDANCE = "/api/attendance";
    public static final String EXCEL = "/api/excel";

    public static final String CREATE = "/create";
    public static final String UPDATE_BY_ID = "/update/{id}";
    public static final String DELETE_BY_ID = "/delete/{id}";
    public static final String GET_BY_ID = "/{id}";
    public static final String FIND_ALL = "/find-all";

    public static final String FIND_BY_USERNAME = "/find/{username}";
    public static final String FIND_BY_EMPLOYEE_ID = "/find/{employeeId}";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String EMPLOYEE_ID = "employeeId";

    //API EMPLOYEE
    public static final String SEND_ATTENDANCE_EMAIL = "/{id}/send-attendance";


    //API EXCEL
    public static final String EXPORT = "/export";
    public static final String IMPORT = "/import";
    public static final String EXPORT_LAST_EMPLOYEE = "/late-employees";
}
