package br.ufal.ic.iface_profile.controller.friendship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
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
}
