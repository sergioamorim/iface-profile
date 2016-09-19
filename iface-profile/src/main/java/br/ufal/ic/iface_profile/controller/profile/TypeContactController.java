package br.ufal.ic.iface_profile.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.TypeContact;
import br.ufal.ic.iface_profile.repository.interfaces.profile.TypeContactRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/type_contact")
public class TypeContactController extends AbstractController<TypeContact,Integer >{

	@Autowired
	@Qualifier("typeContactRepository")
	private TypeContactRepositoryInterface repository;

	protected TypeContactRepositoryInterface getRepository() {
		return repository;
	}

}
