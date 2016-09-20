package com.study.yizw.customizeview.dao;

/**
 * Created by yizw on 2016/9/20.
 */

public enum DaoThreadMode {

    /**
     * 同一个线程
     */
    PostThread,

    /**
     * 主线程
     */
    MainThread,

    /**
     * 后台线程
     */
    BackgroundThread,

    /**
     * 单独开一个线程
     */
    Async

}
