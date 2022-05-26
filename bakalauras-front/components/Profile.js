import { useSession } from "next-auth/react";
import { SCOPES } from "../constants/permission-maps";
import PermissionsGate from "./PermissionsGate";

export default function Profile() {
  const { data: session } = useSession();

  return (
    <>
      <PermissionsGate role={session.role} scopes={[SCOPES.canCreateUsers]}>
        <h1>Admin content</h1>
      </PermissionsGate>
      <PermissionsGate role={session.role} scopes={[SCOPES.canCreate]}>
        <h1>fmc worker and admin content</h1>
      </PermissionsGate>
      <PermissionsGate role={session.role} scopes={[SCOPES.canView]}>
        <h1>vmaster, admin, fmc worker content</h1>
      </PermissionsGate>
      <h1>Public content</h1>
      <span>User name: {session.user.name}</span>

      <form action="#">
        <PermissionsGate
          role={session.role}
          scopes={[SCOPES.canCreateUsers]}
          errorProps={{ disabled: true, placeholder: "disabled because not authorized" }}
        >
          <input type="text" name="fname" className="border"></input>
        </PermissionsGate>
      </form>
    </>
  );
}
