import axios from "axios";
import authHeader from "./AuthHeader";

const API_URL = "http://localhost:8000/auth/";

class AuthService {

    getAdminBoard(){ 
        return axios.get(API_URL + 'admin/adminProfile',{
            headers: authHeader()
        });
    }
    getUserBoard(){
        return axios.get(API_URL+'user/userProfile',
            {headers:authHeader()
                
            });
    }

    login(username,password){
        return axios.post(API_URL+"generateToken",{
            username,
            password
        })
        .then(response=>{
            alert(response.data)
            alert("token generated")
            localStorage.setItem("token",JSON.stringify(response.data))
            return response.data;
        })
    }

    register(name,email,password,roles){
        return axios.post(API_URL + "addNewUser",{
            name,
            email,
            password,
            roles
        });
    }
}

export default new AuthService();