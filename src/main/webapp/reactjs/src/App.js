import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import {BrowserRouter,Routes,Route} from "react-router-dom";
import Home from "./pages/home";
import Search from "./pages/search";
import ServiceProviders from "./pages/serviceProviders";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/search" element={<Search />} />
                <Route path="/service-providers" element={<ServiceProviders />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
