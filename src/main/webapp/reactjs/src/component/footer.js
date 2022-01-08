import React from 'react';
import { Card } from 'react-bootstrap';


function Footer(){
    return (
        <Card className="bg-dark text-white text-center fixed-bottom">
            <Card.Footer className="text-muted">
                &copy; {new Date().getFullYear()} Copyright: <a href="https://www.mdbootstrap.com"> PawPrint.com </a>
            </Card.Footer>
        </Card>

    );
}
export default Footer;