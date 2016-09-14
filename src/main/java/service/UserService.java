package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import model.Task;
import model.User;

public interface UserService {

	List<User> findAll();
	
	User save(User task);
	
	List<User> findByNameSurnameAndUserName(String name,String surname, String userName);

	@Transactional
	void delete(User deleted);
	
	User findById(Integer id);
	
	User findByUserName(String userName);
}
