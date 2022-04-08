
package com.ustc.utils;


import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 容量转换工具
 * @author 田宝宁
 * @date 2022/03/07
 */
public class CapacityUtils {
    public static String convertDetail(Long size) {

        if (size == 0L) {
            return "0B";
        }
        if (size < 1024L) {
            return size + "B";
        }
        if (1024.0D <= size && size < 1048576.0D) {
            return formatForInteger(size / 1024.0D) + "KB";
        }
        if (1048576.0D <= size && size < 1.073741824E9D) {
            return formatForInteger(size / 1048576.0D) + "MB";
        }
        if (1.073741824E9D <= size && size < 1.292785156096E12D) {
            return formatForInteger(size / 1.073741824E9D) + "GB";
        }
        return formatForInteger(size / 1.099511627776E12D) + "TB";
    }

    public static String convert(Long size) {
        if (size == null) {
            return "0B";
        }
        if (size == 0L) {
            return "0B";
        }
        if (size < 1024L) {
            return size + "B";
        }
        if (1024.0D <= size && size < 1048576.0D) {
            return formatDouble(size / 1024.0D) + "KB";
        }
        if (1048576.0D <= size && size < 1.073741824E9D) {
            return formatDouble(size / 1048576.0D) + "MB";
        }
        if (1.073741824E9D <= size && size < 1.292785156096E12D) {
            return formatDouble(size / 1.073741824E9D) + "GB";
        }
        return formatDouble(size / 1.099511627776E12D) + "TB";

    }


    public static String convert(String filesize) {
        if (StringUtils.isEmpty(filesize)) {
            return "-";
        }
        long size = Long.parseLong(filesize);
        if (size == 0L) {
            return "-";
        }
        String str;
        if (size < 1024L) {
            str = size + "B";
        } else if (1024.0D <= size && size < 1048576.0D) {
            str = formatDouble(size / 1024.0D) + "KB";
        } else if (1048576.0D <= size && size < 1.073741824E9D) {
            str = formatDouble(size / 1048576.0D) + "MB";
        } else if (1.073741824E9D <= size && size < 1.292785156096E12D) {
            str = formatDouble(size / 1.073741824E9D) + "GB";
        } else {
            str = formatDouble(size / 1.099511627776E12D) + "TB";
        }
        return str;
    }


    public static String formatDouble(double d) {
        BigDecimal bg = (new BigDecimal(d)).setScale(2, RoundingMode.UP);
        double num = bg.doubleValue();

        if (Math.round(num) - num == 0.0D) {
            return String.valueOf((long) num);
        }

        return String.valueOf(num);

    }


    public static String formatForInteger(double d) {
        BigDecimal bg = (new BigDecimal(d)).setScale(100000, RoundingMode.UP);
        double num = bg.doubleValue();

        if (Math.round(num) - num == 0.0D) {
            return String.valueOf((long) num);
        }

        return String.valueOf(num);
    }

}