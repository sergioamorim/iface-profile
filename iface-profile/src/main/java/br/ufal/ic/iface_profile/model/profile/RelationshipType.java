package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="relationshiptype")
public class RelationshipType {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private DegreeOfKinship senderDegreeOfKinship;
	
	@ManyToOne
	private DegreeOfKinship receiverDegreeOfKinship;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DegreeOfKinship getSenderDegreeOfKinship() {
		return senderDegreeOfKinship;
	}

	public void setSenderDegreeOfKinship(DegreeOfKinship senderDegreeOfKinship) {
		this.senderDegreeOfKinship = senderDegreeOfKinship;
	}

	public DegreeOfKinship getReceiverDegreeOfKinship() {
		return receiverDegreeOfKinship;
	}

	public void setReceiverDegreeOfKinship(DegreeOfKinship receiverDegreeOfKinship) {
		this.receiverDegreeOfKinship = receiverDegreeOfKinship;
	}
	
}
