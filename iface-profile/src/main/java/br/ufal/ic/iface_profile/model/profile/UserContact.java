package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="usercontact")
public class UserContact {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JsonIgnoreProperties("userProfile")
	private User user;
	
	private String contact;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private TypeContact typeContact;
	
	
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public TypeContact getTypeContact() {
		return typeContact;
	}
	public void setTypeContact(TypeContact typeContact) {
		this.typeContact = typeContact;
	}
	
	
}
