package com.xxx.springboot.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbApplicationTest

{
	@Autowired
	private UserDao userDao;

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setId(2l);
		user.setUserName("张飞");
		user.setPassword("123456");
		userDao.saveUser(user);
	}

	@Test
	public void findUserByUserName() {
		User user = userDao.findUserByUserName("张飞");
		System.out.println("user is " + user);
	}

	@Test
	public void updateUser() {
		User user = new User();
		user.setId(2l);
		user.setUserName("关羽");
		user.setPassword("654321");
		userDao.updateUser(user);
	}

	@Test
	public void deleteUserById() {
		userDao.deleteUserById(2l);
	}
}
