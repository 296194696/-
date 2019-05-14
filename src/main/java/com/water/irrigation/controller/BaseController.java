package com.water.irrigation.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@Controller
public class BaseController {

    @RequestMapping("/login_page")
    public String login(){
        return "login_page";
    }

    @RequestMapping("/register_page")
    public String register(){
        return "my/register";
    }

    @RequestMapping("/login_layui")
    public String login1(){
        return "views/user/login";
    }

    @RequestMapping("/user")
    public String list(){
        return "views/user/user/list";
    }

    @RequestMapping("/user/administrators/list")
    public String list1(){
        return "views/user/user/list";
    }

    @RequestMapping("/console")
    public String console(){
        return "views/home/console";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/table")
    public String table(){
        return "my/displayuserinfo";
    }

    @RequestMapping("/table1")
    public String table1(){
        return "my/change_user_role";
    }

    @RequestMapping("/simple")
    public String simple(){
        return "my/simple";
    }

    @RequestMapping("/userform")
    public String userform(){
        return "views/user/user/userform";
    }

    @RequestMapping("/block")
    public String block(){
        return "my/block";
    }

    @RequestMapping("/blockform")
    public String blockinfo(){
        return "my/blockform";
    }

    @RequestMapping("/cropform")
    public String cropform(){
        return "my/cropform";
    }

    @RequestMapping("/charge")
    public String charge(){
        return "my/charge";
    }

    @RequestMapping("/chargeform")
    public String chargeform(){
        return "my/chargeform";
    }

    @RequestMapping("/staticriver")
    public String staticriver(){
        return "river/static";
    }

    @RequestMapping("/dynamicriver")
    public String dynamicriver(){
        return "river/dynamic";
    }

    @RequestMapping("/staticform")
    public String staticform(){
        return "river/staticform";
    }

    @RequestMapping("/dynamicform")
    public String dynamicform(){
        return "river/dynamicform";
    }

    @RequestMapping("/clearSession")
    public void initSession(HttpServletRequest request){
        Enumeration em=request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }

    }

}
