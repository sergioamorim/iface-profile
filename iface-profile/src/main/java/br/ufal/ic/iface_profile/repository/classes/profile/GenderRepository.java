package br.ufal.ic.iface_profile.repository.classes.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.GenderRepositoryInterface;

@Component
public class GenderRepository extends GenericHibernateRepository<Gender, Integer>
				implements GenderRepositoryInterface{
	public void initializeGenders(){
		List<Gender> u =  new ArrayList<Gender>();
		u.add(new Gender("Masculino"));
		u.add(new Gender("Feminino"));
		for (Gender x : u){
			save(x);
		}
	}

}
