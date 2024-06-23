package com.demo.supervisor;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.base.BaseService;
import com.demo.userRole.UserRolesService;

@Service
@Transactional
public class SupirvisorService extends BaseService {

	@Autowired
	private UserRolesService userRolesService;

}
