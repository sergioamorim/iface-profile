package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.DegreeOfKinshipRepositoryInterface;

@Component
public class DegreeOfKinshipRepository extends GenericHibernateRepository<DegreeOfKinship, Integer>
				implements DegreeOfKinshipRepositoryInterface{
	
	public List<DegreeOfKinship> findDegreeOfKinshipByGender(Integer x){
		
		return this.findByCriteria(Restrictions.eq("degree_of_kinship.gender.id", x));	
	}
	
	public void initializeDegreeOfKinships(List<Gender> g){
		List<DegreeOfKinship> u = new ArrayList<DegreeOfKinship>();
		//Masculino
		u.add(new DegreeOfKinship(g.get(0), "Pai"));
		u.add(new DegreeOfKinship(g.get(0), "Filho"));
		u.add(new DegreeOfKinship(g.get(0), "Irmão"));
		u.add(new DegreeOfKinship(g.get(0), "Tio"));
		u.add(new DegreeOfKinship(g.get(0), "Sobrinho"));
		u.add(new DegreeOfKinship(g.get(0), "Primo"));
		u.add(new DegreeOfKinship(g.get(0), "Avô"));
		u.add(new DegreeOfKinship(g.get(0), "Neto"));
		u.add(new DegreeOfKinship(g.get(0), "Padrasto"));
		u.add(new DegreeOfKinship(g.get(0), "Enteado"));
		u.add(new DegreeOfKinship(g.get(0), "Cunhado"));
		u.add(new DegreeOfKinship(g.get(0), "Sogro"));
		u.add(new DegreeOfKinship(g.get(0), "Genro"));
		u.add(new DegreeOfKinship(g.get(0), "Namorado"));
		u.add(new DegreeOfKinship(g.get(0), "Noivo"));
		u.add(new DegreeOfKinship(g.get(0), "Marido"));
		u.add(new DegreeOfKinship(g.get(0), "Viúvo"));
		
		//Feminino
		u.add(new DegreeOfKinship(g.get(1), "Mãe"));
		u.add(new DegreeOfKinship(g.get(1), "Filha"));
		u.add(new DegreeOfKinship(g.get(1), "Irmã"));
		u.add(new DegreeOfKinship(g.get(1), "Tia"));
		u.add(new DegreeOfKinship(g.get(1), "Sobrinha"));
		u.add(new DegreeOfKinship(g.get(1), "Prima"));
		u.add(new DegreeOfKinship(g.get(1), "Avó"));
		u.add(new DegreeOfKinship(g.get(1), "Neta"));
		u.add(new DegreeOfKinship(g.get(1), "Madrasta"));
		u.add(new DegreeOfKinship(g.get(1), "Enteada"));
		u.add(new DegreeOfKinship(g.get(1), "Cunhada"));
		u.add(new DegreeOfKinship(g.get(1), "Sogra"));
		u.add(new DegreeOfKinship(g.get(1), "Nora"));
		u.add(new DegreeOfKinship(g.get(1), "Namorada"));
		u.add(new DegreeOfKinship(g.get(1), "Noiva"));
		u.add(new DegreeOfKinship(g.get(1), "Esposa"));
		u.add(new DegreeOfKinship(g.get(1), "Viúva"));
		
		for(DegreeOfKinship x : u){
			save(x);
		}
	
	}
}