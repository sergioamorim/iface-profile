package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="relationship")
public class Relationship {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;
	
	@ManyToOne
	private RelationshipType relatioshipType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public RelationshipType getRelatioshipType() {
		return relatioshipType;
	}
	public void setRelatioshipType(RelationshipType relatioshipType) {
		this.relatioshipType = relatioshipType;
	}
	public boolean isStatusSolicitation() {
		return statusSolicitation;
	}
	public void setStatusSolicitation(boolean statusSolicitation) {
		this.statusSolicitation = statusSolicitation;
	}
	private boolean statusSolicitation;
}
