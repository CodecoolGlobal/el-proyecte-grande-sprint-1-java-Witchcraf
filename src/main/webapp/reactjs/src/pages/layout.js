import React from 'react';
import HeaderSection from '../component/HeaderSection'
import Footer from "../component/footer";
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