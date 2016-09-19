package br.ufal.ic.iface_profile.controller.profile;

import java.io.IOException;
import java.util.Date;

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
import br.ufal.ic.iface_profile.model.storytelling.UserLog;
import br.ufal.ic.iface_profile.repository.classes.storytelling.UserLogRepository;
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
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		Relationship deletedRelationship = getRepository().findById(id);
		
		User user = deletedRelationship.getSender();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Relationship ending");
		
		userLog.setTitle(user.getName()+
							" ended "+
								deletedRelationship.getRelationshipType()+
									" with "+
										deletedRelationship.getReceiver().getName());
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);
		
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
		
		userLog.setActivity("Relationship began");
		userLog.setTitle(user.getName()+" began "+
							newRelationship.getRelationshipType()+
								" with "+
									newRelationship.getReceiver().getName());
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().save(newRelationship);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Relationship update(@RequestBody @Valid Relationship newRelationship, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newRelationship.getSender();
		userLog.setUser(user);
		
		userLog.setActivity("Relationship modification");
		
		userLog.setTitle(user.getName()+
							" changed the relationship with "+
								newRelationship.getReceiver().getName()+
									" to "+
										newRelationship.getRelationshipType());
		
		userLog.setTimestamp(new Date());
		
		UserLogRepository userLogRepository = new UserLogRepository();
		userLogRepository.save(userLog);

		return getRepository().update(newRelationship);
	}
	
}
