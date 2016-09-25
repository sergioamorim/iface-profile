package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.DegreeOfKinshipRepositoryInterface;

@Component
public class DegreeOfKinshipRepository extends GenericHibernateRepository<DegreeOfKinship, Integer>
				implements DegreeOfKinshipRepositoryInterface{
	
	@SuppressWarnings("unchecked")
	public List<DegreeOfKinship> findDegreeOfKinshipByGender(Integer x){
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM degree_of_kinship "
				+ "WHERE degree_of_kinship.gender_id=:x_id").addEntity(DegreeOfKinship.class);
		q1.setInteger("x_id", x);
		return q1.list();
	}
}