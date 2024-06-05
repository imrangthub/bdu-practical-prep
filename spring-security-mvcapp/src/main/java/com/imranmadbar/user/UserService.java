package com.imranmadbar.user;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imranmadbar.base.BaseService;
import com.imranmadbar.userRole.UserRolesService;

@Service
@Transactional
public class UserService extends BaseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesService userRolesService;

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity findById(Long id) {
		return userRepository.findById(id);
	}

	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public String saveOrUpdate(UserEntity obj) {
		if (obj.getFullName() == null || obj.getPassword() == null) {
			return "Invlaid user Info";
		}
		try {
			if (obj.getId() == null) {
				userRepository.save(obj);
				return "User save successfully done !";
			}
			userRepository.update(obj);
			return "User update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String userSave(UserEntity obj) {
		if (obj.getFullName() == null || obj.getPassword() == null) {
			return "Invlaid user Info";
		}
		try {
			userRepository.save(obj);
			System.out.println(obj);
			if (obj != null) {
				userRolesService.addUserRole(obj.getId());
			}
			return "User save successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String userUpdate(UserEntity userObj) {
		UserEntity obj = null;
		if (userObj.getFullName() == null || userObj.getPassword() == null) {
			return "Invlaid user Info";
		}
		obj = userRepository.findByIdObj(userObj.getId());
		if (obj == null) {
			return "Data not found !";
		}
		try {
			obj.setFullName(userObj.getFullName());
			obj.setPassword(userObj.getPassword());
			userRepository.update(obj);
			return "User update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

}
