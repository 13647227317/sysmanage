package com.wy.sysmanage.service.impl;

import com.wy.sysmanage.service.MenuService;
import com.wy.sysmanage.vo.MenuTreeVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class MenuServiceImplTest {

    @Resource
    private MenuService menuService;

    @Test
    public void getUserMenuTree(){
        List<MenuTreeVo> list=menuService.getUserMenuTree(1L);
        System.out.println(list);
    }
}