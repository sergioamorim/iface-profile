package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.TypeContact;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.TypeContactRepositoryInterface;

@Component
public class TypeContactRepository extends GenericHibernateRepository<TypeContact, Integer>
				implements TypeContactRepositoryInterface{

}
