package com.xxx.springboot.zk.op;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZkService {

	@Autowired
	private ZkClient zkClient;

	public void create(String path, boolean createParents) {
		zkClient.createPersistent(path, createParents);
	}

	public void delete(String path) {
		zkClient.delete(path, -1);
	}
	
	public List<String> getChildren(String parentPath){
		return zkClient.getChildren(parentPath);
	}
	
	public void setData(String path, Object data) {
//		zkClient.createPersistent(path, data);
		zkClient.writeData(path, data);
		
	}
	public Object getData(String path) {
		return zkClient.readData(path);
	}
	
	public boolean exists(String path) {
		return zkClient.exists(path);
	}

}
