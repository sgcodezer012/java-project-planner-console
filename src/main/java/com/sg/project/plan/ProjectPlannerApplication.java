package com.sg.project.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.sg.project.plan.model.Project;
import com.sg.project.plan.service.InputService;
import com.sg.project.plan.service.ScheduleService;

/**
 * Application Controller for the Project Planner
 * @author sydney.v.garcia
 */
public class ProjectPlannerApplication {

     static final int MENU_CREATE_PLAN = 1;
	 static final int MENU_VIEW_PLAN = 2;
	 static final int MENU_EXIT = 3;
	 static final String MENU_MESSAGE_INVALID_INPUT = "[ADMIN] Please enter correct value.\n";
	 static final String MENU_MESSAGE_EXCEPTION = "[ADMIN] Error occur. Please re-run the application.";
	 static final String MENU_MESSAGE_HEADER = "========= PROJECT PLAN ========";
	 static final String MENU_MESSAGE_FOOTER = "========= GOODBYE! END ========";
	 static final String DISPLAY_MENU_MESSAGE_LIST = """
				\n++++++++++++ MENU +++++++++++++
				[1] Create Project Plans 
				[2] View Scheduled Plans
				[3] Exit
				++++++++++++ MENU +++++++++++++
				""";
	 
	/**
	 * User creates and view project task
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MENU_MESSAGE_HEADER);
		List<Project> projects = new ArrayList<Project>();
		int inputValue = 0;
		
		try (Scanner scanner = new Scanner(System.in)){
			String message = DISPLAY_MENU_MESSAGE_LIST;
			inputValue = InputService.readNumber(scanner, message);
			
			while(inputValue >= -1) {
				// Exit Menu
				if (MENU_EXIT == inputValue) {
					break;
				}
				
				switch(inputValue) {
					case MENU_CREATE_PLAN:
						// Create Project Schedule Plan
						projects = ScheduleService.createPlan(scanner);
						
						if(Objects.isNull(projects)) {
							System.out.println(MENU_MESSAGE_INVALID_INPUT);
							break;
						}
						
					case MENU_VIEW_PLAN:
						// View project plans
						ScheduleService.viewPlan(projects);
						break;
					default:
						System.out.println(MENU_MESSAGE_INVALID_INPUT);
				}
							
				inputValue = InputService.readNumber(scanner, message);
			}
			
		} catch (Exception ex) {
			System.out.println(MENU_MESSAGE_EXCEPTION);
		} finally {
			System.out.println(MENU_MESSAGE_FOOTER);
		}	
	}

}
