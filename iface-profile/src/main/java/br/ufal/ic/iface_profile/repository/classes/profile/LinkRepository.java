package br.ufal.ic.iface_profile.repository.classes.profile;

import org.springframework.stereotype.Component;

import br.ufal.ic.iface_profile.model.profile.Link;
import br.ufal.ic.iface_profile.repository.classes.GenericHibernateRepository;
import br.ufal.ic.iface_profile.repository.interfaces.profile.LinkRepositoryInterface;

@Component
public class LinkRepository extends GenericHibernateRepository<Link, Integer>
				implements LinkRepositoryInterface{

}
