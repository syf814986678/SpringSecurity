package com.shiyifan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "views/login";
    }
    @RequestMapping("/{level}/{id}")
    public String tolevel(@PathVariable("level")String level,@PathVariable("id") int id){
        return ("views/"+level+"/"+id);
    }
}
