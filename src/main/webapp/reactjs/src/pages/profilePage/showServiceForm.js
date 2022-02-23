import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {Alert, Button} from "@mui/material";

import {CountryDropdown, RegionDropdown} from "react-country-region-selector";
import {Col, Form, Row} from "react-bootstrap";

function ShowServiceForm({user, cards}) {

    const navigate = useNavigate();

    const [serviceInput, setServiceInput] = useState({
        username: user.name,
        serviceName: "",
        petType: null,
        country: "",
        district: "",
        city: "",
        street: "",
        number: "",
        floor: "",
        door: "",
        bell: "",
        phone: "",
        email: "",
        openingHours: "",
        serviceType: null,
        serviceSubtype: null,
        description: "",
        homepage: "",
        image: null
    });

    const emailValidator = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    const inputEvent = (event) => {
        const name = event.target.name;
        let value = event.target.value;
        // if(name ==="serviceType"){
        //     setService(!service)
        //     value = !service;
        //     console.log(value);
        // }
        // else {
        //     value = event.target.value;
        // }
        setServiceInput((lastValue) => {
            return {
                ...lastValue,
                [name]: value
            }
        });
        validateDistrict();
    }

    const validateEmailAddress = () => {
        let emailAddressError = "";
        const value = serviceInput.email;
        if (value.trim() === "") emailAddressError = "Email Address is required";
        else if (!emailValidator.test(value))
            emailAddressError = "Email is not valid";
        return emailAddressError === "";
    }

    const validateDistrict = () => {
        let nameError = "";
        const value = serviceInput.district;
        if (value.trim() === "" || isNaN(parseInt(value))) {
            nameError = "Invalid district";
        } else {
            setServiceInput({...serviceInput, district: parseInt(value).toString()})
        }
        return nameError === "";
    }

    const validateField = (name) => {
        let isValid = false;
        if (name === "email") isValid = validateEmailAddress();
        else if (name === "district") isValid = validateDistrict();
        return isValid;
    }

    const registerService = async (serviceInput) => {
        return await fetchResults(serviceInput);
    }

    const fetchResults = async (serviceInput) => {
        console.log(serviceInput);
        const res = await fetch(`/api/registerService`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(serviceInput)
        })
        return await res.json();
    }

    const submitForm = async (e) => {
        e.preventDefault();

        let formFileds = [
            "email",
            "district"
        ];

        let isValid = true;
        formFileds.forEach(field => {
            isValid = validateField(field) && isValid;
        });

        if (isValid) {
            await registerService(serviceInput);
            navigate("/profile");
        }
    }

    return (
        // <section className="h-100 gradient-custom-2">
        //     <div className="row d-flex justify-content-center align-items-center h-100">
        //         <div className="col col-lg-9 col-xl-7">
        //             <div className="card">
        //                 <div className="rounded-top text-white d-flex flex-row"
        //                      style={{backgroundColor: "#000",  height:"200px"}}>
        //                     <div className="ms-4 mt-5 d-flex flex-column" style={{height:"150px"}}>
        //                         <img
        //                             src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
        //                             alt="Generic placeholder image"
        //                             className="img-fluid img-thumbnail mt-4 mb-2"
        //                             style={{width: "150px",  zIndex:"1"}}/>
        //
        //                         <button type="button"
        //                                 className="btn btn-outline-dark"
        //                                 data-mdb-ripple-color="dark"
        //                                 style={{zIndex:"1"}}
        //                                 onClick={() => {navigate("/profile")}}
        //                         >
        //                             Add service
        //                         </button>
        //                     </div>
        //                     <div className="ms-3" style={{marginTop: "130px"}}>
        //                         <h5>{user.name}</h5>
        //                     </div>
        //                 </div>
        //                 <br/><br/><br/>
        //                 <div className="card-body p-4 text-black">
        //
        //                     <div className="mb-5">
        //                         <p className="lead fw-normal mb-1">About</p>
        //                         <div className="p-4" style={{backgroundColor: "#f8f9fa"}}>
        //                             <p className="font-italic mb-1">{user.age}</p>
        //                             <p className="font-italic mb-1">Email: {user.email}</p>
        //                         </div>
        //                     </div>
        //                     <div className="d-flex justify-content-between align-items-center mb-4">
        //                         <p className="lead fw-normal mb-0">Services:</p>
        //                     </div>
        //                 </div>
        //             </div>
        //         </div>
        //     </div>
        // </section>

        <section className="h-100 gradient-custom-2">
            <div className="row d-flex justify-content-center align-items-center h-100">
                <div className="col col-lg-9 col-xl-7">
                    <div className="card">
                        <div className="card-body p-4 text-black">
                            <Form onSubmit={submitForm}>
                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalServiceName">
                                    <Form.Label column sm={2}>
                                        Service name
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.serviceName}
                                            onChange={inputEvent}
                                            name="serviceName"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalDescription">
                                    <Form.Label column sm={2}>
                                        Description
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.description}
                                            onChange={inputEvent}
                                            name="description"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalHomepage">
                                    <Form.Label column sm={2}>
                                        Homepage
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.homepage}
                                            onChange={inputEvent}
                                            name="homepage"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                                    <Form.Label column sm={2}>
                                        Email address
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.email}
                                            onChange={inputEvent}
                                            name="email"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalPhone">
                                    <Form.Label column sm={2}>
                                        Phone
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.phone}
                                            onChange={inputEvent}
                                            name="phone"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalOpeningHours">
                                    <Form.Label column sm={2}>
                                        Opening hours
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="text"
                                            value={serviceInput.openingHours}
                                            onChange={inputEvent}
                                            name="openingHours"
                                        />
                                    </Col>
                                </Form.Group>

                                <Row className="mb-3">
                                    <Form.Group as={Col} controlId="formGridCountry">
                                        <Form.Label>
                                            Country
                                        </Form.Label>
                                        <Col sm={10}>
                                            <CountryDropdown
                                                style={{ height: "40px", width:"100%", fontFamily: 'Playfair Display', fontSize:"20px" }}
                                                classename="country"
                                                value={serviceInput.country}
                                                onChange={(value) => setServiceInput({...serviceInput, country: value}) }
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Col} className="mb-3" controlId="formHorizontalCity">
                                        <Form.Label column sm={2}>
                                            City
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Col md={10}>
                                                <RegionDropdown
                                                    style={{ height: "40px" ,width:"100%", fontFamily: 'Playfair Display',fontSize:"20px"}}
                                                    country={serviceInput.country}
                                                    value={serviceInput.city}
                                                    onChange={(value) => setServiceInput({...serviceInput, city: value})}
                                                />
                                            </Col>
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Col} className="mb-3" controlId="formHorizontalDistrict">
                                        <Form.Label column sm={2}>
                                            District
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Form.Control
                                                type="text"
                                                value={serviceInput.district}
                                                onChange={inputEvent}
                                                name="district"
                                            />
                                        </Col>
                                    </Form.Group>
                                </Row>

                                <Row className="mb-3">
                                    <Form.Group as={Col} controlId="formGridStreet">
                                        <Form.Label>
                                            Street
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Form.Control
                                                type="text"
                                                value={serviceInput.street}
                                                onChange={inputEvent}
                                                name="street"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Col} className="mb-3" controlId="formHorizontalNumber">
                                        <Form.Label column sm={2}>
                                            Number
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Col md={10}>
                                                <Form.Control
                                                    type="text"
                                                    value={serviceInput.number}
                                                    onChange={inputEvent}
                                                    name="number"
                                                />
                                            </Col>
                                        </Col>
                                    </Form.Group>
                                </Row>

                                <Row className="mb-3">
                                    <Form.Group as={Col} controlId="formGridFloor">
                                        <Form.Label>
                                            Floor
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Form.Control
                                                type="text"
                                                value={serviceInput.floor}
                                                onChange={inputEvent}
                                                name="floor"
                                            />
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Col} className="mb-3" controlId="formHorizontalDoor">
                                        <Form.Label column sm={2}>
                                            Door
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Col md={10}>
                                                <Form.Control
                                                    type="text"
                                                    value={serviceInput.door}
                                                    onChange={inputEvent}
                                                    name="door"
                                                />
                                            </Col>
                                        </Col>
                                    </Form.Group>

                                    <Form.Group as={Col} className="mb-3" controlId="formHorizontalBell">
                                        <Form.Label column sm={2}>
                                            Bell
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Form.Control
                                                type="text"
                                                value={serviceInput.bell}
                                                onChange={inputEvent}
                                                name="bell"
                                            />
                                        </Col>
                                    </Form.Group>
                                </Row>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalServiceType">
                                    <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                        Service type
                                    </Form.Label>
                                    <Col md={10}>
                                        <Form.Control
                                            style={{ height: "40px", width:"100%",fontFamily: 'Playfair Display',fontSize:"20px" }}
                                            as="select"
                                            value={serviceInput.serviceType === null ? "" : serviceInput.serviceType}
                                            onChange={(e) => {setServiceInput({...serviceInput, serviceType: e.target.value})}}
                                        >
                                            <option value="Select" placeholder="Select service"></option>
                                            <option value="RESTAURANT">Restaurant</option>
                                            <option value="WELLNESS">Wellness</option>
                                            <option value="HEALTHCARE">Healthcare</option>
                                            <option value="SHELTER">Shelter</option>
                                        </Form.Control>
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalServiceSubtype">
                                    <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                        Service subtype
                                    </Form.Label>
                                    <Col md={10}>
                                        <Form.Control
                                            as="select"
                                            value={serviceInput.serviceSubtype === null ? "" : serviceInput.serviceSubtype}
                                            style={{ height: "40px", width:"100%", fontFamily: 'Playfair Display',fontSize:"20px"}}
                                            onChange={(e) => {setServiceInput({...serviceInput, serviceSubtype: e.target.value})}}
                                        >
                                            <option value="Select" placeholder="Select service subtype"></option>
                                            {
                                                serviceInput.serviceType === "WELLNESS" ?
                                                    [
                                                        <option value="WASHANDVAU" key="washandvau">Wash & Vau</option>,
                                                        <option value="COSMETICS" key="cosmetics">Cosmetics</option>
                                                    ] : serviceInput.serviceType === "HEALTHCARE" ?
                                                        [
                                                            <option value="VET" key="vet">Vet</option>,
                                                            <option value="HOSPITAL" key="hospital">Hospital</option>
                                                        ] : null
                                            }
                                        </Form.Control>
                                    </Col>
                                </Form.Group>

                                <fieldset>
                                    <Form.Group as={Row} className="mb-3">
                                        <Form.Label as="legend" column sm={2}>
                                            Pet type
                                        </Form.Label>
                                        <Col sm={10}>
                                            <Form.Check
                                                type="radio"
                                                label="Cat"
                                                value="CAT"
                                                name="formHorizontalRadios"
                                                id="formHorizontalRadios1"
                                                onChange={(e) => {setServiceInput({...serviceInput, serviceType: e.target.value})}}
                                            />
                                            <Form.Check
                                                type="radio"
                                                label="Dog"
                                                value="DOG"
                                                name="formHorizontalRadios"
                                                id="formHorizontalRadios2"
                                                onChange={(e) => {setServiceInput({...serviceInput, serviceType: e.target.value})}}
                                            />
                                            <Form.Check
                                                type="radio"
                                                label="Cat & Dog"
                                                value="CATANDDOG"
                                                name="formHorizontalRadios"
                                                id="formHorizontalRadios3"
                                                onChange={(e) => {setServiceInput({...serviceInput, serviceType: e.target.value})}}
                                            />
                                        </Col>
                                    </Form.Group>
                                </fieldset>

                                <Form.Group as={Row} className="mb-3" controlId="formHorizontalImage">
                                    <Form.Label column sm={2}>
                                        Upload image
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Control
                                            type="file"
                                            value={serviceInput.image}
                                            onChange={(e) => {setServiceInput({...serviceInput, image: e.target.files[0]})}}
                                            name="image"
                                        />
                                    </Col>
                                </Form.Group>

                                <Form.Group as={Row} className="mb-3">
                                    <Col sm={{ span: 10, offset: 2 }}>
                                        <Button type="submit">Save</Button>
                                    </Col>
                                </Form.Group>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    );
}

export default ShowServiceForm;
