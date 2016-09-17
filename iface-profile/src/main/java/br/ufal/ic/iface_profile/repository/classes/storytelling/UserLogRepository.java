package br.ufal.ic.iface_profile.repository.classes.storytelling;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@Component
public class UserLogRepository extends GenericHibernateRepository<UserLog, Integer> implements UserLogRepositoryInterface{

	@Override
	public List<UserLog> getLogs(User u) {
		List<UserLog> userlogs = this.findByCriteria(Restrictions.eq("user", u));
		return userlogs;
	}

}
