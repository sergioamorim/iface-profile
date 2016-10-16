package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.CivilStatusRepositoryInterface;

@Component
public class CivilStatusRepository extends GenericHibernateRepository<CivilStatus, Integer> 
				implements CivilStatusRepositoryInterface{
	
	public void initializeCivilStatus(List<Gender> g){
		List<CivilStatus> cs = new ArrayList<CivilStatus>();
		
		//Masculino
		cs.add(new CivilStatus("Solteiro",g.get(0)));
		cs.add(new CivilStatus("Namorando",g.get(0)));
		cs.add(new CivilStatus("Noivo",g.get(0)));
		cs.add(new CivilStatus("Casado",g.get(0)));
		cs.add(new CivilStatus("Viúvo",g.get(0)));
		
		
		//Feminino
		cs.add(new CivilStatus("Solteira",g.get(1)));
		cs.add(new CivilStatus("Namorando",g.get(1)));
		cs.add(new CivilStatus("Noiva",g.get(1)));
		cs.add(new CivilStatus("Casada",g.get(1)));
		cs.add(new CivilStatus("Viúva",g.get(1)));
		
		for(CivilStatus x : cs){
			save(x);
		}
		
	}
	
	public List<CivilStatus> findCivilStatusByGender(Integer id){
		return this.findByCriteria(Restrictions.eq("gender.id", id));
	}

}
