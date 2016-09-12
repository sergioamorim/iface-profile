package br.ufal.ic.iface_profile.repository.classes.storytelling;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@Component
public class UserLogRepository extends GenericHibernateRepository<UserLog, Integer> implements UserLogRepositoryInterface{

}
