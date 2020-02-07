package cn.net.syzc.analysis.controller;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;
import cn.net.syzc.analysis.service.IndexService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

import java.util.List;

@SuppressWarnings("unused")
public class IndexController extends Controller {
    private static IndexService indexService = new IndexService();

    public void index() {
        renderText("success");
    }

    public void login() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String username = getPara("username");
            String password = getPara("password");
            if (!StrKit.isBlank(username) && !StrKit.isBlank(password)) {
                User user = indexService.login(username, password);
                if (user != null) {
                    if (user.getUserPassword().equals(password)) {
                        baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
                        setSessionAttr("user_id", user.getUserID());
                    }
                } else {
                    baseResponse.setResult(ResultCodeEnum.NO_EXIST_USER);
                }
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    public void logout() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            // clear the session
            removeSessionAttr("user_id");
            getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

    /**
     *
     */
    public void getTaskList() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String UserId = getPara("UserId");
            if (!StrKit.isBlank(UserId)) {
                List<Task> taskList = indexService.getTaskList(UserId);
            } else {
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setResult(ResultCodeEnum.UNKNOWN_ERROR);
        } finally {
            renderJson(baseResponse);
        }
    }

}
