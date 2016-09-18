package br.ufal.ic.iface_profile.controller.storytelling;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.exceptions.ValidationException;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/userlog")

public class UserLogController extends AbstractController<UserLog, Integer>{
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface repository;

	@Override
	protected UserLogRepositoryInterface getRepository() {
		return this.repository;
	}
	
	@RequestMapping(value = "/userlog", method = RequestMethod.GET)
    @ResponseBody
    public List<UserLog> getUserLogswe() {
		return getRepository().getLogs(new User());
    }
	
	@RequestMapping(value = "/userlog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserLog addUserLog(@RequestBody @Valid UserLog newUserLog, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
	JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		return getRepository().save(newUserLog);
    }
	
	@RequestMapping(value = "/userlog/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int id) {
		UserLog userlog = getRepository().findById(id);
		getRepository().delete(userlog);
	}
}
