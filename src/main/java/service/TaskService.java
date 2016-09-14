package service;

import java.util.List;

import model.Task;
import model.User;


public interface TaskService {
	
	List<Task> findAllTasks();
	
	List<Task> findTasksForUser(User user);
	
	List<Task> findTaskByNameContaining(String namePart);
	
	Task findTaskById(Integer id);
	
	Task save(Task task);
	
	void deleteTask(Task task);
	

	
	

}
