package com.bkf.busi.flowmanage.control;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * User: wangy
 * Date: 2020/6/11
 * Description: No Description
 */
@Controller
//@RequestMapping("/studentManage")
public class StudentController   {

    @GetMapping("/test")
    public ModelAndView toStudentPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        System.out.println("student");
        return modelAndView;
    }
}
