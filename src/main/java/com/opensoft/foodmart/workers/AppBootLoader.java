package com.opensoft.foodmart.workers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.opensoft.foodmart.domain.Role;
import com.opensoft.foodmart.domain.User;
import com.opensoft.foodmart.domain.UserRole;
import com.opensoft.foodmart.repository.RoleRepository;
import com.opensoft.foodmart.repository.UserRoleRepository;
import com.opensoft.foodmart.services.PermissionService;
import com.opensoft.foodmart.services.RoleService;
import com.opensoft.foodmart.services.UserService;

import jakarta.transaction.Transactional;


@Component
public class AppBootLoader implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private UserService useService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    
	@Override
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		this.roleService.createSuperRole();
		this.useService.createSuperUser();
		
//		var permissions = GlobalPermissionsConstants.scan();
//		permissions.forEach((permmsion, desc) -> {
//			var permission = Permission.builder().description(desc).name(permmsion).build();
//			this.permissionService.insertPermissionIfNotExistsOrUpdateDescription(permission);
//		});
//		
		Optional<Role> superRoleOpt =  this.roleRepository.findByName("SUPER_ADMIN");
		if(superRoleOpt.isPresent()) {
			
			var role = superRoleOpt.get();
		Optional<User> userOpt =  this.useService.findUserByPhone("700000000");
		if(userOpt.isPresent()) {
			var user = userOpt.get();
			var userRole =  UserRole.builder().role(role).user(userOpt.get()).creator(user).build();
			Optional<UserRole> userRoleOpt =  this.userRoleRepository.findByRoleAndUser(role,user);
			if(userRoleOpt.isEmpty()) {
				try {
					this.userRoleRepository.save(userRole);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
//			List<Permission> perms = this.permissionService.getAllPermissions();
//			perms.forEach(p->{
//				this.roleService.assignPermissionsNotAssignedToSuperRole(role, p);
//			});
		}
		
	}
}
