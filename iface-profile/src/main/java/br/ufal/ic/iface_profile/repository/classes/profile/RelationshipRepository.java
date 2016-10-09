package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipRepositoryInterface;

@Component
public class RelationshipRepository extends GenericHibernateRepository<Relationship, Integer>
				implements RelationshipRepositoryInterface{
	
	public List<Relationship> findRelationships(Integer x) {
		return this.findByCriteria(Restrictions.and(
				 Restrictions.or(
					Restrictions.eq("sender.id", x),
					Restrictions.eq("receiver.id", x)
					),
				 Restrictions.eq("statusSolicitation",true)
				)	
		 );
	}


}
