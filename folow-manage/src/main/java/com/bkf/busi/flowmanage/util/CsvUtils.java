package com.bkf.busi.flowmanage.util;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSV工具类
 * @author wangxuefei 20180404
 */
public class CsvUtils {

    public static  List<List<Object>> getBankListByCSV(File file) throws Exception{
        List<List<Object>> list = new ArrayList<>();

        FileReader fReader = new FileReader(file);
        CSVReader csvReader = new CSVReader(fReader);

        List<String[]> lines = csvReader.readAll();
        for(String[] ss : lines){
            list.add(new ArrayList<>(Arrays.asList(ss)));
        }
        csvReader.close();
        // 删除头行
        list.remove(0);
        return list;
    }
}
