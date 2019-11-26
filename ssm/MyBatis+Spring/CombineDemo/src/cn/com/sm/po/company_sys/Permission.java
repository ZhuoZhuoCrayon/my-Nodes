package cn.com.sm.po.company_sys;

import java.io.Serializable;

public class Permission implements Serializable {
    private Long id;
    private String permission;
    private String perms;
    Permission(){}
    Permission(Long id,String permission,String perms){
        this.id = id;
        this.permission = permission;
        this.perms = perms;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Long getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public String getPerms() {
        return perms;
    }
}
