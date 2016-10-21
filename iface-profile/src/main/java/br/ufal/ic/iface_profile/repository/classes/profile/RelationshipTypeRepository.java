package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;

@Component
public class RelationshipTypeRepository extends GenericHibernateRepository<RelationshipType, Integer>
				implements RelationshipTypeRepositoryInterface{
	
public RelationshipType findRelationshipTypeByGender(Integer gender_sender,Integer dok_receiver){
		
		Criteria c = getSession().createCriteria(RelationshipType.class, "relationshiptype");
		c.createAlias("relationshiptype.senderDegreeOfKinship" , "sender");
		c.createAlias("sender.gender", "gender");
		c.createAlias("relationshiptype.receiverDegreeOfKinship", "receiverdok");
		c.add(Restrictions.and(
				Restrictions.eq("receiverdok.id", dok_receiver),
				Restrictions.eq("gender.id", gender_sender)));
		
		try{
			RelationshipType rt = (RelationshipType) c.list().get(0);
			return rt;
		}catch(IndexOutOfBoundsException e){
			return null;
		}
		
		
	}
	
	
	public void initializeRelationshipTypes(List<DegreeOfKinship> m,List<DegreeOfKinship> f){
		List<RelationshipType> u = new ArrayList<RelationshipType>();
		
		//Pais e filhos
		u.add(new RelationshipType(m.get(0), m.get(1)));
		u.add(new RelationshipType(m.get(0), f.get(1)));
		u.add(new RelationshipType(f.get(0), m.get(1)));
		u.add(new RelationshipType(f.get(0), f.get(1)));
		u.add(new RelationshipType(m.get(1), m.get(0)));
		u.add(new RelationshipType(m.get(1), f.get(0)));
		u.add(new RelationshipType(f.get(1), m.get(0)));
		u.add(new RelationshipType(f.get(1), f.get(0)));
		
		//Irmãos e irmãs
		u.add(new RelationshipType(m.get(2),m.get(2)));
		u.add(new RelationshipType(f.get(2),f.get(2)));
		u.add(new RelationshipType(m.get(2),f.get(2)));
		u.add(new RelationshipType(f.get(2),m.get(2)));
		
		//Tios e sobrinhos
		u.add(new RelationshipType(m.get(3), m.get(4)));
		u.add(new RelationshipType(m.get(3), f.get(4)));
		u.add(new RelationshipType(f.get(3), m.get(4)));
		u.add(new RelationshipType(f.get(3), f.get(4)));
		u.add(new RelationshipType(m.get(4), m.get(3)));
		u.add(new RelationshipType(m.get(4), f.get(3)));
		u.add(new RelationshipType(f.get(4), m.get(3)));
		u.add(new RelationshipType(f.get(4), f.get(3)));
		
		//Primos e primas
		u.add(new RelationshipType(m.get(5), m.get(5)));
		u.add(new RelationshipType(f.get(5), f.get(5)));
		u.add(new RelationshipType(m.get(5), f.get(5)));
		u.add(new RelationshipType(f.get(5), m.get(5)));
		
		//Avôs e netos
		u.add(new RelationshipType(m.get(6), m.get(7)));
		u.add(new RelationshipType(m.get(6), f.get(7)));
		u.add(new RelationshipType(f.get(6), m.get(7)));
		u.add(new RelationshipType(f.get(6), f.get(7)));
		u.add(new RelationshipType(m.get(7), m.get(6)));
		u.add(new RelationshipType(m.get(7), f.get(6)));
		u.add(new RelationshipType(f.get(7), m.get(6)));
		u.add(new RelationshipType(f.get(7), f.get(6)));
		
		//Padrastos e enteados
		u.add(new RelationshipType(m.get(8), m.get(9)));
		u.add(new RelationshipType(m.get(8), f.get(9)));
		u.add(new RelationshipType(f.get(8), m.get(9)));
		u.add(new RelationshipType(f.get(8), f.get(9)));
		u.add(new RelationshipType(m.get(9), m.get(8)));
		u.add(new RelationshipType(m.get(9), f.get(8)));
		u.add(new RelationshipType(f.get(9), m.get(8)));
		u.add(new RelationshipType(f.get(9), f.get(8)));
		
		//Cunhados e cunhadas
		u.add(new RelationshipType(m.get(10), m.get(10)));
		u.add(new RelationshipType(m.get(10), f.get(10)));
		u.add(new RelationshipType(f.get(10), f.get(10)));
		u.add(new RelationshipType(f.get(10), m.get(10)));
		
		//Sogros e genros
		u.add(new RelationshipType(m.get(11), m.get(12)));
		u.add(new RelationshipType(m.get(11), f.get(12)));
		u.add(new RelationshipType(f.get(11), f.get(12)));
		u.add(new RelationshipType(f.get(11), m.get(12)));
		u.add(new RelationshipType(m.get(12), m.get(11)));
		u.add(new RelationshipType(m.get(12), f.get(11)));
		u.add(new RelationshipType(f.get(12), f.get(11)));
		u.add(new RelationshipType(f.get(12), m.get(11)));
		
		//Namorado e namorada
		
		u.add(new RelationshipType(m.get(13), f.get(13)));
		u.add(new RelationshipType(f.get(13), m.get(13)));
		
		//Noivo e noiva
		
		u.add(new RelationshipType(m.get(14), f.get(14)));
		u.add(new RelationshipType(f.get(14), m.get(14)));
		
		//Casado
		
		u.add(new RelationshipType(f.get(15), m.get(15)));
		u.add(new RelationshipType(m.get(15), f.get(15)));
		
		//Viúvo
		
		u.add(new RelationshipType(m.get(16), f.get(16)));
		u.add(new RelationshipType(f.get(16), m.get(16)));

		
		for (RelationshipType x : u){
			save(x);
		}
		
	}
}
