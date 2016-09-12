package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;

@Component
public class RelationshipTypeRepository extends GenericHibernateRepository<RelationshipType, Integer>
				implements RelationshipTypeRepositoryInterface{

}
