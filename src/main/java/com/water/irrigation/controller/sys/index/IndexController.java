package com.water.irrigation.controller.sys.index;

import com.water.irrigation.entity.sys.menu.SysMenu;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.service.sys.menu.SysMenuService;
import com.water.irrigation.service.sys.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/index")
    public String index(ModelMap map){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<SysMenu> sysMenus=sysMenuService.findByLevel(1);

        String username=userDetails.getUsername();
        SysUser sysUser=sysUserService.findUserByUsername(username);
        map.addAttribute("username", sysUser.getSname());
        map.addAttribute("url", "/test");
        map.addAttribute("sysMenus", sysMenus);
        return "views/index";
    }

    @RequestMapping("/index1")
    @ResponseBody
    public List<SysMenu> test(){

        List<SysMenu> sysMenus=sysMenuService.findByLevel(1);

        return sysMenus;
    }

}
