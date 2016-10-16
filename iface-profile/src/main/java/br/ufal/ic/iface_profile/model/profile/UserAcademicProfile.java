package br.ufal.ic.iface_profile.model.profile;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.util.CustomJsonDateDeserializer;
import br.ufal.ic.iface_profile.util.CustomJsonDateSerializer;

@Entity
@Table(name="useracademicprofile")
public class UserAcademicProfile {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private UserProfile userProfile;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Link educationalInstitution;
	
	private String course;
	
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "UTC")
	private Date dateStart;
	
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "UTC")
	private Date dateEnd;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Link getEducationalInstitution() {
		return educationalInstitution;
	}
	public void setEducationalInstitution(Link educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
