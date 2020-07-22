import React, { Component } from 'react';
import {  Jumbotron } from 'react-bootstrap';

class Welcome extends Component {
    state = {  }
    render() { 
        return ( 
            <Jumbotron className="bg-dark text-white">
            <h1>Welcome to the Report Scheduler!</h1>
            <blockquote className="blockquote mb-0">
                <p>
                “A plan is what, a schedule is when. It takes both a plan and a schedule to get things done.”
                </p>
                <footer className="blockquote-footer">
                Peter Turla
                </footer>
            </blockquote>
              
        </Jumbotron>
         );
    }
}
 
export default Welcome;