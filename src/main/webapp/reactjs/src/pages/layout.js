import React from 'react';
import HeaderSection from '../component/HeaderSection'
import Footer from "../component/footer";
import '../App.css';


const Layout = ({title = "Title", children}) => {
    return (
        <div>
            <HeaderSection />
            <div className="jumbotron">
                <h1>{title}</h1>
            </div>
            <div>{children}</div>
            <Footer />
        </div>
    )
}

export default Layout;