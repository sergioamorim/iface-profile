package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserCivilStatus;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserCivilStatusRepositoryInterface;

@Component
public class UserCivilStatusRepository extends GenericHibernateRepository<UserCivilStatus,Integer>
				implements UserCivilStatusRepositoryInterface{

}
