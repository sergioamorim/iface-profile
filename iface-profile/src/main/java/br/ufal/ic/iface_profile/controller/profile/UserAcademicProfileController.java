package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.UserAcademicProfile;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserAcademicProfileRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/useracademicprofile")
public class UserAcademicProfileController extends AbstractController<UserAcademicProfile, Integer>{
	
	@Autowired
	@Qualifier("userAcademicProfileRepository")
	private UserAcademicProfileRepositoryInterface repository;

	protected UserAcademicProfileRepositoryInterface getRepository() {
		return repository;
	}
	
}
