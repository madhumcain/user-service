package com.dailycode.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycode.user.VO.Department;
import com.dailycode.user.VO.ResponsibleTemplateVO;
import com.dailycode.user.entity.User;
import com.dailycode.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository  userRepository;

	@Autowired
	RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponsibleTemplateVO getUserWithDepartment(Long userId) {
		ResponsibleTemplateVO vo = new ResponsibleTemplateVO();
		User user = userRepository.findByUserId(userId);
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(),Department.class);
		vo.setDepartment(department);
		vo.setUser(user);
		
		return vo;
	}
	
}
