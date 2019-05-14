package com.water.irrigation.service.sys.menu;

import com.water.irrigation.entity.sys.menu.SysMenu;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> findByLevel(Integer level);

    SysMenu add(SysMenu sysMenu);

    SysMenu update(SysMenu sysMenu);

    void delete(List<SysMenu> sysMenus);
}
