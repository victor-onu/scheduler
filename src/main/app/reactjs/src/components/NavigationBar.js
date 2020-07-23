import React, { Component } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export default class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/"} className="navbar-brand">
                <Navbar.Brand href="/"><img src="schedule.png" width="25" height="25" alt="brand-icon"/>Report Scheduler</Navbar.Brand>
                </Link>

                <Nav className="mr-auto">
                    <Link to={"/addRecipient"} className="nav-link">Add Recipient</Link>
                    <Link to={"/addReport"} className="nav-link">Upload Report</Link>
                    <Link to={"/ReportList"} className="nav-link">View Reports</Link>
                </Nav>
        
            </Navbar>
        )
    }
}

