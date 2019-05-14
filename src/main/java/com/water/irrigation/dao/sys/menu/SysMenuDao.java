package com.water.irrigation.dao.sys.menu;

import com.water.irrigation.entity.sys.menu.SysMenu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 菜单数据库层
 *
 * @author licl
 */
public interface SysMenuDao extends JpaRepository<SysMenu,Long> {

    /**
     * 根据层级查找
     *
     * @param level 层级
     * @return java.util.List<SysMenu>
     * @author licl
     */
    List<SysMenu> findByLevel(Integer level);

}
