package br.ufal.ic.iface_profile.repository.interfaces.profile;

import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface GenderRepositoryInterface extends RepositoryInterface<Gender, Integer>{
	public void initializeGenders();
}
