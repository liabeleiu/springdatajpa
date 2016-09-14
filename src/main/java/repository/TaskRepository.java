package repository;

import java.util.List;

import model.Task;
import model.TaskStatus;
import model.User;


public interface TaskRepository extends BaseRepository<Task, Long> {

	List<Task> findByNameContaining(String namePart);
	
	Task findById(Integer id);
	
	List<Task> getTaskByUser(User user);

	List<Task> findTaskByNameContainingAndStatusInAndUser(String namePart, List<TaskStatus> status, User user);
	
}
