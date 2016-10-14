package br.ufal.ic.iface_profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.model.profile.DegreeOfKinship;
import br.ufal.ic.iface_profile.model.profile.Gender;
import br.ufal.ic.iface_profile.model.profile.RelationshipType;
import br.ufal.ic.iface_profile.repository.interfaces.profile.CivilStatusRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.profile.DegreeOfKinshipRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.profile.GenderRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/util_controller")
public class UtilController {
	
	@Autowired
	@Qualifier("genderRepository")
	private GenderRepositoryInterface genderRepository;
	
	@Autowired
	@Qualifier("relationshipTypeRepository")
	private RelationshipTypeRepositoryInterface relationshipTypeRepository;
	
	@Autowired
	@Qualifier("degreeOfKinshipRepository")
	private DegreeOfKinshipRepositoryInterface degreeOfKinshiprepository;
	
	@Autowired
	@Qualifier("civilStatusRepository")
	private CivilStatusRepositoryInterface civilStatusRepository;
	
	@RequestMapping(value="/init_civil_status")
	@ResponseBody
	public void initializeCivilStatus(){
		List<CivilStatus> cs = getCivilStatusRepository().findAll();
		if (cs.isEmpty()){
			getCivilStatusRepository().initializeCivilStatus(getGenderRepository().findAll());
		}
	}
	@RequestMapping(value="/init_genders")
	@ResponseBody
	public void initializeGenders(){
		List<Gender> gl = getGenderRepository().findAll();
		if (gl.isEmpty()){
			getGenderRepository().initializeGenders();
		}
	}
	
	@RequestMapping(value="/init_degree_of_kinships")
	@ResponseBody
	public void initializeDegreeOfKinships(){
		List<DegreeOfKinship> dofl = getDegreeOfKinshiprepository().findAll();
		if (dofl.isEmpty()){
			getDegreeOfKinshiprepository().initializeDegreeOfKinships(getGenderRepository().findAll());
		}
		
	}
	
	
	@RequestMapping(value="/init_relationship_types")
	@ResponseBody
	public void initializeRelationshipTypes(){
		List<RelationshipType> rtl = getRelationshipTypeRepository().findAll();
		if (rtl.isEmpty()){
			getRelationshipTypeRepository().initializeRelationshipTypes(getDegreeOfKinshiprepository().findDegreeOfKinshipByGender(1),
					getDegreeOfKinshiprepository().findDegreeOfKinshipByGender(2));
		}
	}
	
	@RequestMapping(value="/init_all")
	@ResponseBody
	public void initializeAll(){
		initializeGenders();
		initializeDegreeOfKinships();
		initializeRelationshipTypes();
		initializeCivilStatus();
	}

	public GenderRepositoryInterface getGenderRepository() {
		return genderRepository;
	}

	public void setGenderRepository(GenderRepositoryInterface genderRepository) {
		this.genderRepository = genderRepository;
	}

	public RelationshipTypeRepositoryInterface getRelationshipTypeRepository() {
		return relationshipTypeRepository;
	}

	public void setRelationshipTypeRepository(RelationshipTypeRepositoryInterface relationshipTypeRepository) {
		this.relationshipTypeRepository = relationshipTypeRepository;
	}

	public DegreeOfKinshipRepositoryInterface getDegreeOfKinshiprepository() {
		return degreeOfKinshiprepository;
	}

	public void setDegreeOfKinshiprepository(DegreeOfKinshipRepositoryInterface degreeOfKinshiprepository) {
		this.degreeOfKinshiprepository = degreeOfKinshiprepository;
	}
	public CivilStatusRepositoryInterface getCivilStatusRepository() {
		return civilStatusRepository;
	}
	public void setCivilStatusRepository(CivilStatusRepositoryInterface civilStatusRepository) {
		this.civilStatusRepository = civilStatusRepository;
	}

}
