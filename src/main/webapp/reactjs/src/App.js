import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Home from "./pages/indexPage/home";
import Login from "./pages/loginRegPage/login";
import Registration from "./pages/loginRegPage/registration";
import Profile from "./pages/profilePage/profile";
import ShowSearchPage from "./pages/profilePage/showSearchPage";


function App() {

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/profile" element={<Profile />}>
                    <Route path="search-details" element={<ShowSearchPage/>} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
