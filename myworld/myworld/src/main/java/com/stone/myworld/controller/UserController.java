package com.stone.myworld.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stone.myworld.bean.User;
import com.stone.myworld.controller.request.LoginRequest;
import com.stone.myworld.controller.request.SigninRequest;
import com.stone.myworld.controller.response.StoneResponce;
import com.stone.myworld.manager.UserManager;
import com.stone.myworld.util.Constant;

@RestController("UserController")
@RequestMapping(value = "/user")
public class UserController{
	
	@PostMapping(value = "/login")
	public StoneResponce<String> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
		StoneResponce<String> responce = new StoneResponce<>();
		if (!loginRequest.valid()) {
			responce.setHr(Constant.FAILURE);
			return responce;
		}
		User user = UserManager.getInstance().getById(loginRequest.getUserId());
		if (user == null) {
			responce.setHr(Constant.USER_NOT_EXISTS);
			responce.setMessage("该用户不存在");
			return responce;
		}
		if (!loginRequest.getPassword().equals(user.getPassword())) {
			responce.setHr(Constant.ERROR_PASSWORD);
			responce.setMessage("密码错误");
			return responce;
		}
		responce.setHr(Constant.SUCCESS);
		responce.setMessage("登陆成功");
		responce.setData(genToken(user.getId()));
		return responce;
	}
	
	private String genToken(Integer userId) {
		Algorithm algorithm = Algorithm.HMAC256(Constant.TOKEN_KEY);
		return JWT.create().withJWTId(userId.toString()).sign(algorithm);
	}
	
	@PostMapping(value = "/signin")
	public StoneResponce<String> signin(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SigninRequest signinRequest) {
		StoneResponce<String> responce = new StoneResponce<>();
		if (signinRequest.valid()) {
			responce.setHr(Constant.FAILURE);
			return responce;
		}
		User user = new User();
		user.setDisplayName(signinRequest.getNickName());
		user.setPassword(signinRequest.getPassword());
		UserManager.getInstance().create(user);
		responce.setHr(Constant.SUCCESS);
		responce.setMessage("注册成功");
		responce.setData(genToken(user.getId()));
		return responce;
	}
}
