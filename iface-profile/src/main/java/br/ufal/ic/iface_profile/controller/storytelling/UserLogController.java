package br.ufal.ic.iface_profile.controller.storytelling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/userlog")

public class UserLogController {
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface repository;

	protected UserLogRepositoryInterface getRepository() {
		return repository;
	}
}
