package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;

import java.util.ArrayList;
import java.util.List;

public class IndexService {
    private static User userDao = new User().dao();
    private static Task taskDao = new Task().dao();

    public User login(String username, String password) {
        BaseResponse response = new BaseResponse();
        return userDao.findFirst("select * from user where UserName = ? ", username);
    }

    public List<Task> getTaskList(String UserId) {
        BaseResponse baseResponse = new BaseResponse();
        List<Task> taskList = new ArrayList<>();
        taskList = taskDao.find("select * from task where UserID = ? ", UserId);
        return taskList;
    }
}
