import React, { Component } from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

class Recipient extends Component {

    constructor(props){
        super(props)
        this.state = this.initialState;
        this.recipientChange = this.recipientChange.bind(this)
        this.submitRecipient = this.submitRecipient.bind(this)
    }

    initialState = {
        firstName: '', lastName: '', email: ''
    }

    submitRecipient = event =>{
        event.preventDefault();

        const recipient = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email
        }
        axios.post("/api/recipient", recipient)
        .then(response=>{
            if (response.data != null) {
                this.setState(this.initialState);
                alert("recipient saved successfully")
            }
        })

        
    }

    recipientChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        })
    }
    
    render() { 
        const {firstName, lastName, email} = this.state;
        return ( 
            <Card className="border border-dark bg-dark text-white">
                <Form id="recipientFormId" onSubmit={this.submitRecipient}>
                    <Card.Header><FontAwesomeIcon icon={faPlusSquare} /> Add Recipient</Card.Header>
                        <Card.Body>
                                <Form.Group controlId="firstName" as={Col}>
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control required autoComplete = "off"
                                    name="firstName" type="test"
                                    value={firstName}
                                    onChange={this.recipientChange} 
                                    placeholder="Enter first name" 
                                    className="text-white bg-dark" 
                                    />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group controlId="lastName" as={Col}>
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control required autoComplete = "off"
                                        name="lastName" 
                                        value={lastName}
                                        onChange={this.recipientChange} 
                                        type="test" 
                                        placeholder="Enter last name" 
                                        className="text-white bg-dark" 
                                    />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group controlId="email" as={Col}>
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control required autoComplete = "off"
                                        name="email" 
                                        value={email}
                                        onChange={this.recipientChange}
                                        type="test" placeholder="Enter email" 
                                        className="text-white bg-dark" />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>
                           
                        </Card.Body>

                <Card.Footer style={{"text-align": "right"}}>
                    <Button variant="success" type="submit" size="sm">
                    <FontAwesomeIcon icon={faSave} /> Submit
                    </Button>
                </Card.Footer>

                </Form>
            </Card>
         );
    }
}
 
export default Recipient;