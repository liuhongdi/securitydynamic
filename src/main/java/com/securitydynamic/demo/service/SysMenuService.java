package com.securitydynamic.demo.service;

import com.securitydynamic.demo.pojo.SysMenu;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> getMenus();
}

/*
import com.fern.security.bean.Menu;
import com.fern.security.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuService {
    @Autowired
    private MenuMapper menuMapper;
    public List<Menu> getMenus() {
        return menuMapper.getMenus();
    }
}
*/