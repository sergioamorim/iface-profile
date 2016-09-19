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
import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.storytelling.UserLogRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserProfileRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_profile")
public class UserProfileController extends AbstractController<UserProfile, Integer>{
	
	@Autowired
	@Qualifier("userProfileRepository")
	private UserProfileRepositoryInterface repository;

	protected UserProfileRepositoryInterface getRepository() {
		return repository;
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		UserProfile deletedUserProfile = getRepository().findById(id);
		
		User user = deletedUserProfile.getUser();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Remove profile");
		
		userLog.setTitle(user.getName()+" said goodbye to iFace");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);
		
		getRepository().delete(deletedUserProfile);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserProfile save(@RequestBody @Valid UserProfile newUserProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Create profile");
		userLog.setTitle(user.getName()+" starts using iFace");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().save(newUserProfile);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserProfile update(@RequestBody @Valid UserProfile newUserProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Profile modification");
		
		userLog.setTitle(user.getName()+"modified profile");
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().update(newUserProfile);
	}

}
