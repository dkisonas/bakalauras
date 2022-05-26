import { useSession, signOut } from "next-auth/react";

export default function Login() {
  const { data: session } = useSession();

  return (
    <>
      Signed in as {session.user.name} role = {session.role} <br />
      <button onClick={() => signOut()}>Sign out</button>
    </>
  );
}
