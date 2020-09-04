package com.securitydynamic.demo.mapper;

import com.securitydynamic.demo.pojo.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuMapper {
    List<SysMenu> selectAllMenus();
}