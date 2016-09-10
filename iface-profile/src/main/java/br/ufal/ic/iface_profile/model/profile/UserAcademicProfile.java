package br.ufal.ic.iface_profile.model.profile;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="useracademicprofile")
public class UserAcademicProfile {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Link link;
	
	private String college;
	
	private Date dateStart;
	
	private Date dateEnd;
	
	
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
	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
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
}
