import React from 'react';
import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap';


function HeaderSection() {
    const username = window.localStorage.getItem("username");
    console.log(username)


    return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="/" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>PawPrint</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/search" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Search Services</Nav.Link>
                            <Nav.Link href="/service-provider" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Become Service Provider</Nav.Link>
                            <NavDropdown title="Services" id="collasible-nav-dropdown" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                <NavDropdown.Item href="#action/3.1" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Wash&Vau</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Cosmetics</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Vets</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action/3.4" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Hospital</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                        <Nav>
                            {username !== "Betti" ? (
                                <>
                                <Nav.Link href="/registration" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Sing up</Nav.Link>
                                <Nav.Link eventKey={2} href="/login" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                Sing in
                                </Nav.Link>
                                </>
                            ) : (
                                <>
                                <Nav.Link href="/logout" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Logout</Nav.Link>
                                <Nav.Link eventKey={2} href="/login" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                    Sing in as: {username}
                                </Nav.Link>
                                <Nav.Link href="/profile" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>Profile</Nav.Link>
                                </>
                                )}

                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
    );
}

export default HeaderSection;