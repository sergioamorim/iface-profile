package br.ufal.ic.iface_profile.model.profile;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.util.CustomJsonDateDeserializer;
import br.ufal.ic.iface_profile.util.CustomJsonDateSerializer;

@Entity
@Table(name="userprofile")

public class UserProfile {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	private User user;
	
	private String name;
	
	private String lastname;
	
	private String picture;
	
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "UTC")
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
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private  List<UserAcademicProfile> userAcademicProfile;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<UserProfessionalProfile> userProfessionalProfile;
	
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
	public List<UserAcademicProfile> getUserAcademicProfile() {
		return userAcademicProfile;
	}
	public void setUserAcademicProfile(List<UserAcademicProfile> userAcademicProfile) {
		this.userAcademicProfile = userAcademicProfile;
	}
	public List<UserProfessionalProfile> getUserProfessionalProfile() {
		return userProfessionalProfile;
	}
	public void setUserProfessionalProfile(List<UserProfessionalProfile> userProfessionalProfile) {
		this.userProfessionalProfile = userProfessionalProfile;
	}
	
}
