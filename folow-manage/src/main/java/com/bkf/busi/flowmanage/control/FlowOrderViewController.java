package com.bkf.busi.flowmanage.control;

import com.alibaba.fastjson.JSON;
import com.bkf.busi.flowmanage.service.FlowOrderService;
import com.bkf.busi.flowmanage.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 *功能描述
 * @author wangxuefei
 * @date 2020/12/10
 * @param
 * @return
 */
@Controller
public class FlowOrderViewController {
    @Resource
    private FlowOrderService flowOrderService;

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
    @RequestMapping(value="/to_upload",method={RequestMethod.GET, RequestMethod.POST})
    public String toUpload(HttpServletRequest req) {
        return ("upload_excel");
    }



    /**
     * 上传图片
     */
    @RequestMapping(value="/upload_file",method={RequestMethod.GET, RequestMethod.POST},  produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("localFile") MultipartFile attachFile){

        Map<String, Object> jsonMap=new HashMap<String, Object>();

        //上传文件后缀名称
        String extendName = attachFile.getOriginalFilename().substring(attachFile.getOriginalFilename().lastIndexOf("."));
        //新文件名称
        String newFileName = UUID.randomUUID().toString() + extendName;

        try
        {
            //应用的真实路径
            String realContextPath = request.getSession().getServletContext().getRealPath("/");
            String relativePath = "upload/" + DateUtils.getDate(new Date());
            // 新文件所属目录
            File destFile = new File(realContextPath + relativePath);

            if (!destFile.exists()){
                destFile.mkdirs();
            }

            // 新文件
            File normalFile = new File(destFile, newFileName);
            attachFile.transferTo(normalFile);

            jsonMap.put("req_code", "T");
            jsonMap.put("req_mess", "文件上传成功");
            jsonMap.put("fileURL", normalFile);

            jsonMap.put("error", 0);
            jsonMap.put("url", normalFile);

            // 操作excel
            flowOrderService.importExcel(normalFile);

//            logger.info("上传成功，返回参数：{}", JSON.toJSONString(jsonMap));
            System.out.println("上传成功，返回参数：{}"+ JSON.toJSONString(jsonMap));
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("req_code", "F");
            jsonMap.put("req_mess", "文件上传失败");

            jsonMap.put("error", 1);
            jsonMap.put("message", e.toString());
        }

        return jsonMap;
    }
}
