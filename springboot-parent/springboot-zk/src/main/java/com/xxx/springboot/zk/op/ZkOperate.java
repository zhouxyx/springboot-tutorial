package com.xxx.springboot.zk.op;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkOperate {

	private final static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

	public static void main(String[] args) throws InterruptedException {
		ZkClient zkClient = new ZkClient(connectString, 4000);
		// zkClient一次创建多个节点
		zkClient.createPersistent("/zkclient/zkclient1/zkclient1-1", true);
		System.out.println("创建多层节点成功");

		List<String> list = zkClient.getChildren("/zkclient");
		System.out.println("获取zkclient节点下的子节点：" + list);

		// zkClient删除一个节点,和原生api一样，只能从最底层节点一个一个删除
		// zkClient.delete("/zkclient");
		// zkClient递归删除某个节点及其子节点
		//zkClient.deleteRecursive("/zkclient");
		//System.out.println("删除zkclient及其下面的子节点成功\n");

		// 利用watch机制做订阅,使用异步操作处理节点
		zkClient.subscribeDataChanges("/node", new IZkDataListener() {
			public void handleDataChange(String arg0, Object arg1) throws Exception {
				System.out.println("节点名称：" + arg0 + "-->修改后的值：" + arg1);
			}

			public void handleDataDeleted(String arg0) throws Exception {
				System.out.println("删除节点" + arg0 + "成功");
			}
		});
		
		
		zkClient.createPersistent("/node", "node");
		TimeUnit.SECONDS.sleep(2);
		zkClient.writeData("/node", "node1");
		TimeUnit.SECONDS.sleep(2);
		zkClient.delete("/node");
		TimeUnit.SECONDS.sleep(2);
	}
}
