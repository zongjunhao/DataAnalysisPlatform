package cn.net.syzc.analysis.kit;

@SuppressWarnings("unused")
public enum ResultCodeEnum {

    UNKNOWN_ERROR("1003", "Unknown error, please try again."),
    RECORD_NO_EXIST("1006", "No records in the database."),
    File_NO_EXIST("1007", "File not found."),
    USER_NOT_LOGIN("1008", "User not login."),
    DB_FIND_SUCCESS("2006", "Database query successful."),
    PARA_NUM_ERROR("3001", "Wrong number of request parameters."),
    LOGIN_SUCCESS("4000", "Login success."),
    LOGIN_ERROR("4001", "Login failed, password error."),
    NO_EXIST_USER("4002", "Login failed, user dose not exist."),
    TASK_QUERY_SUCCESS("6001", "Task query success."),
    TASK_DELETE_SUCCESS("6004", "Task deleted successfully."),
    TASK_NOT_EXIST("6005", "Task not exist."),
    TASK_DELETE_FAILURE_DB_ERROR("6006", "Task deleted failed_database error."),
    CLASSIFICATION_GET_SUCCESS("6007", "Get the classification number success."),
    SIMILARITY_GET_SUCCESS("6008", "Get the similarity success."),
    CALL_PYTHON_SCRIPTS_ERROR("6009", "Call the python scripts error."),
    CALL_PYTHON_SCRIPTS_SUCCESS("6010", "Call the python scripts success."),
    TASK_ADD_SUCCESS("6016", "Task added successfully."),
    TASK_ADD_FAILURE_DB_ERROR("6017", "Task added fail_database error."),
    // pass: 0; Different number of attributes: 1; Duplicate point: 2; Contains other attributes not 0 or 1 :3;
    //  * Edge file exception: 4; Classification file exception: 5.
    ATTR_FILE_EXCEPTION("6018", "Attribute file exception."),
    EDGE_FILE_EXCEPTION("6019", "Edge file exception."),
    CLASS_FILE_EXCEPTION("6020","Classification file exception."),
    FILE_CHECK_PASSED("6021", "File check passed."),
    READ_FILE_FAILED("6022", "Read file failed, please check the file."),
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
