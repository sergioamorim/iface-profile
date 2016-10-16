package br.ufal.ic.iface_profile.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.model.profile.CivilStatus;
import br.ufal.ic.iface_profile.repository.interfaces.profile.CivilStatusRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value="/civil_status")
public class CivilStatusController extends AbstractController<CivilStatus, Integer> {
	
	@Autowired
	@Qualifier("civilStatusRepository")
	private CivilStatusRepositoryInterface repository;
	
	@Override
	protected CivilStatusRepositoryInterface getRepository(){
		return this.repository;
	}
	
	@RequestMapping(value="/find_civil_status_by_gender/{id}")
	@ResponseBody
	public List<CivilStatus> findCivilStatusByGender(@PathVariable Integer id){
		return getRepository().findCivilStatusByGender(id);
	}
}
