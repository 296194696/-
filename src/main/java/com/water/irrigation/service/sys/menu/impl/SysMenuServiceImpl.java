package com.water.irrigation.service.sys.menu.impl;

import com.water.irrigation.dao.sys.menu.SysMenuDao;
import com.water.irrigation.entity.sys.menu.SysMenu;
import com.water.irrigation.service.sys.menu.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 根据层级查找
     *
     * @param level 层级
     * @return java.util.List<SysMenu>
     * @author licl
     */
    public List<SysMenu> findByLevel(Integer level){
        return sysMenuDao.findByLevel(level);
    }

    /**
     * 添加工会组织
     * @param sysMenu 要添加的工会组织信息
     * @return 添加的工会组织信息
     */
    @Override
    public SysMenu add(SysMenu sysMenu) {
        return sysMenuDao.save(sysMenu);
    }

    /**
     * 修改工会组织
     * @param sysMenu 工会组织信息
     * @return  更新后工会组织信息
     */
    @Override
    public SysMenu update(SysMenu sysMenu) {
        SysMenu orgObj =
                this.sysMenuDao.findById(sysMenu.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, SysMenu);
        return sysMenuDao.save(sysMenu);
    }

    /**
     * 删除工会组织信息
     * @param sysMenus 需要删除的资产信息
     */
    @Override
    public void delete(List<SysMenu> sysMenus) {
        for(SysMenu sysMenu : sysMenus ) {
            sysMenuDao.deleteById(sysMenu.getIndocno());
        }
    }
}
