package com.bawei.guojinyi;

import android.app.Application;
import android.content.Context;

/**
 * ClassName: weektest01
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2019/12/31 8:52
 * @Description: 用途：完成特定功能
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= this;
    }
}
