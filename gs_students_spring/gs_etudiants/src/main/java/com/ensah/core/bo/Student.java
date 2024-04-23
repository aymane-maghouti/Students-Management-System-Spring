package com.ensah.core.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "This field is required")
	private String nom; 
	
	@NotBlank(message = "This field is required")
	private String prenom;
	
	@NotBlank(message = "This field is required")
	@Pattern(regexp = "^[A-Z]{1}[0-9]{9}", message= "The CNE must be 1 upper letter followed by  9 digits")
	@Column(unique = true)
	private String cne;
	
	

	@NotBlank(message = "This field is required")
	private String gender;
	
	@NotBlank(message = "This field is required")
	@Email(message = "Enter a valid email")
	@Column(unique = true)
	private String email;
	
	
	@NotBlank(message = "This field is required")
	private String addresse;
	
	@NotBlank(message = "This field is required")
	private String Level;

	
	
	
	
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getCne() {
		return cne;
	}


	
	public String getPrenom() {
		return prenom;
	}


	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}


	public String getAddresse() {
		return addresse;
	}

	public String getLevel() {
		return Level;
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCne(String cne) {
		this.cne = cne;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public void setLevel(String level) {
		this.Level = level;
	}
	
	
	

}
