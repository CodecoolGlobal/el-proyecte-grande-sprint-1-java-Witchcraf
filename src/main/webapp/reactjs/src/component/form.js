import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'
import { Form, Button } from 'react-bootstrap'

function SearchForm(){
    return (
        <Form className="signup-form">
            <input className="name-input" type="text" placeholder="name" name="name"/>
            <input className="email-input" type="text" placeholder="email" name="email" />
            <Button className="submit-button" value="submit" type="submit">submit</Button>
        </Form>

    );
}
export default SearchForm;