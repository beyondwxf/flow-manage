package com.bkf.busi.flowmanage.service.impl;

import com.bkf.busi.flowmanage.bean.FlowOrder;
import com.bkf.busi.flowmanage.mapper.FlowOrderMapper;
import com.bkf.busi.flowmanage.service.FlowOrderService;
import com.bkf.busi.flowmanage.util.page.PageStart;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
}
