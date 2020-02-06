package cn.net.syzc.analysis.service;

import cn.net.syzc.analysis.kit.BaseResponse;
import cn.net.syzc.analysis.model.Task;
import cn.net.syzc.analysis.model.User;
import cn.net.syzc.analysis.model._MappingKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import org.junit.jupiter.api.Test;

public class IndexService {
    private static User userDao = new User().dao();
    private static Task taskDao = new Task().dao();

    static {
        PropKit.use("config.properties");
        DruidPlugin dbPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dbPlugin);
        arp.setShowSql(PropKit.getBoolean("devMode"));
        arp.setDialect(new MysqlDialect());
        _MappingKit.mapping(arp);
        dbPlugin.start();
        arp.start();
    }

    @Test
    public void login() {
        System.out.println("test");
        BaseResponse response = new BaseResponse();
        String username = "test";
        String password = "a";
        User user = userDao.findFirst("select * from user where UserName = ? and UserPassword = ?", username, password);
        System.out.println("user = " + user);
        System.out.println(user == null);
        // return response;
    }
}
