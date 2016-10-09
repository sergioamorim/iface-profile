package br.ufal.ic.iface_profile.repository.classes.profile;


import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfileRepositoryInterface;

@Component
public class UserProfileRepository extends GenericHibernateRepository<UserProfile, Integer>
				implements UserProfileRepositoryInterface{
	public UserProfile findUserProfileById(Integer x) {
		try{
			return this.findByCriteria(Restrictions.eq("user.id", x)).get(0);	
		}catch(NullPointerException e){
			return null;
		}
	}	

}
