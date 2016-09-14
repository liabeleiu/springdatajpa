package controller;

import java.util.List;

import model.Task;

public class UserTasksForm {

	List<Task> tasks;
	
	public UserTasksForm()
	{
		
	}
	
	public UserTasksForm(List<Task> tasks){
		this.tasks = tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
