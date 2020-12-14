package com.bkf.busi.flowmanage.service.impl;

import com.bkf.busi.flowmanage.bean.FlowOrder;
import com.bkf.busi.flowmanage.mapper.FlowOrderMapper;
import com.bkf.busi.flowmanage.service.FlowOrderService;
import com.bkf.busi.flowmanage.util.CsvUtils;
import com.bkf.busi.flowmanage.util.DateUtils;
import com.bkf.busi.flowmanage.util.ExcelUtils;
import com.bkf.busi.flowmanage.util.StringUtils;
import com.bkf.busi.flowmanage.util.page.PageStart;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName FlowOrderServiceImpl
 * @Description
 * @Author wangxuefei
 * @Date 2020/12/7 11:36
 * @Version V1.0
 **/
@Service
public class FlowOrderServiceImpl implements FlowOrderService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private FlowOrderMapper flowOrderMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return flowOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FlowOrder record) {
        return flowOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(FlowOrder record) {
        return flowOrderMapper.insertSelective(record);
    }

    @Override
    public FlowOrder selectByPrimaryKey(Integer id) {
        return flowOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(FlowOrder record) {
        return flowOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FlowOrder record) {
        return flowOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<FlowOrder> findFlowOrderByPage(Map<String, Object> dataMap, PageInfo pageInfo) {
        PageStart.start(dataMap, pageInfo);
        List<FlowOrder> list = flowOrderMapper.findFlowOrderByPage();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<FlowOrder> findFlowOrderList(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FlowOrder> list = flowOrderMapper.findFlowOrderByPage();

        PageInfo<FlowOrder> pageInfo = new PageInfo<FlowOrder>(list);
        return pageInfo;
    }



    /**
     * excel数据导入数据库
     * @param normalFile
     */
    @Override
    public void importExcel(File normalFile) throws Exception {

        List<List<Object>> list = null;
        // 1、判断文件类型
        String fileName = normalFile.getPath();
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if(".csv".equalsIgnoreCase(fileType)){
            list = CsvUtils.getBankListByCSV(normalFile);
//            log.info(" ** 解析CSV文件完成，共{}行。",list.size());
            System.out.println(" ** 解析CSV文件完成，共{}行。"+list.size());
        } else {
            InputStream input = new FileInputStream(normalFile);
            list = ExcelUtils.getBankListByExcel(input,normalFile.getPath());
//            log.info(" ** 解析Excel文件完成，共{}行。",list.size());
            System.out.println(" ** 解析Excel文件完成，共{}行。"+list.size());
        }
        List<Object[]> params = new ArrayList<>();

        String sql = "INSERT INTO `channel_flow`.`flow_order` (`phone`, `plate_number`, `standard_name`, `applicant_name`, `create_time`) VALUES (?, ?,?, ?,?);";
        for(List<Object> rows : list){

            Date date = null;
            System.out.println(rows.get(0).toString());
            System.out.println(rows.get(1).toString());
            System.out.println(rows.get(2).toString());
            System.out.println(rows.get(3).toString());
            if (rows.size()<5) {
               date = new Date();
            } else {
                date = DateUtils.toDate(rows.get(4).toString(),null);
            }
            System.out.println("-----------================---------");
//            rows.remove(0);
//            rows.add(0,date);
//            rows.add(0, UUID.randomUUID().toString().replaceAll("-",""));
//
//            Object[] os = rows.toArray(new Object[rows.size()]);
//            params.add(os);

            params.add(new Object[]{
//                    UUID.randomUUID().toString().replaceAll("-",""),
                    String.valueOf(rows.get(0)),
                    String.valueOf(rows.get(1)),
                    String.valueOf(rows.get(2)),
                    String.valueOf(rows.get(3)),
                    date
            });

        }
        long startTime = System.currentTimeMillis();
        try {
            jdbcTemplate.batchUpdate(sql, params);
        }catch (Exception e){ // 异常不做处理，继续执行。
//            log.info(e.getMessage());
            System.out.println(e.getMessage());
        }
        System.out.println(" ** 批量插入完成,耗时：{}秒"+(System.currentTimeMillis()-startTime)/1000);
//        log.info(" ** 批量插入完成,耗时：{}秒",(System.currentTimeMillis()-startTime)/1000);
    }
}
