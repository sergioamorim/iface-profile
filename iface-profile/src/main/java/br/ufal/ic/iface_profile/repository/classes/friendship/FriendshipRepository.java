package br.ufal.ic.iface_profile.repository.classes.friendship;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.friendship.Friendship;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.profile.UserProfile;
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
	public List<User> findFriends(Integer x){
		UserRepository ur = new UserRepository(this.getSession());
		DetachedCriteria userSubquery1 = DetachedCriteria.forClass(User.class, "f")
				.setProjection(Projections.property("f.id"))
				;
		DetachedCriteria userSubquery2 = DetachedCriteria.forClass(Friendship.class, "f")
				.setProjection(Projections.property("f.user_y.id"))
				.add(Restrictions.and(
						Restrictions.eq("user_x.id",x),
						Restrictions.eq("approved",true))
						)
				;
		
		DetachedCriteria userSubquery3 = DetachedCriteria.forClass(Friendship.class, "f")
				.setProjection(Projections.property("f.user_x.id"))
				.add(Restrictions.and(
						Restrictions.eq("user_y.id",x),
						Restrictions.eq("approved",true))
						)
				;
				
		List<User> list1 = 	ur.findByCriteria(Restrictions.and(	
				(Subqueries.propertyIn("id", userSubquery1)),
				(Subqueries.propertyIn("id", userSubquery2))
			));
		List<User> list2 =ur.findByCriteria(Restrictions.and(	
				(Subqueries.propertyIn("id", userSubquery1)),
				(Subqueries.propertyIn("id", userSubquery3))
			));
		 list1.addAll(list2);
		 return list1;
	}
	

	public List<Friendship> findFriendshipRequests(Integer id){
		return this.findByCriteria(Restrictions.and(
				Restrictions.eq("user_y.id", id),
			    Restrictions.eq("approved",false)
				)	
		 );
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findFriendsByName (Integer id_user, String name){
		List<UserProfile> u = new ArrayList<UserProfile>();
		
		Criteria c = getSession().createCriteria(Friendship.class, "friendship");
		c.createAlias("friendship.user_x", "user_x"); 
		c.createAlias("friendship.user_y", "user_y");
		c.createAlias("user_x.userProfile", "userProfileX");
		c.createAlias("user_y.userProfile", "userProfileY");
		c.add(Restrictions.and(
				Restrictions.or(
						Restrictions.and(
								Restrictions.like("userProfileX.name", "%"+name+"%"),
								Restrictions.eq("user_y.id", id_user)),
						Restrictions.and(
								Restrictions.like("userProfileY.name", "%"+name+"%"),
								Restrictions.eq("user_x.id", id_user))),
				Restrictions.eq("friendship.approved", true)
						));
		List<Friendship> f = c.list();
		
		
		for(Friendship aux : f){
			if(aux.getUser_x().getId() == id_user){
				u.add(aux.getUser_y().getUserProfile());
			}else{
				u.add(aux.getUser_x().getUserProfile());
			}
		}
		
		return u;		
	}

	@Override
	public Friendship hasFriendship(Integer id_1, Integer id_2) {
		try{
		return this.findByCriteria(Restrictions.or(
				Restrictions.and(
						Restrictions.eq("user_x.id", id_1),
						Restrictions.eq("user_y.id", id_2)
				),
				Restrictions.and(
						Restrictions.eq("user_y.id", id_1),
						Restrictions.eq("user_x.id", id_2)
				)
				
		)).get(0);
		}catch(IndexOutOfBoundsException e){
			return	null;
		}
	}

}
