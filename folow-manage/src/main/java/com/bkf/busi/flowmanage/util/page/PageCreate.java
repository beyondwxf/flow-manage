package com.bkf.busi.flowmanage.util.page;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author wangxuefei
 * @title: PageCreate
 * @projectName stock-app
 * TODO
 * @date 2019/6/19 16:50
 */
public class PageCreate<T> {
    /**
     * 初始化Page对象
     * @param dataMap 参数
     * @return 
     * @author wangxuefei
     * @date 2019/6/19 16:59 
     */
    public PageInfo<T> create(Map<String,Object> dataMap){
        PageInfo<T> pageInfo = new PageInfo<>();
        String pageNumStr = "";
        String pageSizeStr = "";
        if (null !=dataMap.get("pageNum")) {
            pageNumStr = dataMap.get("pageNum").toString();
        }
        if (null !=dataMap.get("pageSize")) {
            pageSizeStr = dataMap.get("pageSize").toString();
        }


        int pageNum = StringUtils.isBlank(pageNumStr)?1:Integer.parseInt(String.valueOf(dataMap.get("pageNum")));

        int pageSize = StringUtils.isBlank(pageSizeStr) ? 10
                : Integer.parseInt(String.valueOf(dataMap.get("pageSize")));

        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }
}
