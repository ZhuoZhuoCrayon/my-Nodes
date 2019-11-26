package cn.com.sm.po.company_sys;

import java.io.Serializable;

public class Role implements Serializable {
    private Long id;
    private String role;
    private String description;

    Role(){}
    Role(Long id,String role,String description){
        this.id = id;
        this.role = role;
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRole() {
        return role;
    }
}
