import React, { Component } from 'react';
import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import { faList, faTrash } from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import axios from 'axios';
class ReportList extends Component {
    
    // constructor(props){
    //     super(props);
    //     this.state = {
    //         reports: []
    //     };
    // }

    // componentDidMount(){
    //     axios.get("/api/report")
    //     .then(response => response.data)
    //     .then((data) => {
    //         this.setState({reports: data})
    //     }
    //    )
    // }

    state = {
        isLoading: true,
        Reports: []
    }

    async componentDidMount(){
        const response = await fetch('/api/report')
        const body = await response.json();
        this.setState({Reports: body, isLoading: false})
    }

    render() { 
        const {Reports, isLoading} = this.state;
        if(isLoading){
            return(<div>Loading...</div>) 
        }

        let rows =
            Reports.data.map(report =>
                <tr key = {report.id}>
                <td>{report.reportTitle}</td>
                <td>{report.reportDescription}</td>
                <td>{report.reportDocument}</td>
                <td>{report.status}</td>
                <td>{report.scheduleExpression}</td>
                <td>{report.url}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" variant="outline-danger"> <FontAwesomeIcon icon={faTrash} /></Button>
                    </ButtonGroup>
                </td>
            </tr>
                ) 

        return ( 
        
            <Card className="border border-dark bg-dark text-white">
                <Card.Header><FontAwesomeIcon icon={faList} /> Report List</Card.Header>
                <Card.Body>
                    <Table bordered hover striped variant="dark">
                        <thead>
                            <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Document Name</th>
                        
                            <th>Status</th>
                            <th>Schedule</th>
                            <th>Document Link</th>
                            <th>Cancel</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rows}                   
                            
                        </tbody>

                    </Table>
                </Card.Body>
            </Card>
        
        );
        
    }
}
 
export default ReportList; 