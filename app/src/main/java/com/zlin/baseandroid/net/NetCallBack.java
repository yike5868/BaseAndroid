package com.zlin.baseandroid.net;


import com.zlin.baseandroid.control.FuResponse;

public interface NetCallBack {

	void cancel(int taskId);

	void loadData(int taskId, FuResponse rspObj);

	void netError(int taskId, String msg);
}
