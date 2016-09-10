package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserProfessionalProfile;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfessionalProfileRepositoryInterface;

@Component
public class UserProfessionalProfileRepository extends GenericHibernateRepository<UserProfessionalProfile, Integer>
				implements UserProfessionalProfileRepositoryInterface{

}
