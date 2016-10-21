package br.ufal.ic.iface_profile.model.profile;

import java.util.List;

public class RelationshipWrapper {

	private List<Relationship> relationships;
	
	private Integer genderSenderId;

	public Integer getGenderSenderId() {
		return genderSenderId;
	}

	public void setGenderSenderId(Integer genderSenderId) {
		this.genderSenderId = genderSenderId;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}
	
}
