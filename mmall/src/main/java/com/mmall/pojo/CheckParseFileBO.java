package com.mmall.pojo;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class CheckParseFileBO {

    /**
     * 文件夹路径
     */
    private String dirPath;

    /**
     * 文件夹名截取规则, beginIndex-endIndex
     */
    private String dirNameSubRule;

    /**
     * 字符串转时间格式
     */
    private String dateFormat;

    /**
     * 该文件夹下前一天的file
     */
    private List<File> yesterdayFileList;

    /**
     * 该文件夹下近三天的file
     */
    private List<File> threeDayFileList;

}

