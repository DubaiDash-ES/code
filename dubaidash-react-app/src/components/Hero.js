import React from 'react';
import AirplaneService from '../services/AirplaneService';

class Hero extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            airplanes:[]
        }
    }

    componentDidMount() {
        AirplaneService.get_dubai_airplanes().then((response) => {
            this.setState({ airplanes: response.data })
        });
    }

    render() {
        return (
            
            <div>
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        {
                            this.state.airplanes.map(
                                airplane =>
                                <div key={airplane.icao24}>
                                    <h1 className="display-4">{airplane.origin_country} obteve o maior registo de temperatura em Portugal neste mês</h1>                                   
                                    <p className="lead">Data: {airplane.geo_altitude}m</p>
                                    <p>Temperatura registada: {airplane.velocity}(m/s))</p>
                                </div> 
                            )
                        }
                        
                    </div>
                </div>
            </div>
        )
    }
}

export default Hero