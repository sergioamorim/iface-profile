package br.ufal.ic.iface_profile.repository.classes.infrastructure;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public User getUserByUsername(Integer id) {
		return this.findByCriteria(Restrictions.eq("id", id)).get(0);
	}

}
