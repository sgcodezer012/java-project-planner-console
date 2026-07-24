package com.sg.project.plan.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Project POJO
 * @author sydney.v.garcia
 */
public class Project {
	private int id;
	private String name;
	private LocalDate startDate;
	private List<Task> tasks;

	public Project(int id, String name, LocalDate startDate, List<Task> tasks) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.tasks = tasks;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("\n [Project Name: %s | Start Date: %s | Total Tasks: %s] ",
				this.getName(), 
				this.getStartDate(),
				this.getTasks().size())).append("\n");
		
		this.getTasks().forEach(task -> 
			 builder.append(" + ")
			 		.append(task)
			 		.append("\n"));
	
		return builder.toString();
	}

}
