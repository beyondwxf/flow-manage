package com.bkf.busi.flowmanage.service;

import com.bkf.busi.flowmanage.bean.FlowOrder;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.util.Map;

/**
 * @ClassName FlowOrderService
 * @Description
 * @Author wangxuefei
 * @Date 2020/12/7 11:35
 * @Version V1.0
 **/
public interface FlowOrderService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    int insert(FlowOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    int insertSelective(FlowOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    FlowOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FlowOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table flow_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FlowOrder record);


    /**
     * 分页查询数据
     * @param dataMap 参数
     * @param pageInfo
     * @return
     */
    PageInfo<FlowOrder> findFlowOrderByPage(Map<String, Object> dataMap, PageInfo pageInfo);



    /**
     *功能描述
     * @author wangxuefei
     * @date 2020/12/10
     * @param pageNum
     * @param pageSize
     * @return java.util.List<com.bkf.busi.flowmanage.bean.FlowOrder>
     */
    PageInfo<FlowOrder> findFlowOrderList(int pageNum,int pageSize);


    /**
     * excel数据导入数据库
     * @param normalFile
     */
    void importExcel(File normalFile) throws Exception;

}
