import React, {useState} from 'react';
import { CountryDropdown, RegionDropdown } from 'react-country-region-selector';
import {Col, Form, Row, Button} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import ServiceSubtype from "./ServiceSubtype";

function SearchForm ({setResults}) {
    const [search, setSearch] = useState({
        country: "",
        city: "",
        district: "",
        serviceType: null,
        serviceSubtype: null,
        petType: null
    })

    // const convertSearchToPayload = (search) => {
    //     return {
    //         country: search.country,
    //         city: search.city,
    //         district: search.district,
    //         serviceType: search.serviceType,
    //         serviceSubtype: search.serviceSubtype,
    //         petType: search.petType
    //     }
    // }


    const fetchResults = async (search) => {
        console.log(search)
        // const payload = convertSearchToPayload(search);
        // console.log(payload);

        const res = await fetch(`http://localhost:8080/api/search`,{
            method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(search)
        })
        const data =  await res.json()
        console.log(data)
        return data;
    }

    const getSearchResults = async (search) => {
        const resultFromApi = await fetchResults(search);
        setResults(resultFromApi)
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        await getSearchResults(search);
        setSearch({
            country: "",
            city: "",
            district: "",
            serviceType: null,
            serviceSubtype: null,
            petType: null
        })
    }


    return (
        <Form onSubmit={handleSubmit}>
                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                    <Form.Label column lg={2}>
                        Country
                    </Form.Label>
                    <Col md={10}>
                        <CountryDropdown
                            style={{ height: "40px", width:"100%" }}
                            classename="country"
                            value={search.country}
                            onChange={(val) => setSearch({...search, country: val}) }/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                    <Form.Label column md={2}>
                        City
                    </Form.Label>
                    <Col md={10}>
                        <RegionDropdown
                            style={{ height: "40px" ,width:"100%"}}
                            country={search.country}
                            value={search.city}
                            onChange={(val) => setSearch({...search, city: val})} />
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                    <Form.Label column md={2}>
                        Zip
                    </Form.Label>
                    <Col md={10}>
                        <Form.Control
                            style={{ height: "40px", width:"100%" }}
                            value={search.district} onChange={(e) => {setSearch({...search, district: e.target.value})}}/>
                    </Col>
                </Form.Group>
               <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                   <Form.Label column md={2}>
                       Services
                   </Form.Label>
                   <Col md={10}>
                   <Form.Control
                       style={{ height: "40px", width:"100%" }}
                       as="select"
                       value={search.serviceType}
                       onChange={(e) => {setSearch({...search, serviceType: e.target.value})}}
                   >
                       <option value="Select">Select Service</option>
                       <option value="RESTAURANT">Restaurant</option>
                       <option value="WELLNESS">Wellness</option>
                       <option value="HEALTHCARE">Healthcare</option>
                       <option value="SHELTER">Shelter</option>
                   </Form.Control>
                   </Col>
               </Form.Group>

            {
                search.serviceType === "WELLNESS" || search.serviceType === "HEALTHCARE" ?
                    <ServiceSubtype search={search} setSearch={setSearch} /> :
                    null
            }

               <Form.Group as={Row} className="row justify-content-center" controlId="formHorizontalEmail">
                    <Col md={3}>
                    <Form.Check type="checkbox" label="Dog"
                                value="DOG"
                                onChange={(e) => {setSearch({...search, petType: e.target.value})}}/>
                    </Col>
                    <Col md={2}>
                    <Form.Check type="checkbox" label="Cat"
                                value="CAT"
                                onChange={(e) => {setSearch({...search, petType: e.target.value})}} />
                    </Col>
                   <Col md={2}>
                       <Form.Check type="checkbox" label="Cat&Dog"
                                   value="CATANDDOG"
                                   onChange={(e) => {setSearch({...search, petType: e.target.value})}}/>
                   </Col>
            </Form.Group>
               <br/>
            <Button variant="success" type="submit"
                    style={{ width:"100%" }}>
                Submit
            </Button>
            </Form>
    )
}

export default SearchForm;
