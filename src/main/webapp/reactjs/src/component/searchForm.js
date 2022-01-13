import React, {useState} from 'react';
import {CountryDropdown, RegionDropdown} from 'react-country-region-selector';
import {Button, Col, Form, Row} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import ServiceSubtype from "./serviceSubtype";

function SearchForm ({setResults}) {
    const [isCheckedDog, setIsCheckedDog] = useState(false);
    const [isCheckedCat, setIsCheckedCat] = useState(false);
    const [isCheckedCatAndDog, setIsCheckedCatAndDog] = useState(false);

    const [search, setSearch] = useState({
        country: "",
        city: "",
        district: "",
        serviceType: null,
        serviceSubtype: null,
        petType: null
    })


    const fetchResults = async (search) => {

        const res = await fetch(`http://localhost:8080/api/search`,{
            method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(search)
        })
        return await res.json();
    }

    const getSearchResults = async (search) => {
        const resultFromApi = await fetchResults(search);
        setResults(resultFromApi)
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        await getSearchResults(search);
        setIsCheckedDog(false);
        setIsCheckedCat(false);
        setIsCheckedCatAndDog(false);
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
                    <Form.Label column lg={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                        Country
                    </Form.Label>
                    <Col md={10}>
                        <CountryDropdown
                            style={{ height: "40px", width:"100%",fontFamily: 'Playfair Display',fontSize:"20px" }}
                            classename="country"
                            value={search.country}
                            onChange={(val) => setSearch({...search, country: val}) }/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                    <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                        City
                    </Form.Label>
                    <Col md={10}>
                        <RegionDropdown
                            style={{ height: "40px" ,width:"100%", fontFamily: 'Playfair Display',fontSize:"20px"}}
                            country={search.country}
                            value={search.city}
                            onChange={(val) => setSearch({...search, city: val})} />
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                    <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                        Zip
                    </Form.Label>
                    <Col md={10}>
                        <Form.Control
                            style={{ height: "40px", width:"100%" }}
                            value={search.district} onChange={(e) => {setSearch({...search, district: e.target.value})}}/>
                    </Col>
                </Form.Group>
               <Form.Group as={Row} className="mb-3" controlId="formHorizontalEmail">
                   <Form.Label column md={2} style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                       Services
                   </Form.Label>
                   <Col md={10}>
                   <Form.Control
                       style={{ height: "40px", width:"100%",fontFamily: 'Playfair Display',fontSize:"20px" }}
                       as="select"
                       value={search.serviceType === null ? "" : search.serviceType}
                       onChange={(e) => {setSearch({...search, serviceType: e.target.value})}}
                   >
                       <option value="Select" > Select Service</option>
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
                    <Form.Check type="checkbox" label="Dog" checked={isCheckedDog}
                                value="DOG"
                                style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                onChange={(e) => {setSearch({...search, petType: e.target.value}); setIsCheckedDog(!isCheckedDog)}}/>
                    </Col>
                    <Col md={2}>
                    <Form.Check type="checkbox" label="Cat" checked={isCheckedCat}
                                value="CAT"
                                style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                onChange={(e) => {setSearch({...search, petType: e.target.value}); setIsCheckedCat(!isCheckedCat);}} />
                    </Col>
                   <Col md={2}>
                       <Form.Check type="checkbox" label="Cat&Dog" checked={isCheckedCatAndDog}
                                   value="CATANDDOG"
                                   style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                   onChange={(e) => {setSearch({...search, petType: e.target.value}); setIsCheckedCatAndDog(!isCheckedCatAndDog);}}/>
                   </Col>
            </Form.Group>
               <br/>
            <Button variant="success" type="submit"
                   style={{ fontFamily: 'Playfair Display',fontSize:"20px", width:"100%" }}>
                Submit
            </Button>
            </Form>
    )
}

export default SearchForm;
