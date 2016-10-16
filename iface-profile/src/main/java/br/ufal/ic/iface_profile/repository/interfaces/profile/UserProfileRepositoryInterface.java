package br.ufal.ic.iface_profile.repository.interfaces.profile;

import java.util.List;

import br.ufal.ic.iface_profile.model.profile.UserProfile;
import br.ufal.ic.iface_profile.repository.interfaces.RepositoryInterface;

public interface UserProfileRepositoryInterface extends RepositoryInterface<UserProfile, Integer>{

	public UserProfile findUserProfileById(Integer x);
	
	public List<UserProfile> findUserProfileByName(String name);
}
