package br.ufal.ic.iface_profile.repository.interfaces.storytelling;

import java.util.List;

import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface UserLogRepositoryInterface extends RepositoryInterface<UserLog, Integer>{
	public List<UserLog> getLogs(User u);
}