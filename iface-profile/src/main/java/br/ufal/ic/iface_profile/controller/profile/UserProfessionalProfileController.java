package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.UserProfessionalProfile;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfessionalProfileRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/userprofessionalprofile")
public class UserProfessionalProfileController extends AbstractController<UserProfessionalProfile, Integer>{
	
	@Autowired
	@Qualifier("userProfessionalProfileRepository")
	private UserProfessionalProfileRepositoryInterface repository;

	protected UserProfessionalProfileRepositoryInterface getRepository() {
		return repository;
	}
	
	

}
