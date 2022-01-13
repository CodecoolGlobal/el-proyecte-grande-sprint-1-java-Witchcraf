import React from 'react';
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';


function HeaderSection() {
    return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="/">PawPrint</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/search" >Search Services</Nav.Link>
                            <Nav.Link href="/service-provider">Become Service Provider</Nav.Link>
                            <NavDropdown title="Services" id="collasible-nav-dropdown">
                                <NavDropdown.Item href="#action/3.1">Wash&Vau</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2">Cosmetics</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Vets</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action/3.4">Hospital</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                        <Nav>
                            <Nav.Link href="#deets">Sing up</Nav.Link>
                            <Nav.Link eventKey={2} href="/login">
                                Sing in
                            </Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
    );
}

export default HeaderSection;