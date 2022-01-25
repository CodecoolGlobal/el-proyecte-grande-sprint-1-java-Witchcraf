import React, {useState} from 'react';
import {CountryDropdown, RegionDropdown} from 'react-country-region-selector';
import {Button, Col, Form, Row} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import ServiceSubtype from "./serviceSubtype";

function SearchForm ({setResults}) {
    const [isCheckedDogOnly, setIsCheckedDogOnly] = useState(false);
    const [isCheckedCatOnly, setIsCheckedCatOnly] = useState(false);
    const [isCheckedBothOnly, setIsCheckedBothOnly] = useState(false);
    const [isCheckedAllDog, setIsCheckedAllDog] = useState(false);
    const [isCheckedAllCat, setIsCheckedAllCat] = useState(false);
    const [isCheckedAllPetType, setIsCheckedAllPetType] = useState(false);

    const [search, setSearch] = useState({
        country: "",
        city: "",
        district: "",
        serviceType: null,
        serviceSubtype: null,
        isDogOnly: false,
        isCatOnly: false,
        isBothOnly: false,
        isAllDog: false,
        isAllCat: false,
        isAllPetType: false
    })

    const fetchResults = async (search) => {
        console.log(search);
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
        setIsCheckedDogOnly(false);
        setIsCheckedCatOnly(false);
        setIsCheckedBothOnly(false);
        setIsCheckedAllDog(false);
        setIsCheckedAllCat(false);
        setIsCheckedAllPetType(false);
        setSearch({
            country: "",
            city: "",
            district: "",
            serviceType: null,
            serviceSubtype: null,
            isDogOnly: false,
            isCatOnly: false,
            isBothOnly: false,
            isAllDog: false,
            isAllCat: false,
            isAllPetType: false
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
                   {
                       search.isAllDog || (
                           !search.isDogOnly &&
                           !search.isCatOnly &&
                           !search.isBothOnly &&
                           !search.isAllDog &&
                           !search.isAllCat &&
                           !search.isAllPetType) ?
                           <>
                               <Col md={3}>
                                   <Form.Check type="checkbox" label="Dog" checked={isCheckedAllDog}
                                               value="DOG"
                                               style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                               onChange={() => {setIsCheckedAllDog(!isCheckedAllDog); setSearch({...search, isAllDog: !isCheckedAllDog})}}/>
                               </Col>
                           </> :
                           null
                   }
                   {
                       search.isAllCat || (
                           !search.isDogOnly &&
                           !search.isCatOnly &&
                           !search.isBothOnly &&
                           !search.isAllDog &&
                           !search.isAllCat &&
                           !search.isAllPetType) ?
                           <>
                               <Col md={2}>
                                   <Form.Check type="checkbox" label="Cat" checked={isCheckedAllCat}
                                               value="CAT"
                                               style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                               onChange={() => {setIsCheckedAllCat(!isCheckedAllCat); setSearch({...search, isAllCat: !isCheckedAllCat})}}/>
                               </Col>
                           </> :
                           null
                   }
                   {
                       search.isAllPetType || (
                           !search.isDogOnly &&
                           !search.isCatOnly &&
                           !search.isBothOnly &&
                           !search.isAllDog &&
                           !search.isAllCat &&
                           !search.isAllPetType) ?
                           <>
                               <Col md={2}>
                                   <Form.Check type="checkbox" label="Cat&Dog" checked={isCheckedAllPetType}
                                               value="CATANDDOG"
                                               style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                               onChange={() => {setIsCheckedAllPetType(!isCheckedAllPetType); setSearch({...search, isAllPetType: !isCheckedAllPetType})}} />
                               </Col>
                           </> :
                           null
                   }
            </Form.Group>
               <br/>

            <Form.Group as={Row} className="row justify-content-center" controlId="formHorizontalEmail">
                {
                    search.isDogOnly || (
                        !search.isDogOnly &&
                        !search.isCatOnly &&
                        !search.isBothOnly &&
                        !search.isAllDog &&
                        !search.isAllCat &&
                        !search.isAllPetType) ?
                        <>
                            <Col md={3}>
                                <Form.Check type="checkbox" label="Dog only" checked={isCheckedDogOnly}
                                            value="DOGONLY"
                                            style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                            onChange={() => {setIsCheckedDogOnly(!isCheckedDogOnly); setSearch({...search, isDogOnly: !isCheckedDogOnly})}}/>
                            </Col>
                        </> :
                        null
                }
                {
                    search.isCatOnly || (
                        !search.isDogOnly &&
                        !search.isCatOnly &&
                        !search.isBothOnly &&
                        !search.isAllDog &&
                        !search.isAllCat &&
                        !search.isAllPetType) ?
                        <>
                            <Col md={2}>
                                <Form.Check type="checkbox" label="Cat only" checked={isCheckedCatOnly}
                                            value="CATONLY"
                                            style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                            onChange={() => {setIsCheckedCatOnly(!isCheckedCatOnly); setSearch({...search, isCatOnly: !isCheckedCatOnly})}}/>
                            </Col>
                        </> :
                        null
                }
                {
                    search.isBothOnly || (
                        !search.isDogOnly &&
                        !search.isCatOnly &&
                        !search.isBothOnly &&
                        !search.isAllDog &&
                        !search.isAllCat &&
                        !search.isAllPetType) ?
                        <>
                            <Col md={2}>
                                <Form.Check type="checkbox" label="Cat&Dog only" checked={isCheckedBothOnly}
                                            value="CATANDDOGONLY"
                                            style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}
                                            onChange={() => {setIsCheckedBothOnly(!isCheckedBothOnly); setSearch({...search, isBothOnly: !isCheckedBothOnly})}}/>
                            </Col>
                        </> :
                        null
                }


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
