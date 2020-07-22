import React, { Component } from 'react';
import {Navbar, Nav, Button} from 'react-bootstrap';

export default class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/"><img src="schedule.png" width="25" height="25" alt="brand-icon"/>Report Scheduler</Navbar.Brand>
                <Nav className="mr-auto">
                <Nav.Link href="#home">Home</Nav.Link>
                <Nav.Link href="#features">Features</Nav.Link>
                <Nav.Link href="#pricing">Pricing</Nav.Link>
                </Nav>
        
            </Navbar>
        )
    }
}

