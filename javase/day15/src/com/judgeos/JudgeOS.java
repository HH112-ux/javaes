package com.judgeos;

/**
 * @author jh
 * @project com.judgeos
 * @time 2026/1/17
 */
public class JudgeOS {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name").toLowerCase();
        OSType osType =null;
        if (osName.contains("windows")) {
            osType = OSType.WINDOWS;
        }else if (osName.contains("linux")) {
            osType = OSType.LINUX;
        }
        switch (osType){
            case WINDOWS :
                System.out.println("你的系统时windows，推荐使用IE浏览器");
                break;
            case LINUX:
                System.out.println("你的系统是linux，推荐使用IE浏览器");
                break;
            default:
                System.out.println("操作系统未知");
        }

    }
}
