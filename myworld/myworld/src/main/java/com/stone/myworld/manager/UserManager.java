package com.stone.myworld.manager;

import com.stone.myworld.bean.User;
import com.stone.myworld.dal.UserDal;

public class UserManager {
	
	private static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public void create(User user) {
		user.setId(UserDal.getInstance().create(user));
	}
	
	public User getById(Integer userId) {
		User user = null;
		try {
			user = UserDal.getInstance().getById(userId);
		} catch (Exception e) {
			
		}
		return user;
	}
}
