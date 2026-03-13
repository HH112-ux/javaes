package com.jh.supermarket.servic.system;

import com.jh.supermarket.bean.Log;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.util.DataUtil;
import com.jh.supermarket.util.FileUtil;

import java.text.SimpleDateFormat;

import static com.jh.supermarket.util.DataUtil.addLog;
import static com.jh.supermarket.util.DataUtil.logsData;

public class Logservic {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    public static void showLogData() {
        System.out.println("-----------日志记录----------");
        if (DataUtil.logsData.isEmpty()) {
            System.out.println("暂无日志记录");
            addLog("查看", "暂无日志记录", false, LogTypeEnum.SYSTEM_LOG);
            return;
        }
        for(Log log:logsData){
            System.out.println(log.getId()+","+SIMPLE_DATE_FORMAT.format(log.getDate()));
            System.out.println(LogTypeEnum.getDescByCode(log.getLogType())+" "+(log.isSuccess()?"成功":"失败"));
            System.out.println(log.getTitle()+":"+log.getDescription());
        }
        System.out.println("记录共"+logsData.size()+"条");
         FileUtil.loadLogs();
        addLog("查看", "查看全部日志记录", true, LogTypeEnum.SYSTEM_LOG);
    }
}
