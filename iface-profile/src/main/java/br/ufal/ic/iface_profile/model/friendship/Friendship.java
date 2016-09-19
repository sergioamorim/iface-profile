package br.ufal.ic.iface_profile.model.friendship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table (name = "userfriendship")

public class Friendship {

	@Id
	@GeneratedValue
	
	private Integer id;
	
	@ManyToOne
	private User user_x;
	
	@ManyToOne
	private User user_y;
	
	private Boolean approved;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
}
