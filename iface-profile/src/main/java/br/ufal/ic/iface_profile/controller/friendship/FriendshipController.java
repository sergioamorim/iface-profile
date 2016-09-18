package br.ufal.ic.iface_profile.controller.friendship;

import java.io.IOException;
import java.util.Date;
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
import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.storytelling.UserLogRepository;
import br.ufal.ic.iface_profile.repository.interfaces.friendship.FriendshipRepositoryInterface;

@RestController
@Transactional
@RequestMapping (value = "/friendship")
public class FriendshipController extends AbstractController <Friendship, Integer>{

	@Autowired
	@Qualifier("friendshipRepository")
	private FriendshipRepositoryInterface repository;
	
	@Override
	protected FriendshipRepositoryInterface getRepository() {
		return this.repository;
	}
	
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
    @ResponseBody
    public List<Friendship> getFriends() {
		return getRepository().findFriends(new User());
    }
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		Friendship deletedFriendship = getRepository().findById(id);
		
		User user_x = deletedFriendship.getUser_x();
		User user_y = deletedFriendship.getUser_y();
		
		UserLog userXLog = new UserLog();
		UserLog userYLog = new UserLog();
		
		userXLog.setUser(user_x);
		userYLog.setUser(user_y);
		
		userXLog.setActivity("Undo friendship");
		userYLog.setActivity("Undo friendship");
		
		userXLog.setTimestamp(new Date());
		userYLog.setTimestamp(new Date());
		
		userXLog.setTitle("Undo friendship with "+user_y.getName());
		userYLog.setTitle("Undo friendship with "+user_x.getName());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userXLog);
		userLogRepository.save(userYLog);
		
		getRepository().delete(deletedFriendship);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Friendship save(@RequestBody @Valid Friendship newFriendship, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		User user_x = newFriendship.getUser_x();
		User user_y = newFriendship.getUser_y();
		
		UserLog userXLog = new UserLog();
		UserLog userYLog = new UserLog();
		
		userXLog.setUser(user_x);
		userYLog.setUser(user_y);
		
		userXLog.setActivity("New friendship");
		userYLog.setActivity("New friendship");
		
		userXLog.setTimestamp(new Date());
		userYLog.setTimestamp(new Date());
		
		userXLog.setTitle("New friendship with "+user_y.getName());
		userYLog.setTitle("New friendship with "+user_x.getName());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userXLog);
		userLogRepository.save(userYLog);
		
		return getRepository().save(newFriendship);
	}
}
