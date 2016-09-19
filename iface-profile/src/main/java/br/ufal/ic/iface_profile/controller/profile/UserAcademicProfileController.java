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
import br.ufal.ic.iface_profile.model.profile.UserAcademicProfile;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserAcademicProfileRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_academic_profile")
public class UserAcademicProfileController extends AbstractController<UserAcademicProfile, Integer>{
	
	@Autowired
	@Qualifier("userAcademicProfileRepository")
	private UserAcademicProfileRepositoryInterface repository;

	protected UserAcademicProfileRepositoryInterface getRepository() {
		return repository;
	}
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface logRepository;
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		UserAcademicProfile deletedUserAcademicProfile = getRepository().findById(id);
		
		User user = deletedUserAcademicProfile.getUser();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Remove academic profile");
		
		userLog.setTitle(user.getName()+
							" removed "+
								deletedUserAcademicProfile.getCollege()+
									" from profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);
		
		getRepository().delete(deletedUserAcademicProfile);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserAcademicProfile save(@RequestBody @Valid UserAcademicProfile newUserAcademicProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserAcademicProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Add academic profile");
		userLog.setTitle(user.getName()+" added "+
							newUserAcademicProfile.getCollege()+
								" to profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().save(newUserAcademicProfile);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserAcademicProfile update(@RequestBody @Valid UserAcademicProfile newUserAcademicProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserAcademicProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Academic profile modification");
		
		userLog.setTitle(user.getName()+
							" modified "+
								newUserAcademicProfile.getCollege()+
									" on profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().update(newUserAcademicProfile);
	}
	
}
