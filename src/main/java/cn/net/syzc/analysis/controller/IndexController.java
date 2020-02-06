package cn.net.syzc.analysis.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class IndexController extends Controller {

    @ActionKey("index")
    public void index() {
        renderText("success");
    }

    @ActionKey("test")
    public void test() {
        render("index.jsp");
    }
}
