package com.demo.supervisor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.user.UserEntity;
import com.demo.user.UserService;
import com.demo.workLog.WorkLogEntity;
import com.demo.workLog.WorkLogService;

@Controller
public class SupirvisorController {

	@Autowired
	private SupirvisorService userService;
	
	@Autowired
    private WorkLogService workLogService;
	
	@Autowired
	private UserService userService2;

	@GetMapping("/supervisor/home")
	public String index(Model model) {
		model.addAttribute("currentUser", userService.getUserInof());
		
		List<WorkLogEntity> workLogList = workLogService.findAll();
		

		for (WorkLogEntity logObj :workLogList) {
			UserEntity userObj = userService2.findById(logObj.getEmpid());
			if(userObj != null) {
				logObj.setEmpName(userObj.getFullName());
			}
		}
		
		
		model.addAttribute("workLogList", workLogList);
		return "supervisor/home";
	}

}
