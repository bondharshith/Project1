import React, { Component } from 'react';
import axios from 'axios';

export default class UserInfo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: {}, // Holds user information
      error: null // Handles API errors
    };
  }

  componentDidMount() {
    // Retrieve the mobile number from localStorage
    const mobile_number = localStorage.getItem("user");

    if (!mobile_number) {
      console.error("No mobile number found in localStorage");
      this.setState({ error: "No user found. Please log in again." });
      return;
    }

    console.log("Retrieved mobile_number:", mobile_number);

    // Fetch user details from API
    axios
      .get(`http://localhost:8000/users/getUserByMobile/${67785589}`)
      .then(response => {
        this.setState({ user: response.data });
        console.log("User data fetched:", response.data);
      })
      .catch(error => {
        console.error("Error fetching user data:", error);
        this.setState({ error: "Failed to load user data. Please try again later." });
      });
  }

  render() {
    const { user, error } = this.state;

    return (
      <div className="component-userinfo">
        <h3 align="center">User Information</h3>

        {error ? (
          <p style={{ color: "red", textAlign: "center" }}>{error}</p>
        ) : (
          <table border="3" align="center">
            <tbody>
              <tr>
                <th>Mobile Number</th>
                <td>{user.mobileNumber || "N/A"}</td>
              </tr>
              <tr>
                <th>Email</th>
                <td>{user.email || "N/A"}</td>
              </tr>
              <tr>
                <th>Full Name</th>
                <td>{`${user.firstName || ""} ${user.lastName || ""}`.trim() || "N/A"}</td>
              </tr>
              <tr>
                <th>Password</th>
                <td>{user.passwordHash || "N/A"}</td>
              </tr>
              <tr>
                <th>Security Question</th>
                <td>{user.securityQuestion || "N/A"}</td>
              </tr>
              <tr>
                <th>Security Answer</th>
                <td>{user.securityAnswer || "N/A"}</td>
              </tr>
              <tr>
                <th>Address</th>
                <td>{user.address || "N/A"}</td>
              </tr>
              <tr>
                <th>Alternate Mobile Number</th>
                <td>{user.alternateMobileNumber || "N/A"}</td>
              </tr>
              <tr>
                <th>Account Status</th>
                <td>{user.accountStatus || "N/A"}</td>
              </tr>
            </tbody>
          </table>
        )}
      </div>
    );
  }
}
