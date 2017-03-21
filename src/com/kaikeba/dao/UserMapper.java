package com.kaikeba.dao;

import java.util.List;

import com.kaikeba.entity.User;

public interface UserMapper {

	/**
	 * 根据id查询user数据
	 */
	User getUserById(int id);
	
	/**
	 * 根据name模糊查询user列表
	 */
	List<User> getUserListByName(String name);
}
