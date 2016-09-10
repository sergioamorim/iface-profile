package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfileRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/userprofile")
public class UserProfileController extends AbstractController<UserProfile, Integer>{
	
	@Autowired
	@Qualifier("userProfileRepository")
	private UserProfileRepositoryInterface repository;

	protected UserProfileRepositoryInterface getRepository() {
		return repository;
	}
	

}
