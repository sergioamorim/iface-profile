package br.ufal.ic.iface_profile.repository.interfaces.profile;

import java.util.List;

import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface CivilStatusRepositoryInterface extends RepositoryInterface<CivilStatus, Integer>{
	public void initializeCivilStatus(List<Gender> g);
	public List<CivilStatus> findCivilStatusByGender(Integer id);
}
