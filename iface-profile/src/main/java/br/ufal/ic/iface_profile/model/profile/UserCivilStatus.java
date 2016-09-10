package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufal.ic.iface_profile.model.infrastructure.User;

@Entity
@Table(name="usercivilstatus")
public class UserCivilStatus {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User usuario;
	
	@ManyToOne
	private CivilStatus myCivilStatus;
	
	@ManyToOne
	private Relationship myRelationship;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public CivilStatus getMyCivilStatus() {
		return myCivilStatus;
	}
	public void setMyCivilStatus(CivilStatus myCivilStatus) {
		this.myCivilStatus = myCivilStatus;
	}
	public Relationship getMyRelationship() {
		return myRelationship;
	}
	public void setMyRelationship(Relationship myRelationship) {
		this.myRelationship = myRelationship;
	}
	
}
