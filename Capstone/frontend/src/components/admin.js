import React, { useState, useEffect } from 'react';
import AuthService from '../services/authService';
import Menu from './menu';
import Login from './login';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

const Admin = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        AuthService.getAdminBoard().then(response => {
            setMessage(response.data);
        }).catch(error => {
            setMessage("Can't fetch admin Details. Please Login");
        });
    }, []);

    return (
        <div>
            <Menu /> <br/><br/>
            {message}
            
            {/* <button path="/login" element ={<Login/>}/>    */}
        </div>
    );
}

export default Admin;
