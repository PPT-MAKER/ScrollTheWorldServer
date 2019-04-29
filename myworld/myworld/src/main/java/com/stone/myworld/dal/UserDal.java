package com.stone.myworld.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.stone.myworld.bean.User;

public class UserDal extends DalBase {
	private static UserDal instance = new UserDal();
	
	public static UserDal getInstance() {
		return instance;
	}
	
	public Integer create(User user) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("in_phone_num", user.getPhoneNum());
		parameters.put("in_display_name", user.getDisplayName());
		parameters.put("in_password", user.getPassword());
		parameters.put("in_gender", user.getGender());
		parameters.put("in_address", user.getAddress());
		parameters.put("in_photo_image_id", user.getPhotoImageId());
		parameters.put("in_email", user.getEmail());
		return (Integer) execute("user_create", parameters).get("out_id");
	}
	
	public List<User> getAll() {
		return executeReturnList("user_get_all", null, new UserMapper());
	}
}

class UserMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setPhoneNum(rs.getString("phone_num"));
		user.setDisplayName(rs.getString("display_name"));
		user.setPassword(rs.getString("password"));
		user.setGender(rs.getString("gender"));
		user.setAddress(rs.getString("address"));
		user.setPhotoImageId(rs.getInt("photo_image_id"));
		user.setEmail(rs.getString("email"));
		user.setCreatedTime(rs.getDate("created_time"));
		user.setUpdatedTime(rs.getDate("updated_time"));
		return user;
	}
}
