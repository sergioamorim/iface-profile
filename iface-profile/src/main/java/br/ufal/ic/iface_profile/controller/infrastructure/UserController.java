package br.ufal.ic.iface_profile.controller.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.interfaces.infrastructure.UserRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user")
public class UserController extends AbstractController<User, Integer>  {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepositoryInterface repository;
	
	@Override
	protected UserRepositoryInterface getRepository() {
		return this.repository;
	}

}
