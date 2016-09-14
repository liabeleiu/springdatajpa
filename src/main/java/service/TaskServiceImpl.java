package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Task;
import model.TaskStatus;
import model.User;
import repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public List<Task> findAllTasks() {
		return  taskRepository.findAll();
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public List<Task> findTasksForUser(User user) {
		return taskRepository.getTaskByUser(user);
	}

	

	@Override
	public Task findTaskById(Integer id) {
		return taskRepository.findById(id);
	}

	@Override
	public void deleteTask(Task task) {
		taskRepository.delete(task);
		
	}

	@Override
	public List<Task> findTaskByNameContainingAndStatusInAndUser(String namePart, List<TaskStatus> statusList, User user){
		return taskRepository.findTaskByNameContainingAndStatusInAndUser(namePart, statusList, user);
	}

	
	
}
