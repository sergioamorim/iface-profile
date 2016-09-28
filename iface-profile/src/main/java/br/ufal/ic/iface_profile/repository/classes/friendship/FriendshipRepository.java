package br.ufal.ic.iface_profile.repository.classes.friendship;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.classes.infrastructure.UserRepository;
import br.ufal.ic.iface_profile.repository.interfaces.friendship.FriendshipRepositoryInterface;

@Component
public class FriendshipRepository extends GenericHibernateRepository<Friendship, Integer>
		implements FriendshipRepositoryInterface {

	@Override
	public List<User> findNotFriends(User u) {
		UserRepository ur = new UserRepository(this.getSession());
		
		DetachedCriteria userSubquery1 = DetachedCriteria.forClass(Friendship.class, "f")
				.setProjection(Projections.property("f.user_x")).add(Restrictions.eq("user_y", u));
		
		DetachedCriteria userSubquery2 = DetachedCriteria.forClass(Friendship.class, "f")
				.setProjection(Projections.property("f.user_y")).add(Restrictions.eq("user_x", u));
		
		return ur.findByCriteria(Restrictions.and(
				Restrictions.and(
						Restrictions.not(Subqueries.propertyIn("id", userSubquery1)),
						Restrictions.not(Subqueries.propertyIn("id", userSubquery2))),
				Restrictions.not(Restrictions.eq("id", u.getId()))));
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findFriends(Integer x){
		SQLQuery q1 = this.getSession().createSQLQuery("SELECT * FROM user INNER JOIN userfriendship "
				+ "ON user.id=userfriendship.user_y_id "
				+ "WHERE (user_x_id=:id) AND approved=1").addEntity(User.class);
		SQLQuery q2 = this.getSession().createSQLQuery("SELECT * FROM user INNER JOIN userfriendship "
				+ "ON user.id=userfriendship.user_x_id "
				+ "WHERE (user_y_id=:id) AND approved=1").addEntity(User.class);
		q1.setInteger("id", x);
		q2.setInteger("id", x);
		List<User> ul = q1.list();
		ul.addAll(q2.list());
		return ul;
	}

}
