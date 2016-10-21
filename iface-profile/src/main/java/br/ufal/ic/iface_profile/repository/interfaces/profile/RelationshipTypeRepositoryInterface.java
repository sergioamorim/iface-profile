package br.ufal.ic.iface_profile.repository.interfaces.profile;

import java.util.List;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface RelationshipTypeRepositoryInterface extends RepositoryInterface<RelationshipType, Integer>{

	public RelationshipType findRelationshipTypeByGender(Integer gender_sender,Integer dok_receiver);
	public void initializeRelationshipTypes(List<DegreeOfKinship> m,List<DegreeOfKinship> f);
}
