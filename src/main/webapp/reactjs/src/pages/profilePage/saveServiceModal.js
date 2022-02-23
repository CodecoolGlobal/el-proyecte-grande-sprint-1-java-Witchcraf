import React, {useState} from "react";
import {Col, Form, Row} from "react-bootstrap";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import {CountryDropdown, RegionDropdown} from "react-country-region-selector";



function SaveServiceModal({setDisplayModal, open, setOpen, username}) {

    const [isSuccessfulSave, setIsSuccessfulSave] = useState(false);

    const [serviceInput, setServiceInput] = useState({
        username: username,
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

    const handleSave = async () => {
        let response = await saveSearchResults(serviceInput);
        if (response.username !== "") {
            setIsSuccessfulSave(true);
            setServiceInput({
                username: username,
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
            })
        }
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

    const handleClose = async () => {
        setOpen(false);
        setDisplayModal(false);
    }

    const fetchSaveSearch = async (serviceInput) => {
        const res = await fetch(`/api/service/save`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(serviceInput)  // user identification?
        })
        return await res.json();
    }

    const saveSearchResults = async (serviceInput) => {
        return await fetchSaveSearch(serviceInput);
    }

    return (
        <>
            <Modal
                className = "myModal"
                show={open}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Add New Service</Modal.Title>
                </Modal.Header>
                <Modal.Body className="modalBody">
                    {
                        isSuccessfulSave ?
                            <p>Successful save!</p> :
                            <>
                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Service name
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="serviceName"
                                        value={serviceInput.serviceName}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, serviceName: e.target.value})
                                        }}
                                    />
                                    <Form.Text className="text-muted">
                                        Required
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Description
                                    </Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        rows={4}
                                        type="text"
                                        name="description"
                                        value={serviceInput.description}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, description: e.target.value})
                                        }}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Homepage
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="homepage"
                                        value={serviceInput.homepage}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, homepage: e.target.value})
                                        }}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Email address
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="email"
                                        value={serviceInput.email}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, email: e.target.value})
                                        }}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Phone
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="phone"
                                        value={serviceInput.phone}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, phone: e.target.value})
                                        }}
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Opening hours
                                    </Form.Label>
                                    <Form.Control
                                        as="textarea"
                                        rows={2}
                                        type="text"
                                        name="openingHours"
                                        value={serviceInput.openingHours}
                                        onChange={(e) => {
                                            setServiceInput({...serviceInput, openingHours: e.target.value})
                                        }}
                                    />
                                </Form.Group>

                                <Row className="mb-3">
                                    <Form.Group as={Col} controlId="formGridCountry">
                                        <Form.Label>
                                            Country
                                        </Form.Label>
                                        <Col sm={10}>
                                            <CountryDropdown
                                                style={{ height: "40px", width:"100%"}}
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
                                                    style={{ height: "40px" ,width:"100%"}}
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
                                                onChange={(e) => {setServiceInput({...serviceInput, district: e.target.value})}}
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
                                                onChange={(e) => {setServiceInput({...serviceInput, street: e.target.value})}}
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
                                                    onChange={(e) => {setServiceInput({...serviceInput, number: e.target.value})}}
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
                                                onChange={(e) => {setServiceInput({...serviceInput, floor: e.target.value})}}
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
                                                    onChange={(e) => {setServiceInput({...serviceInput, door: e.target.value})}}
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
                                                onChange={(e) => {setServiceInput({...serviceInput, bell: e.target.value})}}
                                                name="bell"
                                            />
                                        </Col>
                                    </Form.Group>
                                </Row>

                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>
                                        Service type
                                    </Form.Label>
                                    <Form.Control
                                        style={{ height: "40px", width:"100%"}}
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
                                </Form.Group>

                                {
                                    serviceInput.serviceType === "WELLNESS" || serviceInput.serviceType === "HEALTHCARE" ?
                                        <Form.Group className="mb-3" controlId="formBasicEmail">
                                            <Form.Label>
                                                Service subtype
                                            </Form.Label>
                                            <Form.Control
                                                as="select"
                                                value={serviceInput.serviceSubtype === null ? "" : serviceInput.serviceSubtype}
                                                style={{ height: "40px", width:"100%"}}
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
                                        </Form.Group> : null
                                }

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


                            < />
                    }
                </Modal.Body>
                <Modal.Footer>
                    {
                        isSuccessfulSave ?
                            <Button variant="secondary" onClick={handleClose}>
                                Close
                            </Button> :
                            <Button variant="secondary" onClick={handleSave}>
                                Save
                            </Button>
                    }
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default SaveServiceModal;
