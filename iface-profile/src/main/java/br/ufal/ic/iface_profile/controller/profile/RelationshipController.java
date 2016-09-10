package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/relationship")
public class RelationshipController extends AbstractController<Relationship, Integer>{

	@Autowired
	@Qualifier("relationshipRepository")
	private RelationshipRepositoryInterface repository;
	
	protected RelationshipRepositoryInterface getRepository(){
		return this.repository;
	}
}
