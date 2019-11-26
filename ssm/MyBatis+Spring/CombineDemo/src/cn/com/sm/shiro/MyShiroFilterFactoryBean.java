package cn.com.sm.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyShiroFilterFactoryBean  extends ShiroFilterFactoryBean {
    private static final Logger log= LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);
    /**
     * 配置中的过滤链
     */
    public static String definitions;
    @Override
    public void setFilterChainDefinitions(String definitions) {

        MyShiroFilterFactoryBean.definitions = definitions;


        /*
         //数据库动态权限
        List<TbShiroFilter> list = systemService.getShiroFilter();
        for(TbShiroFilter tbShiroFilter : list){
            //字符串拼接权限
            definitions = definitions+tbShiroFilter.getName() + " = "+tbShiroFilter.getPerms()+"\n";
        }
        */
        definitions = "/static/** = anon\n" +
                        "/css/** = anon\n" +
                        "/js/** = anon\n" +
                        "/img/** = anon\n" +
                        "/vue/** = anon\n" +
                        "/login.html = anon\n" +
                        "/login.do = anon\n" +
                        "/logout = logout\n" +
                        "/index.html = user\n" +
                        "/index.do = user\n" +
                        "/** = user\n";
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
