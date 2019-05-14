package com.water.irrigation.controller.sys.menu;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.sys.menu.SysMenuDto;
import com.water.irrigation.entity.sys.menu.SysMenu;
import com.water.irrigation.service.sys.menu.SysMenuService;
import com.water.irrigation.utils.dozer.DozerHelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    @RequestMapping("/my/menu")
    public String menu(){
        return "my/menu";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public List<SysMenu> tblMenu(){
        List<SysMenu> sysMenus=sysMenuService.findByLevel(1);
        return sysMenus;
    }

    /**
     * 添加 菜单信息
     * @param SysMenuDto 菜单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("menu/add")
    @ResponseBody
    public ResultEntity add(SysMenuDto SysMenuDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            SysMenu sysMenu =
                    dozerHelperUtils.convert(SysMenuDto, SysMenu.class);
            SysMenu result = this.sysMenuService.add(sysMenu);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加菜单信息出错:"+e.getMessage());
            log.error("添加 菜单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 菜单表信息
     * @param sysMenuDto 菜单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("menu/update")
    @ResponseBody
    public ResultEntity update(SysMenuDto sysMenuDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            SysMenu sysMenu =
                    dozerHelperUtils.convert(sysMenuDto, SysMenu.class);
            SysMenu result = this.sysMenuService.
                    update(sysMenu);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 菜单信息出错:"+e.getMessage());
            log.error("修改 菜单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 菜单表信息
     * @param sysMenuDto 菜单表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("menu/delete")
    @ResponseBody
    public ResultEntity delete(SysMenuDto sysMenuDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<SysMenu> sysMenus =
                    sysMenuDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.sysMenuService.delete(sysMenus);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 菜单信息出错:"+e.getMessage());
            log.error("删除 菜单信息出错", e);
        }
        return resultEntity;
    }


}
