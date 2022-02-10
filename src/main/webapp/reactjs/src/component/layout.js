import React from 'react';
import HeaderSection from './headerSection'
import Footer from "./footer";
import '../App.css';


const Layout = ({ children}) => {


    return (
        <div>
            <HeaderSection />
            <div>{children}</div>
            <Footer />
        </div>
    )
}

export default Layout;