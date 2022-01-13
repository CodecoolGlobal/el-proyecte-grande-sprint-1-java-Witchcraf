import React, {useState} from 'react';
import { CountryDropdown, RegionDropdown } from 'react-country-region-selector';
import {Col, Form, Row, Button} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';



function SearchForm ({setResults}) {
    const [search, setSearch] = useState({
        country: "",
        region: "",
        district: "",
        serviceType: "",
        petType: ""
    })


    const fetchResults = async (search) => {
        console.log(search)

        const res = await fetch(`http://localhost:8080/api/searchTest`,{
            method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(search)
        })
        const data =  await res.json()
        if(data === 0){
            alert("We cant find services, please try again!")
        }
        return data;
    }



    const handleSubmit = async (event) => {
        event.preventDefault();
        const getSearchResults = async (search) => {
            const resultFromApi = await fetchResults(search);
            setResults(resultFromApi)
        }
        await getSearchResults(search)
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
                        Region
                    </Form.Label>
                    <Col md={10}>
                        <RegionDropdown
                            style={{ height: "40px" ,width:"100%"}}
                            country={search.country}
                            value={search.region}
                            onChange={(val) => setSearch({...search, region: val})} />
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
                       <option value="Restaurant">Restaurant</option>
                       <option value="Wellness">Wellness</option>
                       <option value="Hospital">Hospital</option>
                       <option value="Shelter">Shelter</option>
                   </Form.Control>
                   </Col>
               </Form.Group>


               <Form.Group as={Row} className="row justify-content-center" controlId="formHorizontalEmail">
                    <Col md={3}>
                    <Form.Check type="checkbox" label="Dog"
                                value="Dog"
                                onChange={(e) => {setSearch({...search, petType: e.target.value})}}/>
                    </Col>
                    <Col md={2}>
                    <Form.Check type="checkbox" label="Cat"
                                value="Cat"
                                onChange={(e) => {setSearch({...search, petType: e.target.value})}} />
                    </Col>
                   <Col md={2}>
                       <Form.Check type="checkbox" label="Cat&Dog"
                                   value="Cat&Dog"
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
