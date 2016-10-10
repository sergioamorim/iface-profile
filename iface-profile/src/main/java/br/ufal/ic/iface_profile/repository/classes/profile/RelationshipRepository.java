package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.SQLQuery;
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
	
	@SuppressWarnings("unchecked")
	public List<Relationship> findRelationshipRequests(Integer user_id){
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM relationship "
				+ "WHERE (sender_id:=id OR receiver_id:=id) AND statusSolicitation=0").addEntity(Relationship.class);
		
		q1.setInteger("id", user_id);
		return q1.list();
	}


}
