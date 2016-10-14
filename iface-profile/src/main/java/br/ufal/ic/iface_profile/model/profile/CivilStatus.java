package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="civilstatus")
public class CivilStatus {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToOne
	private Gender gender;
	
	public CivilStatus(){}
	public CivilStatus(String name, Gender gender){
		this.name = name;
		this.gender = gender;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
