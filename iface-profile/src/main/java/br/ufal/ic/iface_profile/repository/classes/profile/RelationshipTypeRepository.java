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
	
	public RelationshipType findRelationshipTypeByGender(Integer gender_sender,Integer dok_receiver){
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM relationshiptype "
				+ "INNER JOIN degree_of_kinship "
				+ "ON relationshiptype.senderDegreeOfKinship_id=degree_of_kinship.id "
				+ "WHERE degree_of_kinship.gender_id=:gender_sender AND "
				+ "relationshiptype.receiverDegreeOfKinship_id=:dok_receiver").addEntity(RelationshipType.class);
		q1.setInteger("gender_sender", gender_sender);
		q1.setInteger("dok_receiver", dok_receiver);
		@SuppressWarnings("unchecked")
		List<RelationshipType> aux = q1.list();
		RelationshipType ret = aux.get(0);
		return ret;
	}
}
