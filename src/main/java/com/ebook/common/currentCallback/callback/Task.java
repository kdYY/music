package com.ebook.common.currentCallback.callback;

public interface Task {

	//獲取任务编号
	public Integer getTaskNum();
	//獲取任务状态
	public Integer getTaskState();
	//设置任务状态
	public String setTaskState(Enum<?> state);
	
}
