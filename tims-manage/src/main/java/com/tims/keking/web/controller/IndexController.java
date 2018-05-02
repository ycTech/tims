package com.tims.keking.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  页面跳转
 * @author yudian-it
 * @date 2017/12/27
 */
@Controller
public class IndexController {

    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public String go2Index() {
        return "index";
    }

    @RequestMapping(value="preview/upload", method = RequestMethod.GET)
    public String go2FileList () {
        return  "upload";
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String root() {
//        return "redirect:/index";
//    }
}
