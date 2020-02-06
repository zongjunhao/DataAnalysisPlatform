package cn.net.syzc.analysis.controller;

import cn.net.syzc.analysis.service.IndexService;
import com.jfinal.core.Controller;

@SuppressWarnings("unused")
public class IndexController extends Controller {
    private static IndexService indexService = new IndexService();

    public void index() {
        renderText("success");
    }

    public void login() {
        String username = getPara("username");
        String password = getPara("password");

    }

}
