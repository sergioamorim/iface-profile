package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.GenderRepositoryInterface;

@Component
public class GenderRepository extends GenericHibernateRepository<Gender, Integer>
				implements GenderRepositoryInterface{

}
