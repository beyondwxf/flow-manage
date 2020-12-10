package com.bkf.busi.flowmanage.util.page;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author wangxuefei
 * @title: PageStart
 * @projectName stock-app
 * @date 2019/7/24 14:33
 */
public class PageStart {
    /**
     * 处理排序
     * @return
     * @author wangxuefei
     * @date 2019/7/24 14:34 
     */
    public static void start(Map<String, Object> dataMap, PageInfo pageInfo){
        String orderby = StringUtils.EMPTY;
        if (dataMap != null) {
            orderby = dataMap.get("orderby") == null ? StringUtils.EMPTY : String.valueOf(dataMap.get("orderby"));
        }
        int pageNum = pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum();
        int pageSize = pageInfo.getPageSize() == 0 ? 10 : pageInfo.getPageSize();

        if (StringUtils.isNotBlank(orderby)) {
            orderby = orderby.replace("~"," ");
            PageHelper.startPage(pageNum,pageSize,orderby);
        }else {
            PageHelper.startPage(pageNum,pageSize);
        }
    }
}
