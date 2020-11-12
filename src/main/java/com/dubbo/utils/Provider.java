package com.dubbo.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.IOException;

/**
 * 所有需要在进程关闭之前清理资源的Bean，都需按下列方法之一实现相应资源清理接口.否则程序数据会出现不一致等情况.
 * 
 * 第一种：通过 @PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作. 第二种是：通过在xml中定义
 * init-method 和 destory-method方法. 第三种是：
 * 通过bean实现InitializingBean和DisposableBean接口.
 * 
 * @author Cater.Tian
 * 
 */
public class Provider extends Thread implements SignalHandler {

	private ClassPathXmlApplicationContext appContext = null;

	private SignalHandler oldHandler;

	private boolean stoped = false;

	public static void main(String[] args) throws IOException {
		Provider provider = new Provider();
		provider.init();
		provider.startup();
	}

	public void init() {
		Signal sigTERM = new Signal("TERM"); // kill -15
		this.oldHandler = Signal.handle(sigTERM, this);

		Signal sigINT = new Signal("INT"); // CTRL-C
		this.oldHandler = Signal.handle(sigINT, this);
	}

	public boolean startup() {
		//System.getProperties().setProperty("HADOOP_USER_NAME", "userb21042c79426");
		appContext = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		// appContext.registerShutdownHook();
		appContext.start();

		if (appContext.isRunning()) {
			System.out.println("ShopMgr-service is running...");
			while (!this.stoped) {
				try {
					Thread.sleep(3 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("ClassPathXmlApplicationContext closed, application exit now . . . ");
			return true;
		} else {
			System.out.print("ShopMgr-service start failed!");
			System.exit(-1);
			return false;
		}
	}

	public void close() {
		if (appContext.isRunning())
			appContext.close();
	}

	@Override
	public void handle(Signal signal) {
		System.out.println("signal " + signal.getName() + " received. close ClassPathXmlApplicationContext now ...");
		this.close();

		this.oldHandler.handle(signal);
		this.stoped = true;
	}
}
