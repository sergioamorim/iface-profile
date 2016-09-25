package br.ufal.ic.iface_profile.repository.interfaces.profile;

import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface RelationshipTypeRepositoryInterface extends RepositoryInterface<RelationshipType, Integer>{

	public RelationshipType findRelationshipTypeByGender(Integer id_receiver,Integer id_sender);
}
