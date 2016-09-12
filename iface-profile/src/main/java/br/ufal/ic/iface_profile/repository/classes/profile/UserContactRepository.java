package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserContact;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserContactRepositoryInterface;

@Component
public class UserContactRepository extends GenericHibernateRepository<UserContact, Integer>
				implements UserContactRepositoryInterface{

	
}
