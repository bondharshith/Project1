import React, { useState } from 'react';
import AuthService from '../services/authService';
import { useNavigate } from 'react-router-dom';
import Menu from './menu';

const Login = () => {
    const [data, setState] = useState({
        username: '',
        password: '',
        role: 'ROLE_USER'
    });

    const handleChange = (event) => {
        setState({
            ...data, [event.target.name]: event.target.value
        });
    };

    const navigate = useNavigate();

    const validate = (e) => {
        e.preventDefault();

        AuthService.login(data.username, data.password).then(response => {
            alert("Result is " + response);
            console.log(response);

           
            if (data.role === 'ROLE_USER') {
                navigate("/user");
            } else if (data.role === 'ROLE_ADMIN') {
                navigate("/admin");
            }
        });
    };

    return (
        <div>
            <Menu /><br /><br />
            <form onSubmit={validate}>
                <label>USERNAME </label>
                <input
                    type="text"
                    name="username"
                    value={data.username}
                    onChange={handleChange}
                />
                <br /><br />

                <label>PASSWORD </label>
                <input
                    type="password"
                    name="password"
                    value={data.password}
                    onChange={handleChange}
                />
                <br /><br />

                <label>ROLE </label>
                <select
                    name="role"
                    value={data.role}
                    onChange={handleChange}
                >
                    <option value="ROLE_USER">ROLE_USER</option>
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select>
                <br /><br />

                <input type="submit" value="Login" />
                <br /><br />
            </form>
        </div>
    );
};

export default Login;
