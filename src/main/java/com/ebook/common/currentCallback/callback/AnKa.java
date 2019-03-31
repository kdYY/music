package com.ebook.common.currentCallback.callback;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Component
public class AnKa implements currentCallback {
	
	/**
	 * 具体的service 无需关注
	 */

	//记录日志
	private final static Logger logger = LoggerFactory.getLogger(AnKa.class);
	
	//保证严格按照用户上传的时间戳执行任务
	private static volatile BlockingQueue<Task> queue;	
	
	//容量为1000的queue
	private final int DEFAULT_CAPACITY = 1000;
	
	//记录自启动以来已经加载的所有任务数量
	private static AtomicInteger count = new AtomicInteger();
	
	//单线程
	private ExecutorService threadPool;
	
	/**
	 * 使用spring ioc 构造后调用该方法
	 * 使用有界的ArrayBlockingQueue，创建自定义线程池，指定拒绝策略，方便以后维护，同时启动任务执行队列
	 * 在容器中生成一个单例的taskCurrentlist
	 * @throws Exception
	 */
	@PostConstruct
	private void init() throws Exception{
		queue = new ArrayBlockingQueue<>(DEFAULT_CAPACITY);
		//创建线程池
		threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 
				Runtime.getRuntime().availableProcessors() * 2, 
				0, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(512), // 使用有界队列，避免OOM
				new ThreadPoolExecutor.DiscardPolicy());  //什么也不做，直接忽略
		//启动线程
		this.start();
	}
	
	//非阻塞方法
	public boolean addTask(Task task){
		if(getCurrentTaskCount() >= this.DEFAULT_CAPACITY){
			logger.error("任务队列经常性爆满，需要升级机器");
			return false;
		}
		if(queue.offer(task)){
			count.incrementAndGet();
			return true;
		}
		return false;
	} 
	
	//获取任务总数
	public int getCurrentTaskCount(){
		return queue.size();
	}
	
	//获取历史任务总数
	public int getAllTaskCount(){
		return count.get();
	}
	
	//添加任务
	public boolean putTask(Task task) throws InterruptedException{
		//任务队列放不下，拒绝放入
		if(getCurrentTaskCount() >= this.DEFAULT_CAPACITY){
			logger.error("任务队列经常性爆满，需要升级机器");
			return false;
		}
		queue.put(task);
		count.incrementAndGet();
		return true;
	}
	
	//阻塞方法
	public void takeTask() throws InterruptedException{
		queue.take();
	}
	
	/**
	 * 启动执行
	 */
	private void start(){
		threadPool.submit(new currentTask(queue, AnKa.this));
	}
	
	
	@Override
	public void callback(Task task1) throws Exception {
		try {
			logger.debug("task"+task1.getTaskNum()+"finished");
			//修改任务状态
			task1.setTaskState(TaskState.FINISHED);
			//service.saveTaskFinised();
		} catch (Exception e) {
			logger.error("任务为"+task1.getTaskNum()+"时候，回调出错");
			task1.setTaskState(TaskState.ERROR);
			e.printStackTrace();
		}
		
		SongTask task = (SongTask) task1;
		
		System.out.println("握草我被回调了?????");
		//任务运行完毕发送邮箱,将结果图放入数据库
		/**/
		try {
			//修改任务状态
			task.setTaskState(TaskState.FINISHED);
			//service.updateState(task);
			//mailUtil.sendTemplateMail(task.getUid()+"/"+task.getStateuuid(), task.getEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("任务进行中出错");
			e.printStackTrace();
		}
	}

	

	
	
	
}
