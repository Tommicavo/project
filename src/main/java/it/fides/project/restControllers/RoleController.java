package it.fides.project.restControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.project.models.entities.RoleEntity;
import it.fides.project.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public List<RoleEntity> getAllRoles() {
		return roleService.getAllRoles();
	}
	
	@GetMapping("/{id}")
	public RoleEntity getRole(@PathVariable Long id) {
		return roleService.getRole(id);
	}
	
	@PostMapping
	public RoleEntity insertRole(@RequestBody RoleEntity roleEntity) {
		return roleService.insertRole(roleEntity);
	}
	
	@PutMapping("/{id}")
	public RoleEntity updateRole(@PathVariable Long id, @RequestBody RoleEntity roleEntity) {
		return roleService.updateRole(id, roleEntity);
	}
	
	@DeleteMapping
	public void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
}
