import React,{useState} from 'react';
import { Link } from 'react-router-dom';
import './menu.css'

const Menu = ()=>{


return(
    <div>
         <video autoPlay loop muted className="video-bg">
                <source src="/gear-5-technique.3840x2160.mp4" type="video/mp4" />
                Your browser does not support the video tag.
            </video>
        <nav>
            <Link to="/register">REGISTER</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/login">LOGIN</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/admin">ADMIN</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/user">USER</Link>
        </nav>
    </div>
)
}
export default Menu