import React, { useState } from 'react';
import AuthService from "../services/authService";
import Menu from "./menu";
import './register.css';

const Register = () => {
    const [data, setState] = useState({
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        roles: 'ROLE_USER' 
    });
    const [errors, setErrors] = useState({});

    const handleChange = (event) => {
        setState({
            ...data, [event.target.name]: event.target.value
        });
    };

    const show = (e) => {
        e.preventDefault();
        const newErrors = validateForm(data);
        setErrors(newErrors);

        if (Object.keys(newErrors).length === 0) {
            AuthService.register(data.username, data.email, data.password, data.roles)
                .then(response => {
                    alert(response.data);
                });
        }
    };

    const validateForm = (data) => {
        const errors = {};

        if (!data.username.trim()) {
            errors.username = 'Username is required';
        } else if (data.username.length < 4) {
            errors.username = 'Username must be at least 4 characters long';
        }

        if (!data.email.trim()) {
            errors.email = 'Email is required';
        } else if (!/\S+@\S+\.\S+/.test(data.email)) {
            errors.email = 'Email is invalid';
        }

        if (!data.password) {
            errors.password = 'Password is required';
        } else if (data.password.length < 8) {
            errors.password = 'Password must be at least 8 characters long';
        }

        if (data.confirmPassword !== data.password) {
            errors.confirmPassword = 'Passwords do not match';
        }

        return errors;
    };

    return (
        <div>
            <Menu /><br /><br />
            <center>
                <form onSubmit={show}>
                    <label>USERNAME :- </label>
                    <input
                        type="text"
                        name="username"
                        value={data.username}
                        onChange={handleChange}
                    />
                    {errors.username && <span className="error-message">{errors.username}</span>}
                    <br /><br />

                    <label>PASSWORD :- </label>
                    <input
                        type="password"
                        name="password"
                        value={data.password}
                        onChange={handleChange}
                    />
                    {errors.password && <span className="error-message">{errors.password}</span>}
                    <br /><br />

                    <label>CONFIRM PASSWORD :- </label>
                    <input
                        type="password"
                        name="confirmPassword"
                        value={data.confirmPassword}
                        onChange={handleChange}
                    />
                    {errors.confirmPassword && <span className="error-message">{errors.confirmPassword}</span>}
                    <br /><br />

                    <label>EMAIL :- </label>
                    <input
                        type="text"
                        name="email"
                        value={data.email}
                        onChange={handleChange}
                    />
                    {errors.email && <span className="error-message">{errors.email}</span>}
                    <br /><br />

                    <label>ROLES :- </label>
                    <select
                        name="roles"
                        value={data.roles}
                        onChange={handleChange}
                    >
                        <option value="ROLE_USER">ROLE_USER</option>
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    </select>
                    <br /><br />

                    <input type="submit" value="ADD-USER" />
                </form>
            </center>
        </div>
    );
};

export default Register;
