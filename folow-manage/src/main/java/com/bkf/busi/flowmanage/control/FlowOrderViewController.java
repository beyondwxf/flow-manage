package com.bkf.busi.flowmanage.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 *功能描述
 * @author wangxuefei
 * @date 2020/12/10
 * @param
 * @return
 */
@Controller
public class FlowOrderViewController {

    @GetMapping("/list")
    public String index() {

        return "list";
    }


    /**
     * 导入页面
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="to_upload",method={RequestMethod.GET, RequestMethod.POST})
    public String toUpload(HttpServletRequest req) {
        return ("upload_excel");
    }

}
