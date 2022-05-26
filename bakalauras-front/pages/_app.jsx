import "../styles/globals.css";
import { SessionProvider } from "next-auth/react";
import Auth from "../components/Auth";
import Layout from "../components/Layout";

export default function MyApp({
  Component,
  pageProps: { session, ...pageProps },
}) {
  return (
    <Layout>
      <SessionProvider session={session}>
        {Component.auth ? (
          <Auth role={Component.auth.role}>
            <Component {...pageProps} />
          </Auth>
        ) : (
          <Component {...pageProps} />
        )}
      </SessionProvider>
    </Layout>
  );
}
