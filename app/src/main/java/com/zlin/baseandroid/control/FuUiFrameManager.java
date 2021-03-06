package com.zlin.baseandroid.control;


import android.content.Context;

import com.zlin.baseandroid.function.FuContentView;
import com.zlin.baseandroid.function.FuWelcomeView;

public class FuUiFrameManager {


    public static final int FU_MAIN_ID = 999; // MainActivity 视图ID
    public static final int FU_CONTENT_ID = 998; // ContentActivity 视图ID

    public static final int FU_WELCOME = 1; // 欢迎界面
    public static final int FU_MAIN_HOME = 2; // 首页
    public static final int FU_LOGIN = 3; // 登录
    public static final int FU_CONTENT = 4;//主页列表
    public FuUiFrameModel createFuModel(int type, Context contex,
                                        FuEventCallBack callBack) {
        switch (type) {

            case FU_WELCOME:
                return new FuWelcomeView(contex, callBack);
            case FU_CONTENT:
                return new FuContentView(contex,callBack);

        }
        return null;
    }
}
