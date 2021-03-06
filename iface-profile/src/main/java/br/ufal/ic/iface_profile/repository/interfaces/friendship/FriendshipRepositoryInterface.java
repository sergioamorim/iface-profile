package br.ufal.ic.iface_profile.repository.interfaces.friendship;

import java.util.List;

import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface FriendshipRepositoryInterface extends RepositoryInterface <Friendship, Integer>{
	
	public List<User> findNotFriends (User u);
	public List<User> findFriends (Integer x);
	public List<Friendship> findFriendshipRequests(Integer id);
	public List<UserProfile> findFriendsByName (Integer id_user, String name);
	public Friendship hasFriendship (Integer id_1, Integer id_2);
}
