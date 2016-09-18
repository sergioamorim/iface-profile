package br.ufal.ic.iface_profile.repository.classes.friendship;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.friendship.FriendshipRepositoryInterface;

@Component
public class FriendshipRepository extends GenericHibernateRepository<Friendship, Integer>
		implements FriendshipRepositoryInterface {

	@Override
	public List<Friendship> findFriends(User u) {
		List<Friendship> friendships = this.findByCriteria(
				Restrictions.and(
						Restrictions.or(
							Restrictions.eq("user_x", u),
							Restrictions.eq("user_y", u))), 
						Restrictions.eq("approved", true));
		return friendships;
	}

	@Override
	public void sendFriendRequest(User x, User y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Friendship> checkFriendRequests(User u) {
		List<Friendship> friendRequests = this.findByCriteria(
				Restrictions.and(
						Restrictions.or(
							Restrictions.eq("user_x", u),
							Restrictions.eq("user_y", u))), 
						Restrictions.eq("approved", false));
		return friendRequests;
	}

	@Override
	public List<User> findNotFriends(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
