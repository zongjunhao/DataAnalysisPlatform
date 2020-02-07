package cn.net.syzc.analysis.controller;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.kit.ResultCodeEnum;
import cn.net.syzc.analysis.service.IndexService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

@SuppressWarnings("unused")
public class IndexController extends Controller {
    private static IndexService indexService = new IndexService();

    public void index() {
        renderText("success");
    }

    public void login() {
        BaseResponse baseResponse = new BaseResponse();
        String username = getPara("username");
        String password = getPara("password");
        if (!StrKit.isBlank(username) && !StrKit.isBlank(password)) {
            baseResponse = indexService.login(username, password);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

}
