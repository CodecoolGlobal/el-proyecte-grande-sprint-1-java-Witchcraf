import React, {useState} from "react";
import ResultCard from "./resultCard";
import {Form} from "react-bootstrap";
import MultiButton from "./multiButton";
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';



function SaveSearchModal({searches, setSearches, setDisplayModal, open, setOpen, setClearCheckbox, username}) {

    const [isSuccessfulSave, setIsSuccessfulSave] = useState(false);

    const handleSave = async () => {
        let response = await saveSearchResults(searches);
        if (response.username !== "") {
            setIsSuccessfulSave(true);
            setSearches({
                username: username,
                // username: "Hokedli",
                searchName: "My Search",
                description: "",
                searchedServices: []
            })
        }
    }

    const handleClose = async () => {
        setOpen(false);
        setDisplayModal(false);
        setClearCheckbox(true);
    }

    const fetchSaveSearch = async (searches) => {
        const res = await fetch(`/api/search/save`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(searches)  // user identification?
        })
        return await res.json();
    }

    const saveSearchResults = async (search) => {
        return await fetchSaveSearch(search);
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
                    <Modal.Title>Save Search</Modal.Title>
                </Modal.Header>
                <Modal.Body className="modalBody">
                    {
                        isSuccessfulSave ?
                            <p>Successful save!</p> :
                            <>
                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>Title</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="My Search"
                                        value={searches.searchName}
                                        onChange={(e) => {
                                            setSearches({...searches, searchName: e.target.value})
                                        }}
                                    />
                                    <Form.Text className="text-muted">
                                        Name your search.
                                    </Form.Text>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="formBasicEmail">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control type="text"
                                    value={searches.description}
                                    onChange={(e) => {setSearches({...searches, description: e.target.value})}}
                                    />
                                    <Form.Text className="text-muted">
                                    Add description.
                                    </Form.Text>
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

export default SaveSearchModal;