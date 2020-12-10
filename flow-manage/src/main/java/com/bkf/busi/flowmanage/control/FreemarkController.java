package com.bkf.busi.flowmanage.control;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName FreemarkController
 * @Description
 * @Author wangxuefei
 * @Date 2020/12/10 17:37
 * @Version V1.0
 **/
@Controller
@RequestMapping("/bbb")
public class FreemarkController {

    @RequestMapping("/aaa")
    public String index(Model model) {
       return "aaa";
    }
}

