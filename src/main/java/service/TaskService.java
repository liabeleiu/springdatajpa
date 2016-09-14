package service;

import java.util.List;

import model.Task;
import model.TaskStatus;
import model.User;


public interface TaskService {
	
	List<Task> findAllTasks();
	
	List<Task> findTasksForUser(User user);
	
	List<Task> findTaskByNameContainingAndStatusInAndUser(String namePart, List<TaskStatus> statusList, User user);
	
	Task findTaskById(Integer id);
	
	Task save(Task task);
	
	void deleteTask(Task task);
	

	
	

}
