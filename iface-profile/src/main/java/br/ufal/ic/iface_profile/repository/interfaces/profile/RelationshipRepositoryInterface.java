package br.ufal.ic.iface_profile.repository.interfaces.profile;

import java.util.List;

import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface RelationshipRepositoryInterface extends RepositoryInterface<Relationship, Integer>{

	public List<Relationship> findRelationships(Integer x);
	public List<Relationship> findRelationshipRequests(Integer user_id);
	public List<Relationship> findAllRelationships(Integer x);
}
