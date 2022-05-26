import { signIn } from "next-auth/react";
import { useSession } from "next-auth/react";
import { useEffect } from "react";
import LoadingScreen from "../components/LoadingScreen";

export default function Auth({ children }) {
  const { data: session, status } = useSession({ required: true });
  const loading = status === "loading";
  const hasUser = !!session?.user;
  useEffect(() => {
    if (!loading && !hasUser) {
      signIn();
    }
  }, [loading, hasUser]);
  if (loading || !hasUser) {
    return <LoadingScreen />;
  }
  return children;
}
