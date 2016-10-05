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
import br.ufal.ic.iface_profile.model.profile.UserContact;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.profile.UserContactRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_contact")
public class UserContactController extends AbstractController<UserContact, Integer>{
	@Autowired
	@Qualifier("userContactRepository")
	private UserContactRepositoryInterface repository;

	protected UserContactRepositoryInterface getRepository() {
		return repository;
	}
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface logRepository;
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		UserContact deletedUserContact = getRepository().findById(id);
		
		User user = deletedUserContact.getUser();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Remove contact");
		
		userLog.setTitle(user.getUserProfile().getName()+
							" removed "+
								deletedUserContact.getContact()+
									" from profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);
		
		getRepository().delete(deletedUserContact);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserContact save(@RequestBody @Valid UserContact newUserContact, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserContact.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Add contact");
		userLog.setTitle(user.getUserProfile().getName()+" added "+
							newUserContact.getContact()+
								" to profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().save(newUserContact);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserContact update(@RequestBody @Valid UserContact newUserContact, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserContact.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Contact modification");
		
		userLog.setTitle(user.getUserProfile().getName()+
							" modified "+
								newUserContact.getContact()+
									" on profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().update(newUserContact);
	}
	

}
