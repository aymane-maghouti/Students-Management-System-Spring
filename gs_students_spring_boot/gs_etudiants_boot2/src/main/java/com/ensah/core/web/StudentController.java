package com.ensah.core.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensah.core.bo.Student;
import com.ensah.core.services.IStudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {
	
	@Autowired
	IStudentService iStudentService;
	

	
	private Map<String, String> LevelList = new HashMap<String, String>(); 
	public StudentController() {
		LevelList.put("ID", "ID");
		LevelList.put("GI", "GI");
		LevelList.put("TDIA", "TDIA");
	}

	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		
		model.addAttribute("studentModel", new Student());
		model.addAttribute("levelList", LevelList); // Ajouter la liste des niveaux
		model.addAttribute("studentList",iStudentService.getAllStudents());
		
		return "form";
	}
	
	
	
	@PostMapping("/addStudent")
	public String addOrUpdateStudent(@Valid @ModelAttribute("studentModel") Student st, BindingResult b, Model model) {
		
		if (b.hasErrors()) {
			model.addAttribute("levelList", LevelList); // Ajouter la liste des niveaux
	        model.addAttribute("studentList", iStudentService.getAllStudents());
	        model.addAttribute("errorMsg", "Les données sont invalides.");
	        return "form";
	    } else {
	        if (st.getId() == null) {
	            iStudentService.addStudent(st);
	            model.addAttribute("infoMsg", "L'Etudiant " + st.getPrenom() + " " + st.getNom() +  " est ajouté avec succès");
	        } else {
	    		model.addAttribute("levelList", LevelList); // Ajouter la liste des niveaux
	            iStudentService.updateStudent(st);
	            model.addAttribute("infoMsg", "L'Etudiant " + st.getPrenom() + " " + st.getNom() +  " est mis à jour avec succès");
	        }
	        model.addAttribute("studentModel", new Student());
			model.addAttribute("levelList", LevelList); // Ajouter la liste des niveaux
	        model.addAttribute("studentList", iStudentService.getAllStudents());
	        return "form";
	    }
	}
	
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") Long id,Model model) {
		
		iStudentService.deleteStudent(id);
		
		model.addAttribute("studentModel", new Student());
		model.addAttribute("infoMsg", "the students deleted succ ");
		model.addAttribute("studentList",iStudentService.getAllStudents());
		return "form";
	}
	
	
	@GetMapping("/updateStudentForm/{id}")
	public String updateStudent(@PathVariable("id") Long id,Model model) {
		
		Student s = iStudentService.getStudentById(id);	
		model.addAttribute("studentModel", s);
		model.addAttribute("studentList",iStudentService.getAllStudents());
		model.addAttribute("levelList", LevelList); 

		return "form";
	}
	
	
	
	
	
	
	

}
