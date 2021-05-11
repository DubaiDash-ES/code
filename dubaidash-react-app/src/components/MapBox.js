import React from 'react';
import mapboxgl from 'mapbox-gl';
import '../App.css';

mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuaWVsdmNvcnJlaWEiLCJhIjoiY2tpa2xodTViMGIyZjJxcGtsNHZkYnU4NCJ9.xk7aj9lSbWNLpvwM_HmdEg';

class MapBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          lng: 55.36,
          lat: 25.25,
          zoom: 10
        };
        this.mapContainer = React.createRef();
    }

    componentDidMount() {
        const { lng, lat, zoom } = this.state;
        const map = new mapboxgl.Map({
          container: this.mapContainer.current,
          style: 'mapbox://styles/mapbox/streets-v11',
          center: [lng, lat],
          zoom: zoom
        });
    }

    render() {
        return (
          <div>
            <div ref={this.mapContainer} className="map-container" />
          </div>
        );
    }
}

export default MapBox