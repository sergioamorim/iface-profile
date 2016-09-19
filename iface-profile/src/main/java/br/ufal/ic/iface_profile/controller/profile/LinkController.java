package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.Link;
import br.ufal.ic.iface_profile.repository.interfaces.profile.LinkRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/link")
public class LinkController extends AbstractController<Link, Integer>{

	@Autowired
	@Qualifier("linkRepository")
	private LinkRepositoryInterface repository;
	
	protected LinkRepositoryInterface getRepository(){
		return this.repository;
	}
}
