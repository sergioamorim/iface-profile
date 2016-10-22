package br.ufal.ic.iface_profile.controller.profile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.ufal.ic.iface_profile.controller.AbstractController;
import br.ufal.ic.iface_profile.exceptions.ValidationException;
import br.ufal.ic.iface_profile.model.infrastructure.User;
import br.ufal.ic.iface_profile.model.profile.Relationship;
import br.ufal.ic.iface_profile.model.profile.RelationshipWrapper;
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.profile.RelationshipTypeRepositoryInterface;
import br.ufal.ic.iface_profile.repository.interfaces.storytelling.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/relationship")
public class RelationshipController extends AbstractController<Relationship, Integer>{

	@Autowired
	@Qualifier("relationshipRepository")
	private RelationshipRepositoryInterface repository;
	
	@Autowired
	@Qualifier("relationshipTypeRepository")
	private RelationshipTypeRepositoryInterface relationshipTypeRepository;
	
	protected RelationshipTypeRepositoryInterface getRelationshipTypeRepository(){
		return this.relationshipTypeRepository;
	}
	protected RelationshipRepositoryInterface getRepository(){
		return this.repository;
	}
	
	
	@RequestMapping(value = "/find_relationship/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Relationship> findRelationship(@PathVariable Integer id){
		return getRepository().findRelationships(id);
	}
	
	@RequestMapping(value = "/find_requests/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Relationship> findRelationshipRequests(@PathVariable Integer id){
		return getRepository().findRelationshipRequests(id);

	}
	
	@RequestMapping(value = "/find_all_relationships/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Relationship> findAllRelationships(@PathVariable Integer id){
		return getRepository().findAllRelationships(id);

	}
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface logRepository;
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		Relationship deletedRelationship = getRepository().findById(id);
		
		User user = deletedRelationship.getSender();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Desfazer Relacionamento");
		
		userLog.setTitle(user.getUserProfile().getName()+
							" removou " +
								deletedRelationship.getReceiver().getUserProfile().getName() +
									" como " +
										deletedRelationship.getRelationshipType().getSenderDegreeOfKinship().getDegreeOfKinship());
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);
		
		getRepository().delete(deletedRelationship);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relationship save(@RequestBody @Valid Relationship newRelationship, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newRelationship.getSender();
		userLog.setUser(user);
		
		userLog.setActivity("Novo Relacionamento");
		userLog.setTitle(user.getUserProfile().getName() + 
							" adicionou " +
								newRelationship.getReceiver().getUserProfile().getName() +
								" como " +
									newRelationship.getRelationshipType().getSenderDegreeOfKinship().getDegreeOfKinship());
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().save(newRelationship);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Relationship update(@PathVariable Integer id){
		Relationship newRelationship = getRepository().findById(id);
		newRelationship.setStatusSolicitation(true);
		UserLog userLog = new UserLog();		
		User user = newRelationship.getSender();
		userLog.setUser(user);
		
		userLog.setActivity("Modificação de relacionamento");
		
		userLog.setTitle(user.getUserProfile().getName()+
							" aceitou "+
								newRelationship.getSender().getUserProfile().getName() +
									" como "+
										newRelationship.getRelationshipType().getReceiverDegreeOfKinship().getDegreeOfKinship());
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().update(newRelationship);
	}
	
	
	@RequestMapping(value = "/save_all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean saveAll(@RequestBody @Valid RelationshipWrapper listRelationship, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		for (Relationship u : listRelationship.getRelationships()){
			u.setRelationshipType(getRelationshipTypeRepository().findRelationshipTypeByGender(listRelationship.getGenderSenderId(), u.getRelationshipType().getReceiverDegreeOfKinship().getId()));
		}
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		for(Relationship r:listRelationship.getRelationships())
			getRepository().save(r);
		return true;
	}
	
}
