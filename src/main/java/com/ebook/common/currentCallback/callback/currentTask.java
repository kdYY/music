package com.ebook.common.currentCallback.callback;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class currentTask<T> implements Runnable{

	//接收的任务队列
	private volatile BlockingQueue<T> queue;
	
	//回调接口
	private currentCallback callback;
	
	//记录日志
	private final static Logger logger = LoggerFactory.getLogger(AnKa.class);
	
	public currentTask(BlockingQueue<T> queue, currentCallback callback){
		this.queue = queue;
		this.callback = callback;
	}

	/**
	 * 指定任务执行流程
	 */
	@Override
	public void run() {
		SongTask task = null;
		while(true){
			try {
				//取不到返回
				task = (SongTask) queue.take();
				System.out.println("获取到一个task"+task.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String path1 = Thread.currentThread().getContextClassLoader().getResource("").toString();
			String path = path1.substring(path1.indexOf("/")+1, path1.indexOf("WEB-INF"));
			
			//图片的地址要稍微处理一下
			String savePath = path.substring(0,path.lastIndexOf("/")+1)+"newpic";
			String saveuuid = UUID.randomUUID().toString();
			String iter = "--iter",content_weight= "--content_weight",style_weight="--style_weight";
			Process pr;
			
			try {
				//dosomething
				// TODO Auto-generated catch block
				//System.out.println("[PERSON_DUBUG]:id="+task.getYid()+",Email="+task.getEmail()+" task has finished!");
				//System.out.println("PERSONDEBUG图片训练进行中... 准备发送到"+task.getEmail());
				
				//业务代码pytask
				try {
					//task.setResultpicname(saveuuid+"_"+task.getYid()+"_at_iteration_"+iterNum+".png");
					//task.setStateuuid(saveuuid);
					//任务执行完毕,进行回调
					callback.callback(task);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(task.toString()+"发送邮箱或者修改状态失败！！");
					e.printStackTrace();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
