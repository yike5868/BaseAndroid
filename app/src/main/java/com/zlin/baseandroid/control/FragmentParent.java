package com.zlin.baseandroid.control;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.zlin.baseandroid.Constant;
import com.zlin.baseandroid.FuApp;
import com.zlin.baseandroid.activity.FuContentActivity;
import com.zlin.baseandroid.db.helper.ALocalSqlHelper;
import com.zlin.baseandroid.net.MyTask;
import com.zlin.baseandroid.net.NetCallBack;
import com.zlin.baseandroid.tools.ToastUtil;
import com.zlin.baseandroid.tools.ToolUtil;

import java.io.File;

public abstract class FragmentParent extends Fragment {

    protected FuUiFrameModel mModel;

    protected String PAGE_SIZE = "10"; // 加载条数


    /**
     * 数据库
     */
    protected ALocalSqlHelper sqlHelper;


    protected int type;//分类
    Activity activity;//主页面

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper = ((FuApp) getActivity().getApplication()).getSqlHelper();
        activity = getActivity();

    }

    public ALocalSqlHelper getSqlHelper() {
        if (sqlHelper == null)
            sqlHelper = ((FuApp) getActivity().getApplication()).getSqlHelper();
        return sqlHelper;
    }

    protected abstract void loadDataChild(int taskId, FuResponse rspObj); //

    protected abstract void netErrorChild(int taskId, String msg); //

    protected abstract void cancelChild(int taskId); //

    protected mNetCallBack mNetBaseCallBack;

    public final int NET_ERROR = 500; // 网络错误

    public mNetCallBack getCallBackInstance() {

        if (mNetBaseCallBack == null) {
            mNetBaseCallBack = new mNetCallBack();
        }

        return mNetBaseCallBack;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class mNetCallBack implements NetCallBack {

        @Override
        public void cancel(int taskId) {
            Log.e("mNet", mModel.getClass().getName());
            if (taskId == MyTask.UP_LOAD_FILE) {
                ToolUtil.hidePopLoading();
                ToastUtil.showToast(getContext(), "已取消!", Toast.LENGTH_LONG);
                return;
            }
            cancelChild(taskId);
        }

        @Override
        public void loadData(int taskId, FuResponse rspObj) {

            if (taskId == MyTask.UP_LOAD_FILE) {
                Message error = Message.obtain(parentHandler);
                error.what = MyTask.UP_LOAD_FILE;
                error.obj = rspObj.getMessage();
                error.sendToTarget();
                return;
            }

            Log.e("mNet", mModel.getClass().getName());
            loadDataChild(taskId, rspObj);
        }

        @Override
        public void netError(int taskId, String msg) {
            Log.e("mNet", mModel.getClass().getName());

            if (taskId == MyTask.UP_LOAD_FILE) {
                ToastUtil.showToast(getContext(), "保存失败!" + msg, Toast.LENGTH_LONG);
                return;
            }

            netErrorChild(taskId, msg);
        }
    }

    private Handler parentHandler = new Handler() {

        public void handleMessage(Message msg) {
            ToolUtil.hidePopLoading();
            switch (msg.what) {

                case MyTask.ERROR:
                    ToastUtil.showToast(getContext(), msg.obj.toString(), Toast.LENGTH_LONG);
                    break;

                case MyTask.UP_LOAD_FILE:

                    File dir = new File(Constant.PIC_DIR);
                    File[] pics = dir.listFiles();
                    for (int i = 0; i < pics.length; i++) {
                        pics[i].delete();
                    }

                    ToastUtil.showToast(getContext(), "保存成功!", Toast.LENGTH_LONG);
                    ((FuContentActivity) getActivity()).goToPrePage();
                    break;

            }
        }
    };

}
