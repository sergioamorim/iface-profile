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
import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.friendship.FriendshipRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

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
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface logRepository;
	
	@RequestMapping (value="/find_friends/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findFriends(@PathVariable Integer id){
		return getRepository().findFriends(id);
	}
	
	@RequestMapping (value="/find_friends_by_name/{id}/{name}")
	@ResponseBody
	public List<UserProfile> findFriendsByName(@PathVariable Integer id, @PathVariable String name){
		return getRepository().findFriendsByName(id, name);
	}
	
	@RequestMapping (value="/hasFriendship/{id_1}_{id_2}", method = RequestMethod.GET)
	@ResponseBody
	public Friendship hasFriendship(@PathVariable Integer id_1, @PathVariable Integer id_2){
		return getRepository().hasFriendship(id_1,id_2);
	}

	@RequestMapping(value="/find_requests/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Friendship> findFriendshipRequests(@PathVariable Integer id){
		return getRepository().findFriendshipRequests(id);
	}
	
	@RequestMapping (value = "/not_friends")
	public List <User> findNotFriends () {
		User u = new User();
		u.setId(1);
		return getRepository().findNotFriends(u);
	}

	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		Friendship deletedFriendship = getRepository().findById(id);
		if (deletedFriendship.getApproved()){
			User user_x = deletedFriendship.getUser_x();
			User user_y = deletedFriendship.getUser_y();
			
			UserLog userXLog = new UserLog();
			UserLog userYLog = new UserLog();
			
			userXLog.setUser(user_x);
			userYLog.setUser(user_y);
			
			userXLog.setActivity("Dezfazer amizade");
			userYLog.setActivity("Desfazer amizade");
			
			userXLog.setTimestamp(new Date());
			userYLog.setTimestamp(new Date());
			
			userXLog.setTitle("Desfez amizade com "+user_y.getUserProfile().getName());
			userYLog.setTitle("Desfez amizade com "+user_x.getUserProfile().getName());
			
			logRepository.save(userXLog);
			logRepository.save(userYLog);
		}
		
		getRepository().delete(deletedFriendship);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Friendship update(@PathVariable Integer id){
		
		Friendship friendship = getRepository().findById(id);
		friendship.setApproved(true);
		
		User user_x = friendship.getUser_x();
		User user_y = friendship.getUser_y();
		
		UserLog userXLog = new UserLog();
		UserLog userYLog = new UserLog();
		
		userXLog.setUser(user_x);
		userYLog.setUser(user_y);
		
		userXLog.setActivity("Nova amizade");
		userYLog.setActivity("Nova amizade");
		
		userXLog.setTimestamp(new Date());
		userYLog.setTimestamp(new Date());
		
		userXLog.setTitle("Nova amizade com "+user_y.getUserProfile().getName());
		userYLog.setTitle("Nova amizade com "+user_x.getUserProfile().getName());
		
		logRepository.save(userXLog);
		logRepository.save(userYLog);
		
		return getRepository().update(friendship);
	}
	
	@RequestMapping(value ="/block_user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Friendship update(@RequestBody @Valid Friendship friendship,  BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		Friendship friendshipExisting =getRepository().hasFriendship(friendship.getUser_x().getId(), friendship.getUser_y().getId());
		if(friendshipExisting!=null){
			if(friendshipExisting.getBlocked_user()!=null){
				friendshipExisting.setBlocked_user(null);
				friendshipExisting.setApproved(true);
			}
			else{
				friendshipExisting.setBlocked_user(friendship.getBlocked_user());
				friendshipExisting.setApproved(null);
				
			}
			return getRepository().update(friendshipExisting);
		}
		else{
			friendship.setApproved(null);
			return getRepository().save(friendship);
		}	
		
	}
}
