package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="usercontact")
public class UserContact {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User user;
	
	private String contact;
	
	@ManyToOne
	private TypeContact typeContact;
	
	
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
