package it.fides.project.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.project.models.entities.RoleEntity;
import it.fides.project.models.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<RoleEntity> getAllRoles() {
		return roleRepo.findAll();
	}
	
	public RoleEntity getRole(Long id) {
		return roleRepo.findById(id).get();
	}
	
	public RoleEntity insertRole(RoleEntity roleEntity) {
		return roleRepo.save(roleEntity);
	}
	
	public RoleEntity updateRole(Long id, RoleEntity roleEntity) {
		RoleEntity role = getRole(id);
		RoleEntity updatedRole = null;
		
		if (role != null) {
			role.setIdRole(roleEntity.getIdRole());
			role.setLabelRole(roleEntity.getLabelRole());
			
			updatedRole = roleRepo.save(role);
		}
		return updatedRole;
	}
	
	public void deleteRole(Long id) {
		roleRepo.deleteById(id);
	}
}
