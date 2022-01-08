import React from 'react';
import BSNavBar from '../component/BSNavBar'
import Footer from "../component/footer";
import '../App.css';


const Layout = ({title = "Title", children}) => {
    return (
        <div>
            <BSNavBar />
            <div className="jumbotron">
                <h1>{title}</h1>
            </div>
            <div>{children}</div>
            <Footer />
        </div>
    )
}

export default Layout;