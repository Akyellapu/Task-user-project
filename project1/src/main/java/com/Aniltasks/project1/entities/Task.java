package com.Aniltasks.project1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
@Table(name="Tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="taskname",nullable=false)
	private String taskname;
	/**
	 * to fetch less data use lazy type
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="users_id",referencedColumnName="id")
	private User users;
	
	
	
	

}
