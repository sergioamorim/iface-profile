package br.ufal.ic.iface_profile.model.friendship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table (name = "userfriendship")

public class UserFriendship {

	@Id
	@GeneratedValue
	
	private int id;
	private User user_x;
	private User user_y;
	private boolean approved;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public User getUser_x() {
		return user_x;
	}
	public void setUser_x(User user_x) {
		this.user_x = user_x;
	}
	public User getUser_y() {
		return user_y;
	}
	public void setUser_y(User user_y) {
		this.user_y = user_y;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
