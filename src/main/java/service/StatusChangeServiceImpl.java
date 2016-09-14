package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.StatusChange;
import repository.StatusChangeRepositoy;

@Service
public class StatusChangeServiceImpl implements StatusChangeService{

	@Autowired
	StatusChangeRepositoy statusChangeRepositoy;
	
	@Override
	public StatusChange save(StatusChange statusChange) {
		return statusChangeRepositoy.save(statusChange);
	}

	
}
