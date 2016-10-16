package br.ufal.ic.iface_profile.model.profile;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="usercivilstatus")
public class UserCivilStatus {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private CivilStatus myCivilStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private Relationship myRelationship;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
