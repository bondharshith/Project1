import React, { useState, useEffect } from 'react';
import AuthService from '../services/authService';
import Menu from './menu';

const User = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        AuthService.getUserBoard().then(response => {
            setMessage(response.data);
        }).catch(error => {
            setMessage("Error fetching user data");
        });
    }, []);

    return (
        <div>
            <Menu /> <br/><br/>
            {message}
        </div>
    );
}

export default User;
