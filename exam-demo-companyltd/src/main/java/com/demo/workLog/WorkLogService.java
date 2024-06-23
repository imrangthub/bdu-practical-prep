package com.demo.workLog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.user.UserEntity;

@Service
public class WorkLogService {

	@Autowired
	private WorkLogRepository workLogRepository;

	public List<WorkLogEntity> findAll() {
		return workLogRepository.findAll();
	}

	public Optional<WorkLogEntity> findById(Integer id) {
		return workLogRepository.findById(id);
	}

	public WorkLogEntity save(UserEntity user, WorkLogEntity workLog) {
		workLog.setEmpid(user.getId());
		workLog.setApproved(0);
		
		return workLogRepository.save(workLog);
	}
	
	public WorkLogEntity update(UserEntity user, WorkLogEntity workLog) {
		workLog.setSupervisorid(user.getId());
		
		return workLogRepository.save(workLog);
	}

	public void deleteById(Integer id) {
		workLogRepository.deleteById(id);
	}
}
