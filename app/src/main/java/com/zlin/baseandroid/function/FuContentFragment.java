package com.zlin.baseandroid.function;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlin.baseandroid.FuApp;
import com.zlin.baseandroid.control.FragmentParent;
import com.zlin.baseandroid.control.FuResponse;
import com.zlin.baseandroid.control.FuUiFrameManager;

/**
 * Created by zhanglin on 2017/5/8.
 */

public class FuContentFragment extends FragmentParent {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("activity","FuWelcomeFragment");
        FuUiFrameManager lFuUiFrameManager = ((FuApp) getActivity()
                .getApplication()).getFuUiFrameManager();

        mModel = lFuUiFrameManager.createFuModel(
                FuUiFrameManager.FU_CONTENT, getActivity(),
                null);

        return mModel.getFuView();
    }
    @Override
    protected void loadDataChild(int taskId, FuResponse rspObj) {

    }

    @Override
    protected void netErrorChild(int taskId, String msg) {

    }

    @Override
    protected void cancelChild(int taskId) {

    }
}
