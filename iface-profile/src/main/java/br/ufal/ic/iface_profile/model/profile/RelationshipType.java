package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relationshiptype")
public class RelationshipType {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String senderRelationship;
	
	private String receiverRelationship;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSenderRelationship() {
		return senderRelationship;
	}
	public void setSenderRelationship(String senderRelationship) {
		this.senderRelationship = senderRelationship;
	}
	public String getReceiverRelationship() {
		return receiverRelationship;
	}
	public void setReceiverRelationship(String receiverRelationship) {
		this.receiverRelationship = receiverRelationship;
	}
}
