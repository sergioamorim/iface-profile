package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.DegreeOfKinshipRepositoryInterface;

@Component
public class DegreeOfKinshipRepository extends GenericHibernateRepository<DegreeOfKinship, Integer>
				implements DegreeOfKinshipRepositoryInterface{
	
}
