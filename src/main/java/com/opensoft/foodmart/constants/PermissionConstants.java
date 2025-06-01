package com.opensoft.foodmart.constants;

import com.opensoft.foodmart.domain.BaseFoodDomain;

public class PermissionConstants extends BaseFoodDomain {

	public static class CanAddUser extends  PermissionAbstract{
		public static String PERMISSION = "can.add.user";
		public static String DESCRIPTION = "Can add user to the system";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	public static class CanDeleteUser extends  PermissionAbstract{
		public static String PERMISSION = "can.delete.user";
		public static String DESCRIPTION = "Can delete user from the system";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	public static class CanEditUser extends  PermissionAbstract{
		public static String PERMISSION = "can.edit.user";
		public static String DESCRIPTION = "Can edit system user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}


	public static class CanToggleUserActive extends  PermissionAbstract{
		public static String PERMISSION = "can.toggle.user.active";
		public static String DESCRIPTION = "Can toggle user status";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanCreateRole extends  PermissionAbstract{
		public static String PERMISSION = "can.create.role";
		public static String DESCRIPTION = "Can create a system role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanEditRole extends  PermissionAbstract{
		public static String PERMISSION = "can.edit.role";
		public static String DESCRIPTION = "Can edit system role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanDisableRole extends  PermissionAbstract{
		public static String PERMISSION = "can.disable.role";
		public static String DESCRIPTION = "Can disable system role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanassignRoleToUser extends  PermissionAbstract{
		public static String PERMISSION = "can.assign.user.role";
		public static String DESCRIPTION = "Can assign a role to user";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanAssignPermissionToRole extends  PermissionAbstract{
		public static String PERMISSION = "can.assign.role.permission";
		public static String DESCRIPTION = "Can assign permission to a role";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	public static class CanCreateStore extends  PermissionAbstract{
		public static String PERMISSION = "can.create.store";
		public static String DESCRIPTION = "Can create food store";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	public static class CanDisableStore extends  PermissionAbstract{
		public static String PERMISSION = "can.disable.store";
		public static String DESCRIPTION = "Can enable and disable store";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	

	public static class CanCreateProduct  extends  PermissionAbstract{
		public static String PERMISSION = "can.create.product";
		public static String DESCRIPTION = "Can create product in the system";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	

	public static class CanEditProduct extends  PermissionAbstract{
		public static String PERMISSION = "can.edit.product";
		public static String DESCRIPTION = "Can edit a product in the system";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	public static class CanDeleteProduct extends  PermissionAbstract{
		public static String PERMISSION = "can.delete.product";
		public static String DESCRIPTION = "Can delete product";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	public static class CanUpdateStock extends  PermissionAbstract{
		public static String PERMISSION = "can.update.stock";
		public static String DESCRIPTION = "Can update product stock";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}

	public static class CanAddItemOnStock extends  PermissionAbstract{
		public static String PERMISSION = "can.add.item.onsale";
		public static String DESCRIPTION = "Can add a product for display";
		@Override
		public String getPERMISSION() {
			return PERMISSION;
		}

		@Override
		public String getDESCRIPTION() {
			return DESCRIPTION;
		}

	}
	
	
}
