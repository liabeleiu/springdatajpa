package repository;

import java.util.List;

import model.User;

public interface UserRepository extends BaseRepository<User, Long> {
	
	List<User> findByNameContainingAndSurnameContainingAndUsernameContaining(String name,String surname, String userName);
	User findById(Integer id);
	User findByUsername(String username);
}
