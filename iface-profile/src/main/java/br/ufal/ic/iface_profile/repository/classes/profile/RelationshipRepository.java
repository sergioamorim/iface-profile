package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipRepositoryInterface;

@Component
public class RelationshipRepository extends GenericHibernateRepository<Relationship, Integer>
				implements RelationshipRepositoryInterface{
	
	@SuppressWarnings("unchecked")
	public List<User> findRelationships(Integer x){
		
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM relationship "
				+ "WHERE relationship.sender_id=:x_id OR relationship.receiver_id=:x_id").addEntity(Relationship.class);
		q1.setInteger("x_id", x);
		return q1.list();
	}


}
