import React, { Component } from 'react';
import {Navbar, Container, Col} from 'react-bootstrap';

class Footer extends Component {
    
    render() { 

        let fullYear = new Date().getFullYear();
        return ( 
            <Navbar bg="dark" variant="dark" fixed="bottom">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>
                            {fullYear}, All right reserved
                        </div> 
                    </Col>
                </Container>

            </Navbar>
         );
    }
}
 
export default Footer;