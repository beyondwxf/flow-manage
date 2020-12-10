package com.bkf.busi.flowmanage.control;

import com.alibaba.fastjson.JSON;
import com.bkf.busi.flowmanage.bean.FlowOrder;
import com.bkf.busi.flowmanage.service.FlowOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FlowOrderController
 * @Description
 * @Author wangxuefei
 * @Date 2020/12/7 16:35
 * @Version V1.0
 **/
@RestController
public class FlowOrderController {
    @Resource
    private FlowOrderService flowOrderService;

    @GetMapping("/index")
    public Map index() {
        Map map = new HashMap();
        map.put("test", "test");
        return map;
    }
    @GetMapping("/getList")
    public String  getList(HttpServletRequest req, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Map<String, Object> dataMap = new HashMap<>();
        PageHelper.startPage(pageNum,pageSize);

       PageInfo<FlowOrder> pageInfo = flowOrderService.findFlowOrderList(pageNum,pageSize);
        return JSON.toJSONString(pageInfo);
    }




    @GetMapping("/saveOrder")
    public String  saveOrder(HttpServletRequest req, @RequestParam("phone") String phone, @RequestParam("plateNumber") String plateNumber, @RequestParam("standardName") String standardName, @RequestParam("applicantName") String applicantName, @RequestParam("channelId") String channelId, @RequestParam("secret") String secret) {
        FlowOrder flowOrder  = new FlowOrder();
        flowOrder.setPhone(phone);
        flowOrder.setPlateNumber(plateNumber);
        flowOrder.setStandardName(standardName);
        flowOrder.setApplicantName(applicantName);
        flowOrder.setChannelId(channelId);
        flowOrder.setCreateTime(new Date());

        if (flowOrderService.insertSelective(flowOrder)>0) {
            return "success";
        }
        return JSON.toJSONString(flowOrder);
    }
}
