import React, { useState } from "react";
import axios from "axios";
import { withRouter } from "../../withRouter";
import "./usermain.css"; // Ensure this file exists for styling

const BASE_URL = "http://localhost:8000/users";

function UserMain() {
  const [isLoginVisible, setIsLoginVisible] = useState(false);
  const [isSignUpVisible, setIsSignUpVisible] = useState(false);

  // State for login form
  const [loginData, setLoginData] = useState({
    mobileNumber: "",
    passwordHash: "",
  });

  // State for sign-up form
  const [signUpData, setSignUpData] = useState({
    mobileNumber: "",
    email: "",
    firstName: "",
    lastName: "",
    passwordHash: "",
    securityQuestion: "",
    securityAnswer: "",
    address: "",
  });

  // Security questions
  const securityQuestions = [
    "What is your favorite color?",
    "What is your favorite food?",
    "What is your father's name?",
  ];

  // Handle login form input change
  const handleLoginChange = (e) => {
    const { name, value } = e.target;
    setLoginData({ ...loginData, [name]: value });
  };

  // Handle sign-up form input change
  const handleSignUpChange = (e) => {
    const { name, value } = e.target;
    setSignUpData({ ...signUpData, [name]: value });
  };

  // Handle login form submission
  const handleLoginSubmit = (e) => {
    e.preventDefault();
    const { mobileNumber, passwordHash } = loginData;

    axios
      .get(`${BASE_URL}/userLogin/${mobileNumber}/${passwordHash}`)
      .then((response) => {
        if (response.data === "1") {
          localStorage.setItem("user", mobileNumber);
          window.location.href = "/menu"; // Redirect to menu page
        } else {
          alert("Invalid Credentials");
        }
      })
      .catch((error) => {
        console.error("Error during login:", error);
        alert("An error occurred during login. Please try again later.");
      });
  };

  // Handle sign-up form submission
  const handleSignUpSubmit = (e) => {
    e.preventDefault();
    axios
      .post(`${BASE_URL}/addUser`, signUpData)
      .then((response) => {
        console.log("User registered successfully:", response.data);
        alert("Registration successful! Please login.");
        setIsSignUpVisible(false); // Hide sign-up form after successful registration
      })
      .catch((error) => {
        console.error("Error during registration:", error);
        alert("An error occurred during registration. Please try again later.");
      });
  };

  return (
    <div className="user-main">
      {/* Fixed Buttons */}
      <div className="fixed-buttons">
        <button
          className="login-button"
          onClick={() => {
            setIsLoginVisible(!isLoginVisible);
            setIsSignUpVisible(false);
          }}
        >
          Login
        </button>
        <button
          className="signup-button"
          onClick={() => {
            setIsSignUpVisible(!isSignUpVisible);
            setIsLoginVisible(false);
          }}
        >
          Sign Up
        </button>
      </div>

      {/* Login Form */}
      {isLoginVisible && (
        <div className="form-container">
          <h2>Login</h2>
          <form onSubmit={handleLoginSubmit}>
            <div className="form-group">
              <label>Mobile Number</label>
              <input
                type="text"
                name="mobileNumber"
                value={loginData.mobileNumber}
                onChange={handleLoginChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                name="passwordHash"
                value={loginData.passwordHash}
                onChange={handleLoginChange}
                required
              />
            </div>
            <button type="submit" className="submit-button">
              Login
            </button>
          </form>
        </div>
      )}

      {/* Sign Up Form */}
      {isSignUpVisible && (
        <div className="form-container">
          <h2>Sign Up</h2>
          <form onSubmit={handleSignUpSubmit}>
            <div className="form-group">
              <label>Mobile Number</label>
              <input
                type="text"
                name="mobileNumber"
                value={signUpData.mobileNumber}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                name="email"
                value={signUpData.email}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>First Name</label>
              <input
                type="text"
                name="firstName"
                value={signUpData.firstName}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Last Name</label>
              <input
                type="text"
                name="lastName"
                value={signUpData.lastName}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                name="passwordHash"
                value={signUpData.passwordHash}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Security Question</label>
              <select
                name="securityQuestion"
                value={signUpData.securityQuestion}
                onChange={handleSignUpChange}
                required
              >
                <option value="">Select a security question</option>
                {securityQuestions.map((question, index) => (
                  <option key={index} value={question}>
                    {question}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Security Answer</label>
              <input
                type="text"
                name="securityAnswer"
                value={signUpData.securityAnswer}
                onChange={handleSignUpChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Address</label>
              <input
                type="text"
                name="address"
                value={signUpData.address}
                onChange={handleSignUpChange}
              />
            </div>
            <button type="submit" className="submit-button">
              Sign Up
            </button>
          </form>
        </div>
      )}
    </div>
  );
}

export default withRouter(UserMain);