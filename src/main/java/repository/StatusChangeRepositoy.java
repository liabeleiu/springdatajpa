package repository;

import java.util.List;

import model.StatusChange;
import model.Task;
import model.User;

public interface StatusChangeRepositoy extends BaseRepository<StatusChange, Long> {

	public List<StatusChange> findByUser(User user);
}
