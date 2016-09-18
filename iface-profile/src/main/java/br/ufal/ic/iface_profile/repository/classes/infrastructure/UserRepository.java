package br.ufal.ic.iface_profile.repository.classes.infrastructure;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.infrastructure.UserRepositoryInterface;

@Component
public class UserRepository extends GenericHibernateRepository<User, Integer> 
				implements UserRepositoryInterface {
	
	public UserRepository(){
		
	}
	
	public UserRepository(Session session){
		this.setSession(session);
	}
	
	public User getUserById(Integer id){
		return this.findById(id);
	}

}
