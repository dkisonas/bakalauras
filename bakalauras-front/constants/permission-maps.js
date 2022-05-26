export const ROLES = {
  vesselMaster: "VESSEL_MASTER",
  fmcWorker: "FMC_WORKER",
  admin: "ADMIN",
};

export const SCOPES = {
  canCreate: "can-create",
  canEdit: "can-edit",
  canDelete: "can-delete",
  canView: "can-view",
  canCreateUsers: "can-create-users",
};

export const PERMISSIONS = {
  [ROLES.vesselMaster]: [SCOPES.canView],
  [ROLES.fmcWorker]: [
    SCOPES.canView,
    SCOPES.canEdit,
    SCOPES.canCreate,
    SCOPES.canDelete,
  ],
  [ROLES.admin]: [
    SCOPES.canView,
    SCOPES.canEdit,
    SCOPES.canCreate,
    SCOPES.canDelete,
    SCOPES.canCreateUsers,
  ],
};
