package com.intitude_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intitude_manage.model.Trainer;
import com.intitude_manage.repository.Trainer_Repository;

@Service
public class Trainer_Service {
	
	@Autowired
	private Trainer_Repository trainer_repo;
	
	
//getAllTrainer
	
	public List<Trainer> getAllTariner()
	{
		return trainer_repo.findAll();
	}

	

//getTarinerById
	
	public Optional<Trainer> getTarinerById(Long id)
	{
		return Optional.ofNullable(trainer_repo.findById(id).orElseThrow());
	}
	
	
	
//saveTrainer
	
	public Trainer addTrainer(Trainer trainer)
	{
		return trainer_repo.save(trainer);
	}
	
//deleteTrainerById
	
	public void deleteTrainerById(Long id)
	{
		trainer_repo.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
