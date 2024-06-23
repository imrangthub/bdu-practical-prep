package com.demo.workLog;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.ToString;

@Entity
@ToString
@Table(name = "work_log")
public class WorkLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "empid", nullable = false)
	private Long empid;

	@Column(name = "supervisorid")
	private Long supervisorid;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "worked_hours")
	private Integer workedHours;

	@Column(name = "hour_rate")
	private Integer hourRate;

	@Column(name = "approved")
	private Integer approved;
	
	
	  @Transient
	  private String empName;
	  
	  
	  
	  
	  
	  

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public Long getSupervisorid() {
		return supervisorid;
	}

	public void setSupervisorid(Long supervisorid) {
		this.supervisorid = supervisorid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(Integer workedHours) {
		this.workedHours = workedHours;
	}

	public Integer getHourRate() {
		return hourRate;
	}

	public void setHourRate(Integer hourRate) {
		this.hourRate = hourRate;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}
}
