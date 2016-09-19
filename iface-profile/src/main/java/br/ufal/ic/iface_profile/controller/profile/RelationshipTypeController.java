package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/relationship_type")
public class RelationshipTypeController extends AbstractController<RelationshipType, Integer>{

	@Autowired
	@Qualifier("relationshipTypeRepository")
	private RelationshipTypeRepositoryInterface repository;
	
	@Override
	protected RelationshipTypeRepositoryInterface getRepository(){
		return this.repository;
	}
}
