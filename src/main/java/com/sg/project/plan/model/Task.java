package com.sg.project.plan.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Task POJO
 * @author sydney.v.garcia
 */
public class Task {
	private int id;
	private String name;
	private short duration;
	private List<Task> taskDependencies = new ArrayList<>();
	private LocalDate startDate;
	private LocalDate endDate;
	private TaskStatusEnum status;
	
	public Task(int id, String name, short duration, TaskStatusEnum status) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.status = status;
	}

	public List<Task> getTaskDependencies() {
		return taskDependencies;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public TaskStatusEnum getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public short getDuration() {
		return duration;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void addTaskDependency(Task task) {
		this.taskDependencies.add(task);
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(String.format("Task Name: %s | Days: %s | Start Date: %s - End Date: %s | \n++ Status: %s | Number of Dependency: %s | ", 
				this.getName(), 
				this.getDuration(), 
				this.getStartDate(),
				this.getEndDate(),
				this.getStatus(),
				this.getTaskDependencies().size())).append("Dependencies on: ");
	
		this.getTaskDependencies().forEach(task -> 
				builder.append("[" + task.getId())
				    .append(":")
					.append(task.getName() + "]")
					.append(""));
	
		return builder.toString();
	}
}
