package cn.net.syzc.analysis.kit;

@SuppressWarnings("unused")
public enum ResultCodeEnum {

    SITES_OPEN("1001", "网页打开成功"),
    INTERNEE_FAILURE("1002", "网络错误，请重试"),
    UNKNOWN_ERROR("1003", "Unknown error, please try again."),
    REQUEST_NO_PARAM_ID_ERROR("1004", "页面请求参数错误"),
    DB_SYS_ERROR("1005", "数据库错误"),
    RECORD_NO_EXIST("1006", "No records in the database."),
    File_NO_EXIST("1007", "File not found."),
    USER_NOT_LOGIN("1008", "User not login."),

    DB_CONNECTION_SUCCESS("200O", "数据库连接成功"),
    DB_CONNECTION_FAILURE("2001", "数据库连接失败"),
    DB_UPDATE_SUCCESS("2002", "数据库修改成功"),
    DB_UPDATE_ERROR("2003", "数据库修改失败"),
    DB_ERROR_OVERFLOW("2004", "数据库修改失败_字段字数超过规定"),
    DB_ERROR_FORMAT("2005", "数据库修改失败_字段输入数据格式错误"),
    DB_FIND_SUCCESS("2006", "Database query successful."),
    DB_FIND_FAILURE("2007", "数据库查找失败，没有该条记录"),
    DB_WARNING_NULL_WRONG_PARA("2008", "该次查询结果为空_输入参数错误"),
    DB_DELETE_SUCCESS("2009", "数据删除成功"),
    DB_DELETE_FAILURE("2010", "数据删除失败"),

    PARA_FORMAT_ERROR("3000", "请求的参数格式错误"),
    PARA_NUM_ERROR("3001", "Wrong number of request parameters."),
    PARA_PHONE_ERROR("3002", "错误的手机号"),
    PARA_EMAIL_ERROR("3003", "错误的邮箱格式"),
    PARA_PASSWORD_ERROR("3004", "错误的密码格式"),

    LOGIN_SUCCESS("4000", "Login success."),
    LOGIN_ERROR("4001", "Login failed, password error."),
    NO_EXIST_USER("4002", "Login failed, user dose not exist."),
    NO_ENOUGH_MES("4003", "登录失败_账号或密码为空"),
    LOGOUT_SUCCESS("4004", "退出登录成功"),
    NO_LOGIN_USER("4005", "退出登录失败_用户未登录"),
    LOGIN_FAILURE_CODE_ERROR("4008", "登陆失败，验证码错误"),
    LOGOUT_FAILURE("4009", "退出登录失败"),
    // 注册
    MODIFY_FAILURE_PWD_ERROR("5000", "修改失败，密码错误"),
    REGISTER_SUCCESS("5001", "注册成功"),
    REGISTER_FAILURE_DB_ERROR("5002", "注册失败_数据库操作错误"),
    REGISTER_FAILURE_USER_EXIST("5003", "注册失败_账户已存在"),
    REGISTER_FAILURE_SYS_ERROR("5004", "注册失败_系统错误"),


    MODIFY_SUCCESS("5005", "修改成功"),

    CATEGORY_QUERY_NULL("6000", "货品类别查询为空"),
    TASK_QUERY_SUCCESS("6001", "Task query success."),
    GOODS_QUERY_SUCCESS("6002", "货品查询成功"),
    GOODS_QUERY_NULL("6003", "货品查询为空"),

    TASK_DELETE_SUCCESS("6004", "Task deleted successfully."),
    TASK_NOT_EXIST("6005", "Task not exist."),
    TASK_DELETE_FAILURE_DB_ERROR("6006", "Task deleted failed_database error."),

    CLASSIFICATION_GET_SUCCESS("6007", "Get the classification number success."),
    SIMILARITY_GET_SUCCESS("6008", "Get the similarity success."),
    CALL_PYTHON_SCRIPTS_ERROR("6009", "Call the python scripts error."),
    CALL_PYTHON_SCRIPTS_SUCCESS("6010", "Call the python scripts success."),

    GOODS_FOLLOW_FAILURE_DB_ERROR("6010", "货品添加关注失败_数据库错误"),
    GOODS_FOLLOW_QUERY_SUCCESS("6011", "货品关注查询成功"),
    GOODS_FOLLOW_QUERY_NULL("6012", "货品关注查询为空"),
    COMMENT_SUCCESS("6013", "评论成功"),
    COMMENT_FAILURE_DB_ERROR("6014", "评论失败"),

    GOODS_UPLOAD_FAILURE_DB_ERROR("6015", "货品上传失败_数据库错误"),
    TASK_ADD_SUCCESS("6016", "Task added successfully."),
    TASK_ADD_FAILURE_DB_ERROR("6017", "Task added fail_database error."),
    // pass: 0; Different number of attributes: 1; Duplicate point: 2; Contains other attributes not 0 or 1 :3;
    //  * Edge file exception: 4; Classification file exception: 5.
    ATTR_FILE_EXCEPTION("6018", "Attribute file exception."),
    EDGE_FILE_EXCEPTION("6019", "Edge file exception."),
    CLASS_FILE_EXCEPTION("6020","Classification file exception."),
    FILE_CHECK_PASSED("6021", "File check passed."),

    GOODS_CANCEL_FOLLOW_FAILURE_DB_ERROR("6023", "货品取消关注失败_数据库错误"),
    GOODS_CANCEL_FOLLOW__SUCCESS("6024", "货品取消关注成功"),
    GOODS_NOT_FOLLOW("6025", "货品未关注"),

    USER_FOLLOW_THIS_GOODS("6026", "用户关注该商品"),
    USER_NOT_FOLLOW_THIS_GOODS("6027", "用户未关注该商品"),
    USER_NOT_FOLLOW_GOODS("6028", "用户未关注商品"),

    USER_QUERY_SUCCESS("7000", "用户查询成功"),
    TEST("9000", "测试");


    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
