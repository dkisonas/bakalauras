import React, { useState } from "react";
import fishLogo from "../components/icon.png";
import AuthService from "../services/auth-service";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
  } = useForm();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleUsername = (e) => {
    e.preventDefault();
    setUsername(e.target.value);
  };

  const handlePassword = (e) => {
    e.preventDefault();
    setPassword(e.target.value);
  };

  const handleLogin = () => {
    setMessage("");
    setLoading(true);
    AuthService.login(username, password)
      .then(() => {
        navigate("/dashboard");
        window.location.reload();
      })
      .catch((error) => {
        const resMessage = error.response.data ? error.response.data : error.message;
        setLoading(false);
        setMessage(resMessage);
      });
  };

  return (
    <div className="min-h-full flex flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <img className="mx-auto h-20 w-auto" src={fishLogo} alt="Workflow" />
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          Sign in to your account
        </h2>
      </div>

      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
          <form
            className="space-y-6"
            onSubmit={handleSubmit(() => {
              handleLogin();
            })}
          >
            <div>
              <label
                htmlFor="username"
                className="block text-sm font-medium text-gray-700"
              >
                Username
              </label>
              <div className="mt-1">
                <input
                  id="username"
                  name="username"
                  type="username"
                  value={username}
                  onChangeCapture={(e) => handleUsername(e)}
                  autoComplete="username"
                  required
                  {...register("username", { required: true })}
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-700"
              >
                Password
              </label>
              <div className="mt-1">
                <input
                  id="password"
                  name="password"
                  type="password"
                  value={password}
                  onChangeCapture={(e) => handlePassword(e)}
                  autoComplete="current-password"
                  required
                  {...register("password", { required: true })}
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
              </div>
            </div>

            <div>
              <button
                type="submit"
                disabled={loading}
                
                className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 disabled:opacity-50"
              >
                Sign in
              </button>
              {message && (
                <p className="mt-5 flex justify-center mx-auto text-sm text-red-600">{message}</p>
              )}
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
