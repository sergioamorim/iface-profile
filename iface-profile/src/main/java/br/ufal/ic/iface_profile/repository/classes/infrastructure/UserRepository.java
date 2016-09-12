package br.ufal.ic.iface_profile.repository.classes.infrastructure;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.infrastructure.UserRepositoryInterface;

@Component
public class UserRepository extends GenericHibernateRepository<User, Integer> 
				implements UserRepositoryInterface {

}
