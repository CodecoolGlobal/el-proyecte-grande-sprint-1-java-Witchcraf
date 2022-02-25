import {Card, Button, ButtonGroup} from "react-bootstrap";
import React from "react";
import {useNavigate} from "react-router-dom";

function Cards({user, details, setSavedSearch, tokenEncoded}) {

    const navigate = useNavigate();

    const handleUserCardClick = async () => {
        const searchId = details.id;
        const res = await fetch(`/api/getSearch/${searchId}`, {
            method: 'GET',
            headers: {
                'Authorization': tokenEncoded,
            },
        })
        let savedSearchResponse = await res.json();
        console.log(savedSearchResponse);
        setSavedSearch(savedSearchResponse);
        navigate("/profile/search-details");
    }

    return (
        <Card style={{ width: '18rem' }}>
            <Card.Body>
                {/*{user.role === "ADMIN" ? <Card.Title>Service Title</Card.Title> : <Card.Title>{details.name}</Card.Title>}*/}
                <Card.Title>{details.name}</Card.Title>
                {user.role === "ADMIN" ? <Card.Subtitle className="mb-2 text-muted">Description</Card.Subtitle> : <Card.Subtitle className="mb-2 text-muted">Description</Card.Subtitle>}
                <Card.Text>
                    {details.description}
                </Card.Text>
                <ButtonGroup aria-label="Basic example">
                    <Button
                        variant="danger"
                        onClick={handleUserCardClick}
                    >Show</Button>
                    {user.role === "ADMIN" ?  <Button  variant="outline-success">Edit</Button> : ""}
                </ButtonGroup>
            </Card.Body>
        </Card>
    );
}
export default Cards;
