package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "description", nullable = true)
	String description;
	
	@Enumerated(EnumType.STRING)
	TaskStatus status;
	
	@OneToMany(mappedBy="task", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	List<StatusChange> statusChanges;
	
	@ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
	User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public List<StatusChange> getStatusChanges() {
		return statusChanges;
	}

	public void setStatusChanges(List<StatusChange> statusChanges) {
		this.statusChanges = statusChanges;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
