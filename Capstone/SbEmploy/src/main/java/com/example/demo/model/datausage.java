package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="datausage")
public class datausage {

    @Id
	private int usageId;
	private int userPlanId;
	private int userId;
	private double dataUsed;
	private int smsUsed;
	private int callsUsed;
	private Date recordedDate;
}
