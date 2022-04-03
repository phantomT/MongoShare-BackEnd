package com.ustc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件类工具
 * @author 田宝宁
 */
public class FileUtils {
    /**
     * 返回文件夹名称, 文件夹名称设为年月日
     * @return 文件夹名称
     */
    public static String getFolder() {
        SimpleDateFormat formatYear=new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth=new SimpleDateFormat("MM");
        SimpleDateFormat formatDay=new SimpleDateFormat("dd");
        String year=formatYear.format(new Date());
        String month=formatMonth.format(new Date());
        String day=formatDay.format(new Date());

        return year+"/"+month+"/"+day;
    }

    public static List<String> idJsonToList(String idJson) throws JsonProcessingException {
        ArrayList<String> idList = new ArrayList<>();
        if (!StringUtils.isEmpty(idJson)) {
            // 将Json数据转换为Map
            List<Map> list = JsonUtils.jsonToList(idJson, Map.class);
            // 将id加入到列表中, 等下查询使用
            for (Map item : list) {
                Object id = item.get("id");
                if (!StringUtils.isEmpty(id)) {
                    idList.add(id.toString());
                }
            }
        }
        return idList;
    }
}
