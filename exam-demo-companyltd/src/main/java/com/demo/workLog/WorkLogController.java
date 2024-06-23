package com.demo.workLog;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.user.UserEntity;
import com.demo.user.UserService;

@Controller
public class WorkLogController {

	@Autowired
	private WorkLogService workLogService;
	
	
	@Autowired
	private UserService userService;

	@GetMapping("/worklog/create")
	public String createWorkLog(Model model) {
		WorkLogEntity workLog = new WorkLogEntity();
		model.addAttribute("workLog", workLog);
		
		
		model.addAttribute("currentUser", userService.getUserInof());
		return "worklog/create";
	}

	@GetMapping
	public List<WorkLogEntity> getAllWorkLogs() {
		return workLogService.findAll();
	}

	@GetMapping("/worklog/apprived/{id}")
	public String getWorkLogById(@PathVariable Integer id, Model model) {
		Optional<WorkLogEntity> workLog = workLogService.findById(id);
		WorkLogEntity obj = workLog.get();
		obj.setApproved(1);
		UserEntity user = userService.getUserInof();
		
		 workLogService.update(user, obj);
		
			List<WorkLogEntity> workLogList = workLogService.findAll();
			for (WorkLogEntity logObj :workLogList) {
				UserEntity userObj = userService.findById(logObj.getEmpid());
				if(userObj != null) {
					logObj.setEmpName(userObj.getFullName());
				}
			}
			model.addAttribute("workLogList", workLogList);
			
			model.addAttribute("currentUser", userService.getUserInof());
			return "supervisor/home";
	}
	
	@GetMapping("/worklog/reject/{id}")
	public String reject(@PathVariable Integer id, Model model) {
		Optional<WorkLogEntity> workLog = workLogService.findById(id);
		WorkLogEntity obj = workLog.get();
		obj.setApproved(0);
		UserEntity user = userService.getUserInof();
		
		 workLogService.update(user, obj);
		
			List<WorkLogEntity> workLogList = workLogService.findAll();

			for (WorkLogEntity logObj :workLogList) {
				UserEntity userObj = userService.findById(logObj.getEmpid());
				if(userObj != null) {
					logObj.setEmpName(userObj.getFullName());
				}
			}
			
			model.addAttribute("workLogList", workLogList);
			
			model.addAttribute("currentUser", userService.getUserInof());
			return "supervisor/home";
	}

	
	@PostMapping("/worklog/save")
	public String createWorkLog(@RequestParam("hourRate") Integer hourRate, @RequestParam("workedHours") Integer workedHours, HttpSession session, Model model) {

		WorkLogEntity workLog = new WorkLogEntity();
		model.addAttribute("workLog", workLog);
		UserEntity user = userService.getUserInof();
		
		model.addAttribute("currentUser", userService.getUserInof());
		
		workLog.setEmpid(user.getId());
		workLog.setWorkedHours(workedHours);
		workLog.setHourRate(hourRate);
		
		 workLogService.save(user, workLog);
		 
		
		return "worklog/create";
		
	}
	
	

}
