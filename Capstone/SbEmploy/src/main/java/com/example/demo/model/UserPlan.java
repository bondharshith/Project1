package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Userplan")
public class UserPlan {
	
	@Id
	private int userPlanId;
	private int userId;
	private int planId;
	private String planStartDate;
	private String planEndDate;
	private double remainingDataInGb;
	private int remainingSms;
	private double remainingTalkTime;
	
	

}
