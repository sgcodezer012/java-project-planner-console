# Project Planner (Java) Console

# Overview 
A java console application that calculates the calendar schedules for project plans. </br>
It allows users to create multiple project plans, defined tasks with duration and dependencies.</br>
Generates the start and end dates for each task based on the dependency relationships and duration.</br>

## Requirement
Each project plan consists of one or more tasks.</br>
Each project has:
1. Project ID
2. Project Name
3. Start Date
4. List of Task
   
Each task has:
1. Task ID
2. Task Name
3. Duration (in days)
4. Zero or more task dependencies
5. Start and End Dates
6. Status

## Features and Assumptions
1. Console-based user interface.
2. Create multiple project plans.
3. Create multiple tasks for each project
4. Assign task durations and dependencies
5. Calculate task start and end dates
6. Print generated schedules.
7. Input validation and accepts only numeric, text and space.
8. Not accepting special characters
9. All task durations are positive integers
10. Project start date is used as the initial task date
11. Tasks without dependencies begin immediately on the project start date.
12. Task begins the next day after tasks completed.
13. Dependencies refer to task IDs within the same project
14. Each task belongs to only one project.
15. Other project plan has no dependencies to other project and only task has dependencies

## Technology Used
1. Java 25 - Standard Edition
2. Eclipse IDE version 2026-06 (4.40.0)

## Project Structure
```shell
│ src
│  ├── ProjectPlannerApplication.java
│  ├── [ model ]
│    ├── Project.java
│    ├── Task.java
│    └── TaskStatusEnum.java
│  ├── [ service ]
│    ├── InputService.java
│    └── ScheduleService.java
```
## Sample Input:
```shell
========= PROJECT PLAN ========

++++++++++++ MENU +++++++++++++
[1] Create Project Plans
[2] View Scheduled Plans
[3] Exit
++++++++++++ MENU +++++++++++++

	 Enter value here: ###
 => Enter number of projects.
	 Enter value here: ###

 => [PROJECT ID: 1] PROJECT NAME
	 Enter value here: ###
 => Enter number of tasks.
	 Enter value here: ###
|+++ [TASK ID: 1] TASK NAME
	 Enter value here: ###
|=++++ [TASK ID:1] TASK DURATION IN DAY(s)
	 Enter value here: ###
```

## Sample Output:

### Single Project
```shell
[Project ID: 1] Project Eureka Successfully Created. 

 [Project Name: Project Eureka | Start Date: 2026-07-24 | Total Tasks: 1] 
 + Task Name: Build | Days: 4 | Start Date: 2026-07-24 - End Date: 2026-07-27 | 
++ Status: NOT_STARTED | Number of Dependency: 0 | Dependencies on: 
```

### Single Project with Multiple Task and Dependencies
```shell
[Project ID: 1] Project Meta Successfully Created. 

 [Project Name: Project Meta | Start Date: 2026-07-24 | Total Tasks: 2] 
 + Task Name: Build | Days: 2 | Start Date: 2026-07-24 - End Date: 2026-07-25 | 
++ Status: NOT_STARTED | Number of Dependency: 0 | Dependencies on: 
 + Task Name: Testing | Days: 3 | Start Date: 2026-07-26 - End Date: 2026-07-28 | 
++ Status: NOT_STARTED | Number of Dependency: 1 | Dependencies on: [1:Build]
```

### Multiple Project with Multiple Task and Dependencies
```shell
 [Project Name: Project X | Start Date: 2026-07-24 | Total Tasks: 3] 
 + Task Name: Requirements | Days: 2 | Start Date: 2026-07-24 - End Date: 2026-07-25 | 
++ Status: NOT_STARTED | Number of Dependency: 0 | Dependencies on: 
 + Task Name: Build | Days: 3 | Start Date: 2026-07-26 - End Date: 2026-07-28 | 
++ Status: NOT_STARTED | Number of Dependency: 1 | Dependencies on: [1:Requirements]
 + Task Name: Testing | Days: 2 | Start Date: 2026-07-29 - End Date: 2026-07-30 | 
++ Status: NOT_STARTED | Number of Dependency: 2 | Dependencies on: [1:Requirements][2:Build]


 [Project Name: Project Meta | Start Date: 2026-07-24 | Total Tasks: 2] 
 + Task Name: Build | Days: 3 | Start Date: 2026-07-28 - End Date: 2026-07-30 | 
++ Status: NOT_STARTED | Number of Dependency: 1 | Dependencies on: [2:Testing]
 + Task Name: Testing | Days: 4 | Start Date: 2026-07-24 - End Date: 2026-07-27 | 
++ Status: NOT_STARTED | Number of Dependency: 0 | Dependencies on: 

```

### User error input
```shell
├── User viewing from missing project plans
++++++++++++ MENU +++++++++++++

	 Enter value here:2
[ADMIN] Hey! Create project plans!

├── User input value not in option
++++++++++++ MENU +++++++++++++

	 Enter value here:4
[ADMIN] Please enter correct value.

├── User input value on integer
 => Enter number of projects.
	 Enter value here:p
Only numbers[1-9] is allowed.

├── User incorrect input value on tasks
 => Enter number of tasks.
	 Enter value here:0
[ADMIN] Please enter correct value.

├── User incorrectly adding dependencies from allowable number of task created.
|=++++++ [TASK ID: 3]: Build - Add Number of Task Dependencies
	 Enter value here:3
[ADMIN] 2 task can be added only.

├── User inputted value with the same task id on dependency
|=++++++ [TASK ID: 3]: Build TASK DEPENDENCIES (Task ID only)
	 Enter value here:3
[ADMIN] Dependency should not be the same with the parent id. Skipped.

├── User adding dependencies which is not existing 
|=++++++ [TASK ID: 2]: Testing - Add Number of Task Dependencies
	 Enter value here:3
[ADMIN] 1 task can be added only. 
|=++++++ [TASK ID: 2]: Testing - Add Number of Task Dependencies
	 Enter value here:1
|=++++++ [TASK ID: 2]: Testing TASK DEPENDENCIES (Task ID only)
	 Enter value here:4
[ADMIN] Task not found for the id number [4]. Skipped. 
```
