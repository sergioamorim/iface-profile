package br.ufal.ic.iface_profile.repository.interfaces.profile;

import java.util.List;

import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface DegreeOfKinshipRepositoryInterface extends RepositoryInterface<DegreeOfKinship, Integer>{
	public List<DegreeOfKinship> findDegreeOfKinshipByGender(Integer x);
	public void initializeDegreeOfKinships(List<Gender> g);
}
