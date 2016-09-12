package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.CivilStatusRepositoryInterface;

@Component
public class CivilStatusRepository extends GenericHibernateRepository<CivilStatus, Integer> 
				implements CivilStatusRepositoryInterface{

}
