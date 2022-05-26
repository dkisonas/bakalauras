import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import http from "../../../services/http-service";

export default NextAuth({
  providers: [
    CredentialsProvider({
      name: "Elogbook",

      credentials: {
        username: {
          label: "Username",
          type: "username",
          placeholder: "elogbookuser",
        },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        const { username, password } = credentials;

        const user = await http
          .post("/auth/signin", {
            username,
            password,
          })
          .then((response) => {
            if (response.status === 200) {
              return response.data;
            }
          })
          .catch((error) => {
            console.log(error.message);
            console.log(error.response?.data);
            return null;
          });
        if (user) {
          return user;
        }
        return null;
      },
    }),
  ],

  session: {
    strategy: "jwt",
    maxAge: 24 * 60 * 60,
  },

  callbacks: {
    async jwt({ token, user }) {
      if (user?.token) {
        token.token = user.token;
      }
      if (user?.username) {
        token.name = user.username;
      }
      if (user?.role) {
        token.role = user.role;
      }
      return token;
    },

    async session({ session, token }) {
      if (token?.token) {
        session.token = token.token;
      }
      if (token?.role) {
        session.role = token.role;
      }
      return session;
    },
  },

  theme: {
    colorScheme: "auto",
    logo: "http://getdrawings.com/cliparts/largemouth-bass-clipart-5.png",
  },
});
