import { PERMISSIONS } from "../constants/permission-maps";
import { cloneElement } from "react";

const hasPermission = ({ permissions, scopes }) => {
  const scopesMap = {};
  scopes.forEach((scope) => {
    scopesMap[scope] = true;
  });

  return permissions.some((permission) => scopesMap[permission]);
};

export default function PermissionsGate({
  children,
  role = null,
  RenderError = () => <></>,
  errorProps = null,
  scopes = [],
}) {
  if (typeof window === "undefined") return null;

  const permissions = PERMISSIONS[role];

  const permissionGranted = hasPermission({ permissions, scopes });

  if (!permissionGranted && !errorProps) return <RenderError />;

  if (!permissionGranted && errorProps) {
    return cloneElement(children, { ...errorProps });
  }

  return <>{children}</>;
}
