package com.lt.test.constant;

import android.Manifest;

/**
 * 权限常量类
 *  READ_EXTERNAL_STORAGE 读的权限
 *  WRITE_EXTERNAL_STORAGE 写入的权限
 *  CAMERA 拍照的权限
 *  CALL_PHONE 打电话的权限
 *  ACCESS_FINE_LOCATION 定位权限
 *  READ_SMS 获取短信的权限
 * */
public class Permission {
    //读的权限
    /**
    *   READ_EXTERNAL_STORAGE 读的权限
    * */
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    /**
     *   WRITE_EXTERNAL_STORAGE 写入的权限
     * */
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    /**
     *   CAMERA 拍照的权限
     * */
    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
    /**
     *   CALL_PHONE 打电话的权限
     * */
    public static final String CAMERA = Manifest.permission.CAMERA;
    /**
     *   ACCESS_FINE_LOCATION 定位权限
     * */
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    /**
     *   READ_SMS 获取短信的权限
     * */
    public static final String CODE_SMS = Manifest.permission.READ_SMS;
}
