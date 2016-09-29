package br.ufal.ic.iface_profile.model.infrastructure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String username;
	
	private boolean haveProfile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHaveProfile() {
		return haveProfile;
	}

	public void setHaveProfile(boolean haveProfile) {
		this.haveProfile = haveProfile;
	}
}
	
	