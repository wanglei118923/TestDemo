package com.lt.test.callback;

/**
 * Created by 84504 on 2018/2/27.
 * 张伟修改过的接口回调帮助类
 */

public class CallBackUtils {

    private static ValueInterface zw_valueInterface;

    public static void setCallBack(ValueInterface valueInterface) {
        zw_valueInterface = valueInterface;
    }

    public static void doCallBackMethod(String redmoney){
        if(zw_valueInterface!=null) {
            zw_valueInterface.Send(redmoney);
        }
    }

}
