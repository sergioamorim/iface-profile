package br.ufal.ic.iface_profile.repository.interfaces.infrastructure;

import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface UserRepositoryInterface extends RepositoryInterface<User, Integer>{
	public User getUserByUsername(Integer id);
}
