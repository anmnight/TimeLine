package com.anxiao.timeline;

import android.app.Application;
import com.anxiao.timeline.data.database.DBRegister;
import com.facebook.stetho.Stetho;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.ultraframework
 * @description: application
 * @date: 2019-12-30
 * @time: 16:18
 */
public class MvtmApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        DBRegister.init(this);

        //init stetho
        Stetho.initializeWithDefaults(this);

    }
}
