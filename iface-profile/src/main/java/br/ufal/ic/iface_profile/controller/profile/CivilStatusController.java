package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.repository.interfaces.profile.CivilStatusRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/civilstatus")
public class CivilStatusController extends AbstractController<CivilStatus, Integer> {
	
	@Autowired
	@Qualifier("civilStatusRepository")
	private CivilStatusRepositoryInterface repository;
	
	@Override
	protected CivilStatusRepositoryInterface getRepository(){
		return this.repository;
	}
	
}
