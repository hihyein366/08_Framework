package com.kh.test.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.user.model.dto.User;
import com.kh.test.user.model.mapper.UserMapper;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public User selectUser(String userId) {
		return mapper.selectUser(userId);
	}

}
