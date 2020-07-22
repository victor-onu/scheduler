import React from 'react';
import './App.css';
import NavigationBar from './components/NavigationBar';
import { Container, Row, Col } from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Footer from './components/Footer';
import Recipient from './components/Recipient';
import Welcome from './components/Welcome';


function App() {
  const marginTop={
    marginTop: "20px"
  }
  return (
    <Router>
        <NavigationBar/>
        <Container>
          <Row>
            <Col lg={12} style={marginTop}>

              <Switch>
                <Route path="/" exact component={Welcome}  />
                <Route path="/addRecipient" exact component={Recipient}  />
                {/* <Route path="/list" exact component={BookList}  />  */}
              </Switch>

            </Col>

            </Row>

        </Container>
        <Footer/>
    </Router>
  );
}

export default App;