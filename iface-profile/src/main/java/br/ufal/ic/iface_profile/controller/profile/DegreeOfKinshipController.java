package br.ufal.ic.iface_profile.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.repository.interfaces.profile.DegreeOfKinshipRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/degree_of_kinship")
public class DegreeOfKinshipController extends AbstractController<DegreeOfKinship, Integer>{
	
	@Autowired
	@Qualifier("degreeOfKinshipRepository")
	private DegreeOfKinshipRepositoryInterface repository;
	
	@Override
	public DegreeOfKinshipRepositoryInterface getRepository(){
		return this.repository;
	}
	
	@RequestMapping(value = "/find_degree_of_kinship_by_gender/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public List<DegreeOfKinship> findDegreeOfKinshipByGender(@PathVariable Integer id){
		return getRepository().findDegreeOfKinshipByGender(id);
	}
	
	
}
