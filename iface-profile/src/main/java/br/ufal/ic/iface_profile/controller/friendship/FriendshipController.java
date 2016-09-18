package br.ufal.ic.iface_profile.controller.friendship;

import java.util.Date;
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
		
		userXLog.setActivity("Desfazer amizade");
		userYLog.setActivity("Desfazer amizade");
		
		userXLog.setTimestamp(new Date());
		userYLog.setTimestamp(new Date());
		
		userXLog.setTitle("Desfez a amizade com"+user_y.getName());
		userYLog.setTitle("Desfez a amizade com"+user_x.getName());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userXLog);
		userLogRepository.save(userYLog);
		
		getRepository().delete(deletedFriendship);
	}
}
