package br.ufal.ic.iface_profile.model.profile;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="userprofile")

public class UserProfile {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private User user;
	
	private String name;
	
	private String lastname;
	
	private String picture;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Link birthPlace;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Link homeTown;
	
	@ManyToOne
	private Gender gender;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<UserContact> userContacts;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private UserCivilStatus userRelationship;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	public Link getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(Link birthPlace) {
		this.birthPlace = birthPlace;
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
