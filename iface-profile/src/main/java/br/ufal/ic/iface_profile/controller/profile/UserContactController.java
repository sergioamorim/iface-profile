package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.UserContact;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserContactRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/usercontact")
public class UserContactController extends AbstractController<UserContact, Integer>{
	@Autowired
	@Qualifier("userContactRepository")
	private UserContactRepositoryInterface repository;

	protected UserContactRepositoryInterface getRepository() {
		return repository;
	}
	

}
