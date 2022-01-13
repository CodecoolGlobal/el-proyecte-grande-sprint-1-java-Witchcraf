import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Home from "./pages/indexPage/home";
import Search from "./pages/search";
import ServiceProviders from "./pages/serviceProviders";
import Login from "./pages/login";
import Profile from "./pages/profile";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/search" element={<Search />} />
                <Route path="/service-providers" element={<ServiceProviders />} />
                {/*<Route path="/login" element={<Login />} />*/}
                <Route path="/profile" element={<Profile />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
