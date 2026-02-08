package com.intitude_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intitude_manage.model.Trainer;

@Repository
public interface Trainer_Repository extends JpaRepository<Trainer,Long>{

}
