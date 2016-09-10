package br.ufal.ic.iface_profile.repository.classes.profile;

import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipRepositoryInterface;

public class RelationshipRepository extends GenericHibernateRepository<Relationship, Integer>
				implements RelationshipRepositoryInterface{

}
