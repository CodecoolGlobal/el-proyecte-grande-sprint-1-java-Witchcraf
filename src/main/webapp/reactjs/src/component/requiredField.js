import React from "react";
import {Col, Form, Row} from "react-bootstrap";

function RequiredField () {

    return (
        <Form.Group as={Row}>
            <Form.Label column lg={2} style={{fontFamily: 'Playfair Display', fontSize: "20px"}}>

            </Form.Label>
            <Col md={10} style={{color: "red"}}>
                * Required field
            </Col>
        </Form.Group>
    );
}

export default RequiredField;