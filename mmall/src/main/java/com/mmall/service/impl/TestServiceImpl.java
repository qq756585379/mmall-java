package com.mmall.service.impl;

import com.mmall.pojo.CheckParseFileBO;
import com.mmall.service.ITestService;
import com.mmall.util.DateTimeUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TestServiceImpl implements ITestService {

    public void call() {
        List<CheckParseFileBO> list = getCheckParseFileBOList();

        for (int i = 0; i < list.size(); i++) {
            CheckParseFileBO checkParseFileBO = list.get(i);
            String[] subList = checkParseFileBO.getDirNameSubRule().split("-");

            File dir = new File(checkParseFileBO.getDirPath());//  取得目录
            File[] fs = dir.listFiles();//  取得目录下所有文件

            List<File> yesterdayFileList = new LinkedList<>();
            List<File> threeDayFileList = new LinkedList<>();
            SimpleDateFormat sdf = new SimpleDateFormat(checkParseFileBO.getDateFormat());

            // 遍历该文件夹下所有的文件
            for (int j = 0; j < fs.length; j++) {
                File file = fs[j];
                try {
                    String fileName = file.getName();
                    fileName = fileName.substring(Integer.parseInt(subList[0]), Integer.parseInt(subList[1]));
                    Date date = sdf.parse(fileName);
                    int dev = DateTimeUtils.diffDays(date);
                    if (dev > 0 && dev <= 3) {
                        if (dev == 1) {
                            yesterdayFileList.add(file);
                        }
                        threeDayFileList.add(file);
                    }
//                        System.out.println("文件 = " + fileName);
                } catch (StringIndexOutOfBoundsException e) {
                    String fileName = fs[i].getName();
                    System.out.println("文件 = " + fileName);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            checkParseFileBO.setYesterdayFileList(yesterdayFileList);
            checkParseFileBO.setThreeDayFileList(threeDayFileList);

            System.out.println("yesterdayFileList = " + yesterdayFileList);
            System.out.println("threeDayFileList = " + threeDayFileList);
        }
    }

    private List<CheckParseFileBO> getCheckParseFileBOList() {
        String root_path = "/Users/yangjun/Documents/journalfile/";
        List<CheckParseFileBO> list = new ArrayList<CheckParseFileBO>();

        CheckParseFileBO billBo = new CheckParseFileBO();
        billBo.setDirPath(root_path + "99bill");
        billBo.setDirNameSubRule("0-10");
        list.add(billBo);

        CheckParseFileBO alipayBo = new CheckParseFileBO();
        alipayBo.setDirPath(root_path + "alipay");
        alipayBo.setDirNameSubRule("0-8");
        list.add(alipayBo);

        return list;
    }
}
