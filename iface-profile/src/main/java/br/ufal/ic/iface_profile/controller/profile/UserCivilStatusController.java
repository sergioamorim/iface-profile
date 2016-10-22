package br.ufal.ic.iface_profile.controller.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.UserCivilStatus;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserCivilStatusRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_civil_status")
public class UserCivilStatusController extends AbstractController<UserCivilStatus, Integer>{
	
	@Autowired
	@Qualifier("userCivilStatusRepository")
	private UserCivilStatusRepositoryInterface repository;

	protected UserCivilStatusRepositoryInterface getRepository() {
		return repository;
	}

}
