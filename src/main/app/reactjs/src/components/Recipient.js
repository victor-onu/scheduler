import React, { Component } from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';

class Recipient extends Component {

    constructor(props){
        super(props)
        this.state = {firstName: '', lastName: '', email: ''}
    }

    submitRecipient(event){
        event.preventDefault();
    }

    recipientChange(event){
        this.setState({
            [event.target.name] : event.target.value
        })
    }
    
    render() { 
        return ( 
            <Card className="border border-dark bg-dark text-white">
                <Form id="bookFormId" onSubmit={this.submitRecipient}>
                    <Card.Header>Add Recipient</Card.Header>
                        <Card.Body>
                                <Form.Group controlId="fitstName" as={Col}>
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control required 
                                    name="firstName" type="test"
                                    value={this.state.firstName}
                                    onChange={this.recipientChange} 
                                    placeholder="Enter first name" 
                                    className="text-white bg-dark" />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group controlId="lastName" as={Col}>
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control name="lastName" type="test" placeholder="Enter last name" className="text-white bg-dark" />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group controlId="email" as={Col}>
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control name="coverPhotoURL" type="test" placeholder="Enter email" className="text-white bg-dark" />
                                    <Form.Text >
                                    </Form.Text>
                                </Form.Group>
                           
                        </Card.Body>

                <Card.Footer style={{"text-align": "right"}}>
                    <Button variant="success" type="submit" size="sm">
                            Submit
                    </Button>
                </Card.Footer>

                </Form>
            </Card>
         );
    }
}
 
export default Recipient;