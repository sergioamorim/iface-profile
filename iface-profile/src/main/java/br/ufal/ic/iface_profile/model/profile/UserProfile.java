package br.ufal.ic.iface_profile.model.profile;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="userprofile")

public class UserProfile {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User user;
	
	private String name;
	
	private int age;
	
	private String picture;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@ManyToOne
	private Link homeTown;
	
	@ManyToOne
	private Link currentCity;
	
	private Gender gender;
	
	@OneToMany
	private List<UserContact> userContacts;
	
	@ManyToOne
	private UserCivilStatus userRelationship;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Link getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(Link homeTown) {
		this.homeTown = homeTown;
	}
	public Link getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(Link currentCity) {
		this.currentCity = currentCity;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<UserContact> getUserContacts() {
		return userContacts;
	}
	public void setUserContacts(List<UserContact> userContacts) {
		this.userContacts = userContacts;
	}
	public UserCivilStatus getUserRelationship() {
		return userRelationship;
	}
	public void setUserRelationship(UserCivilStatus userRelationship) {
		this.userRelationship = userRelationship;
	}
	
}
