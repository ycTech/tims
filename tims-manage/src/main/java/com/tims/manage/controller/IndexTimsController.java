package com.tims.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  页面跳转
 * @author yudian-it
 * @date 2017/12/27
 */
@Controller
public class IndexTimsController {

    @RequestMapping(value = "/scanner", method = RequestMethod.GET)
    public String scanner() {
        return "redirect:/scanner-index.html";
    }
}
