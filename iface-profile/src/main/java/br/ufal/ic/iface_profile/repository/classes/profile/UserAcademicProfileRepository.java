package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserAcademicProfile;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserAcademicProfileRepositoryInterface;

@Component
public class UserAcademicProfileRepository extends GenericHibernateRepository<UserAcademicProfile, Integer>
				implements UserAcademicProfileRepositoryInterface{

}
