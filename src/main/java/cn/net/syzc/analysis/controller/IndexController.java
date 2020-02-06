package cn.net.syzc.analysis.controller;

import com.jfinal.core.Controller;

@SuppressWarnings("unused")
public class IndexController extends Controller {

    public void index() {
        renderText("success");
    }

    public void test() {
        render("index.jsp");
    }
}
