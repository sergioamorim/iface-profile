package br.ufal.ic.iface_profile.repository.interfaces.friendship;

import java.util.List;

import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface FriendshipRepositoryInterface extends RepositoryInterface <Friendship, Integer>{
	
	public List<Friendship> findFriends(User u);
	
	public void sendFriendRequest (User x, User y);
	
	public List<Friendship> checkFriendRequests (User u);
	
	public List<User> findNotFriends (User u);
}
