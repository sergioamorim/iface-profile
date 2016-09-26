package br.ufal.ic.iface_profile.controller.storytelling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.infrastructure.UserRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_log")

public class UserLogController extends AbstractController<UserLog, Integer>{
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface repository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepositoryInterface userRepository;

	@Override
	protected UserLogRepositoryInterface getRepository() {
		return this.repository;
	}
	
	@RequestMapping(value = "/individual/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserLog> getUserLogs(@PathVariable Integer id) {
		return getRepository().getLogs(userRepository.getUserByUsername(id));
    }
}
