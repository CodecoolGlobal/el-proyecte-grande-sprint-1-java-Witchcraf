import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, {useState} from 'react';
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Home from "./pages/indexPage/home";
import Search from "./pages/search";
import ServiceProviders from "./pages/serviceProviders";
import Login from "./pages/loginRegPage/login";
import Registration from "./pages/loginRegPage/registration";
import Profile from "./pages/profile";


function App() {
    const [user, setUser] = useState('');


    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/search" element={<Search />} />
                <Route path="/service-providers" element={<ServiceProviders />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/profile" element={<Profile />} />
            </Routes>
        </BrowserRouter>
    );
}




export default App;
