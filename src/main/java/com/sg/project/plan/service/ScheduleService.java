package com.sg.project.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.project.plan.model.Project;
import com.sg.project.plan.model.Task;
import com.sg.project.plan.model.TaskStatusEnum;

/**
 * Service that contains business logic for create, view and calculate 
 * @author sydney.garcia
 */
public class ScheduleService {
	
	static final String SUB_MENU_HEADER_MESSAGE_PROJECT_COUNT = " => Enter number of projects.";
	static final String SUB_MENU_HEADER_MESSAGE_PROJECT_NAME = "\n => [PROJECT ID: %s] PROJECT NAME";
	static final String SUB_MENU_HEADER_MESSAGE_TASK_COUNT = " => Enter number of tasks.";
	static final String SUB_MENU_HEADER_MESSAGE_TASK_NAME = "|+++ [TASK ID: %s] TASK NAME";
	static final String SUB_MENU_HEADER_MESSAGE_TASK_DURATION = "|=++++ [TASK ID:%s] TASK DURATION IN DAY(s)";
	static final String SUB_MENU_HEADER_MESSAGE_TASK_DEPENDENCIES = "|=++++++ [TASK ID: %s]: %s TASK DEPENDENCIES (Task ID only)";
	static final String SUB_MENU_HEADER_MESSAGE_ADDTASK_DEPENDENCY = "|=++++++ [TASK ID: %s]: %s - Add Number of Task Dependencies";
	static final String ERROR_MESSAGE_SAME_DEPENDENCY_TASK_ID = "[ADMIN] Dependency should not be the same with the parent id. Skipped.\n";
	static final String ERROR_MESSAGE_DEPENDENCY_TASK_NOT_FOUND = "[ADMIN] Task not found for the id number [%s]. Skipped. \n";
	static final String ERROR_MESSAGE_DEPENDENCY_EXCEED = "[ADMIN] %s task can be added only. \n";
	static final String ERROR_MESSAGE_EMPTY_PROJECT_PLANS = "[ADMIN] Hey! Create project plans! \n";
	static final String SUCCESS_MESSAGE_PROJECT_PLAN_CREATED = "[Project ID: %s] %s Successfully Created. \n";
	
	/**
	 * To create a project task and dependencies
	 * @param scanner
	 * @return Project list 
	 */
	public static List<Project> createPlan(Scanner scanner) {
		int projectCount = InputService.readNumber(scanner, SUB_MENU_HEADER_MESSAGE_PROJECT_COUNT);
		List<Project> projects = new ArrayList<Project>();
		
		if(InputService.isZeroValue(projectCount)) {
			return null;
		}
		
		for(int pc=1; pc <= projectCount; pc++) {
			String projectName = InputService.readText(scanner, String.format(SUB_MENU_HEADER_MESSAGE_PROJECT_NAME, pc));
			LocalDate projectStartDate = LocalDate.now();
			int taskCount = InputService.readNumber(scanner, SUB_MENU_HEADER_MESSAGE_TASK_COUNT);
			
			if(InputService.isZeroValue(taskCount)) {
				return null;
			}
			
			List<Task> tasks = new ArrayList<Task>();
			Map<Integer, Task> taskMap = new HashMap<>();
			
			for(int tc=1; tc <= taskCount; tc++) {
				String taskName = InputService.readText(scanner, 
						String.format(SUB_MENU_HEADER_MESSAGE_TASK_NAME, tc));
				short taskDuration = (short) InputService.readNumber(scanner, 
						String.format(SUB_MENU_HEADER_MESSAGE_TASK_DURATION, tc));
			
				Task task = new Task(tc, taskName, taskDuration, TaskStatusEnum.NOT_STARTED);
				tasks.add(task);
				taskMap.put(tc, task);
			}
			
			if(tasks.size() > 1) {
				for (Task task: tasks) {
					int dependencyCount = InputService.readNumber(scanner, 
							String.format(SUB_MENU_HEADER_MESSAGE_ADDTASK_DEPENDENCY,task.getId(), task.getName()));
					
					if(InputService.isZeroValue(dependencyCount)) {
						continue;
					}
					
					if(dependencyCount >= tasks.size()) {
						System.out.printf(ERROR_MESSAGE_DEPENDENCY_EXCEED, tasks.size()-1);
						dependencyCount = InputService.readNumber(scanner, 
								String.format(SUB_MENU_HEADER_MESSAGE_ADDTASK_DEPENDENCY,task.getId(), task.getName()));
						
					}
					
					for (int dc= 1; dc<=dependencyCount; dc++) {
						int taskID = InputService.readNumber(scanner, 
								String.format(SUB_MENU_HEADER_MESSAGE_TASK_DEPENDENCIES,task.getId(), task.getName()));
						
						if (taskID == task.getId()) {
							System.out.println(ERROR_MESSAGE_SAME_DEPENDENCY_TASK_ID);
							continue;
						}
						
						Task taskDependency = taskMap.get(taskID);
						
						if(taskDependency != null) {
							task.addTaskDependency(taskDependency);
						}else {
							System.out.printf(ERROR_MESSAGE_DEPENDENCY_TASK_NOT_FOUND,taskID);
						}
					}
					
				}				
			}
			
			for (Task task: tasks) {
				addTaskDate(projectStartDate, task);
			}
			
			Project project = new Project(pc, projectName, projectStartDate, tasks);
			projects.add(project);
			
			System.out.printf(SUCCESS_MESSAGE_PROJECT_PLAN_CREATED, pc, projectName);
		}
		
		return projects;
	}
	
	/**
	 * To print a view for the project plans
	 * @param projects contains the task and schedules
	 */
	public static void viewPlan(List<Project> projects) {
		
		if(InputService.isZeroValue(projects.size())) {
			System.out.printf(ERROR_MESSAGE_EMPTY_PROJECT_PLANS);
		}
	
		projects.stream().forEach(System.out::println);
	}
	
	/**
	 * To add dates on task based on task duration
	 * @param startDate project start date
	 * @param task task
	 */
	private static void addTaskDate(LocalDate startDate, Task task) {
		if(task.getStartDate() != null) {
			return;
		}
		LocalDate start = startDate;
		
		for(Task taskDependency: task.getTaskDependencies()) {
			addTaskDate(start, taskDependency);
			
			LocalDate candidate = taskDependency.getEndDate().plusDays(1);
			if(candidate.isAfter(start)) {
				start = candidate;
			}
		}
		
		task.setStartDate(start);
		task.setEndDate(start.plusDays(task.getDuration() - 1));
	}
}
