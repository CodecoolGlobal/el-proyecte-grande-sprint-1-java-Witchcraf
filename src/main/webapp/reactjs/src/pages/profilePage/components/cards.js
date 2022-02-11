import {Card, Button, ButtonGroup} from "react-bootstrap";
import React from "react";

function Cards({user, details}){
    console.log(user)
    console.log(details)
    return (
        <Card style={{ width: '18rem' }}>
            <Card.Body>
                {user.role === "ADMIN" ? <Card.Title>Service Title</Card.Title> : <Card.Title>{details.name}</Card.Title>}
                {user.role === "ADMIN" ? <Card.Subtitle className="mb-2 text-muted">Description</Card.Subtitle> : <Card.Subtitle className="mb-2 text-muted">Description</Card.Subtitle>}
                <Card.Text>
                    {details.description}
                </Card.Text>
                <ButtonGroup aria-label="Basic example">
                    <Button variant="danger">Delete</Button>
                    {user.role === "ADMIN" ?  <Button  variant="outline-success">Edit</Button> : ""}
                </ButtonGroup>
            </Card.Body>
        </Card>
    );
}
export default Cards;
