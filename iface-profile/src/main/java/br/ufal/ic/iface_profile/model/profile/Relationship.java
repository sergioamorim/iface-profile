package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Integer id;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RelationshipType relationshipType;
	
	private boolean statusSolicitation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	public boolean isStatusSolicitation() {
		return statusSolicitation;
	}
	public void setStatusSolicitation(boolean statusSolicitation) {
		this.statusSolicitation = statusSolicitation;
	}
}
