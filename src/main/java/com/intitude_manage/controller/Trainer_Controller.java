package com.intitude_manage.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.intitude_manage.model.Trainer;
import com.intitude_manage.service.Trainer_Service;

@Controller
public class Trainer_Controller {
	
	@Autowired
	private Trainer_Service trainer_ser;
	
	@GetMapping("/allTrainer")
	public String feathTrainers(Model model)
	{
		model.addAttribute("listTrainer", trainer_ser.getAllTariner());
		return "View_Trainer";
	}
	
	
	@GetMapping("/viwe_From")
	public String addNewTrainer(Model model)
	{
		Trainer trainer = new Trainer();
		model.addAttribute("trainer", trainer);
		return "New_Trainer_Form";
	}
	
	
	@PostMapping("/saveTrainer")
	public String saveTrainer(@ModelAttribute("trainer") Trainer trainer)
	{
		trainer_ser.addTrainer(trainer);
		return "redirect:/allTrainer";
	}
	
	
	
	@GetMapping("/updateTrainer/{id}")
	public String updateTrainer(@PathVariable Long id , Model model)
	{
		Trainer trainer = trainer_ser.getTarinerById(id).orElseThrow();
		
		model.addAttribute("trainer_up", trainer);
		return "update_Trainer_From";
	}
	
	@PostMapping("/trainer/{id}")
	public String updateingTrainer(@PathVariable Long id, @ModelAttribute("trainer_up") Trainer trainer)
	{
		trainer.setId(id);
		trainer_ser.addTrainer(trainer);
		return "redirect:/allTrainer";
	}
	
	
	@GetMapping("/deleteTrainerbyId/{id}")
	public String deleteTrainerById(@PathVariable Long id)
	{
		
		trainer_ser.deleteTrainerById(id);
		return "redirect:/allTrainer";
	}
	
	
	
	
	
	
	
	
	

}
