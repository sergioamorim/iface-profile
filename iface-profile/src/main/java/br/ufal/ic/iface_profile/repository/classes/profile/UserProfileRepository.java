package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfileRepositoryInterface;

@Component
public class UserProfileRepository extends GenericHibernateRepository<UserProfile, Integer>
				implements UserProfileRepositoryInterface{
	public UserProfile findUserProfileById(Integer x){
		SQLQuery q1 = this .getSession().createSQLQuery("SELECT * FROM userprofile "
				+ "WHERE user_id=:id").addEntity(UserProfile.class);
		q1.setInteger("id", x);
		@SuppressWarnings("unchecked")
		List<UserProfile> aux = q1.list();
		return aux.get(0);
	}

}
