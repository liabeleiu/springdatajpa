package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.StatusChange;
import model.Task;
import model.User;
import repository.StatusChangeRepositoy;
import repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskService taskService;
	@Autowired
	StatusChangeRepositoy statusChangeRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findByNameSurnameAndUserName(String name, String surname, String userName) {
		return userRepository.findByNameContainingAndSurnameContainingAndUsernameContaining(name, surname, userName);
	}

	@Override
	public void delete(User deleted) {
		List<Task> userTasks = taskService.findTasksForUser(deleted);
		for(Task task : userTasks){
			task.setUser(null);
			taskService.save(task);
		}
		
		List<StatusChange> statusChangesForUser = statusChangeRepository.findByUser(deleted);
		for(StatusChange change : statusChangesForUser){
			change.setUser(null);
			statusChangeRepository.save(change);
		}
		
		userRepository.delete(deleted);	
	}

	@Override
	public User findById(Integer id) {
		
		return userRepository.findById(id);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	
}
