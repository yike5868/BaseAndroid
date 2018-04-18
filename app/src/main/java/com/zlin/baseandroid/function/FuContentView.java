package com.zlin.baseandroid.function;

import android.content.Context;
import android.view.LayoutInflater;

import com.zlin.baseandroid.R;
import com.zlin.baseandroid.control.FuEventCallBack;
import com.zlin.baseandroid.control.FuUiFrameModel;

/**
 * Created by zhanglin on 2017/5/8.
 */

public class FuContentView extends FuUiFrameModel {
    public FuContentView(Context cxt, FuEventCallBack callBack) {
        super(cxt, callBack);
    }

    @Override
    protected void createFuLayout() {

        mFuView = LayoutInflater.from(mContext).inflate(
                R.layout.fu_content, null);
    }

    @Override
    protected void initFuData() {

    }

    @Override
    protected void initWidget() {

    }
}
