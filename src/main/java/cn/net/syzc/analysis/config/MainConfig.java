package cn.net.syzc.analysis.config;

import cn.net.syzc.analysis.controller.IndexController;
import cn.net.syzc.analysis.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class MainConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        //读取数据库配置文件
        PropKit.use("config.properties");
        //设置当前是否为开发模式
        me.setDevMode(PropKit.getBoolean("devMode"));
        //设置默认上传文件保存路径 getFile等使用
        me.setBaseUploadPath("upload/");
        //设置上传最大限制尺寸
        me.setMaxPostSize(1024 * 1024 * 1024);
        //设置默认下载文件路径 renderFile使用
        me.setBaseDownloadPath("download");
        //设置默认视图类型
        me.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dbPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dbPlugin);
        arp.setShowSql(PropKit.getBoolean("devMode"));
        arp.setDialect(new MysqlDialect());
        _MappingKit.mapping(arp);
        me.add(dbPlugin);
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
