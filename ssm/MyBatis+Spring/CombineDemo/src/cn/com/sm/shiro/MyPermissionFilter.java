package cn.com.sm.shiro;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Exrickx
 */
public class MyPermissionFilter extends AuthorizationFilter {

    private static final Logger log= LoggerFactory.getLogger(MyPermissionFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        System.out.println("验证权限....");
        Subject subject = this.getSubject(request, response);
        String[] perms = (String[]) ((String[]) o);
        boolean isPermitted = true;

        if (subject.getPrincipal() == null) {
            log.info("未登录或登录时间过长！");
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("success", false);
            resultMap.put("message", "您还未登录或登录时间过长,请重新登录！");
            FilterUtil.out(response, resultMap);
            //isPermitted = false;
        } else {
            if (perms != null && perms.length > 0) {
                if (perms.length == 1) {
                    if (!subject.isPermitted(perms[0])) {
                        isPermitted = false;
                    }
                } else if (!subject.isPermittedAll(perms)) {
                    isPermitted = false;
                }
            }
            if (!isPermitted) {
                log.info("没有该权限");
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("success", false);
                resultMap.put("message", "没有该权限");
                FilterUtil.out(response, resultMap);
            }
        }
        return isPermitted;
    }

}
