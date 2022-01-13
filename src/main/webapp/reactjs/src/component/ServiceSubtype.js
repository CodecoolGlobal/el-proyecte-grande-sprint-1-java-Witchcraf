import React, {useState} from "react";
import {Col, Form, Row} from "react-bootstrap";

function ServiceSubtype ({search, setSearch}) {

    return(
        <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
            <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                Service Subtype
            </Form.Label>
            <Col md={10}>
                <Form.Control
                    as="select"
                    value={search.serviceSubtype === null ? "" : search.serviceSubtype}
                    style={{ height: "40px", width:"100%", fontFamily: 'Playfair Display',fontSize:"20px"}}
                    onChange={(e) => {setSearch({...search, serviceSubtype: e.target.value})}}
                >
                    <option value="Select">Select Service Subtype</option>
                    {
                        search.serviceType === "WELLNESS" ?
                            [
                                <option value="WASHANDVAU" key="washandvau">Wash & Vau</option>,
                                <option value="COSMETICS" key="cosmetics">Cosmetics</option>
                            ] : search.serviceType === "HEALTHCARE" ?
                                [
                                    <option value="VET" key="vet">Vet</option>,
                                    <option value="HOSPITAL" key="hospital">Hospital</option>
                                ] : null
                    }
                </Form.Control>
            </Col>
        </Form.Group>
    );

}

export default ServiceSubtype;