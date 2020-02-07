package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;

public class IndexService {
    private static User userDao = new User().dao();
    private static Task taskDao = new Task().dao();

    public BaseResponse login(String username, String password) {
        System.out.println("test");
        BaseResponse response = new BaseResponse();
        User user = userDao.findFirst("select * from user where UserName = ? ", username);
        if (user != null){
            if (user.getUserPassword().equals(password)){
                response.setResult(ResultCodeEnum.LOGIN_SUCCESS);
            }else {
                response.setResult(ResultCodeEnum.LOGIN_ERROR);
            }
        } else {
            response.setResult(ResultCodeEnum.NO_EXIST_USER);
        }
        return response;
    }
}
