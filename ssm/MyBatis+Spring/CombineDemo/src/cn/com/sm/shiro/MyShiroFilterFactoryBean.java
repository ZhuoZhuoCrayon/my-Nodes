package cn.com.sm.shiro;

import cn.com.sm.po.company_sys.Permission;
import cn.com.sm.service.impl.company_sys.PermissionsServiceImpl;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroFilterFactoryBean  extends ShiroFilterFactoryBean {

    @Autowired
    private PermissionsServiceImpl permissionsService;

    private static final Logger log= LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);
    /**
     * 配置中的过滤链
     */
    public static String definitions;
    @Override
    public void setFilterChainDefinitions(String definitions) {

        MyShiroFilterFactoryBean.definitions = definitions;

        List<Permission> permissions = permissionsService.findAll();
        definitions = "/static/** = anon\n" +
                        "/css/** = anon\n" +
                        "/js/** = anon\n" +
                        "/img/** = anon\n" +
                        "/vue/** = anon\n" +
                        "/login.html = anon\n" +
                        "/login.do = anon\n" +
                        "/logout = logout\n" +
                        "/index.html = authc\n" +
                        "/index.do = authc\n" +
                        "/mall.html = authc\n" +
                        "/mall.do = authc\n" +
                        "/purchases/trade.do = authc\n" +
                        "/customers/getIds.do = authc\n" +
                        "/employees/getIds.do = authc\n" +
                        "/user/getUser.do = authc\n";
        //导入权限
        for(Permission permission:permissions){
            definitions += permission.getPermission() + " = " + permission.getPerms() + "\n";
        }
        System.out.println(definitions);
        log.info(definitions);

        //从配置文件加载权限配置
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        this.setFilterChainDefinitionMap(section);
    }
}
