package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;

@Component
public class RelationshipTypeRepository extends GenericHibernateRepository<RelationshipType, Integer>
				implements RelationshipTypeRepositoryInterface{
	
	public RelationshipType findRelationshipTypeByGender(Integer id_sender,Integer id_receiver){
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM relationshiptype "
				+ "WHERE relationshiptype.senderDegreeOfKinship_id=:id_sender AND "
				+ "relationshiptype.receiverDegreeOfKinship_id=:id_receiver").addEntity(RelationshipType.class);
		q1.setInteger("id_sender", id_sender);
		q1.setInteger("id_receiver", id_receiver);
		@SuppressWarnings("unchecked")
		List<RelationshipType> aux = q1.list();
		RelationshipType ret = aux.get(0);
		return ret;
	}
}
