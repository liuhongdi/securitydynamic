package com.securitydynamic.demo.pojo;

import java.util.List;

public class SysMenu {
    private Integer menuId;
    private String pattern;
    private List<String> roles;
    //menu id
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    //menu pattern
    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    //menu roles
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + menuId +
                ", pattern='" + pattern + '\'' +
                ", roles=" + roles +
                '}';
    }
}