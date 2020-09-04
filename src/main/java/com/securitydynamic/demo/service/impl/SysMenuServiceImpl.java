package com.securitydynamic.demo.service.impl;

import com.securitydynamic.demo.pojo.SysMenu;
import com.securitydynamic.demo.mapper.MenuMapper;
import com.securitydynamic.demo.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<SysMenu> getMenus() {
        return menuMapper.selectAllMenus();
    }
}