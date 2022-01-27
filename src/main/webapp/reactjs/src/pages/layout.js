import React from 'react';
import HeaderSection from '../component/headerSection'
import Footer from "../component/footer";
import '../App.css';


const Layout = ({ children, token}) => {
    return (
        <div>
            <HeaderSection token={token} />
            <div>{children}</div>
            <Footer />
        </div>
    )
}

export default Layout;