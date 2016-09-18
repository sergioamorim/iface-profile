package br.ufal.ic.iface_profile.controller.profile;

import java.io.IOException;
import java.util.Date;

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
import br.ufal.ic.iface_profile.model.profile.UserCivilStatus;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.storytelling.UserLogRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserCivilStatusRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/usercivilstatus")
public class UserCivilStatusController extends AbstractController<UserCivilStatus, Integer>{
	
	@Autowired
	@Qualifier("userCivilStatusRepository")
	private UserCivilStatusRepositoryInterface repository;

	protected UserCivilStatusRepositoryInterface getRepository() {
		return repository;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		UserCivilStatus deletedUserCivilStatus = getRepository().findById(id);
		
		User user = deletedUserCivilStatus.getUsuario();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Remove civil status");
		
		userLog.setTitle(user.getName()+
							" removed "+
								deletedUserCivilStatus.getMyCivilStatus().getName()+
									" from profile");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);
		
		getRepository().delete(deletedUserCivilStatus);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserCivilStatus save(@RequestBody @Valid UserCivilStatus newUserCivilStatus, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserCivilStatus.getUsuario();
		userLog.setUser(user);
		
		userLog.setActivity("Add academic profile");
		userLog.setTitle(user.getName()+" added "+
							newUserCivilStatus.getMyCivilStatus().getName()+
								" to profile");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().save(newUserCivilStatus);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserCivilStatus update(@RequestBody @Valid UserCivilStatus newUserCivilStatus, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserCivilStatus.getUsuario();
		userLog.setUser(user);
		
		userLog.setActivity("Relationship began");
		
		userLog.setTitle(user.getName()+
							" modified "+
								newUserCivilStatus.getMyCivilStatus().getName()+
									" from profile");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().update(newUserCivilStatus);
	}

}
