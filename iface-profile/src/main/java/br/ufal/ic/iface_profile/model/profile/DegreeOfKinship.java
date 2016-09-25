package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="degree_of_kinship")
public class DegreeOfKinship {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Gender gender;
	
	private String degreeOfKinship;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDegreeOfKinship() {
		return degreeOfKinship;
	}

	public void setDegreeOfKinship(String degreeOfKinship) {
		this.degreeOfKinship = degreeOfKinship;
	}
	
}
