import './App.css';
import TableDubaiAirplanes from './components/TableDubaiAirplanes';
import MapAirplanes from './components/MapAirplanes';
import NavBar from './components/NavBar';
import Hero from './components/Hero';

function App() {
  return (
    <div className="App">
      <br />

      <div className="row">
        <div className="col-2"/>
        <div className="col-8">

          <MapAirplanes />
        </div>
        <div className="col-2"/>
      </div>
      <br />
      
      <div className="row">
        <div className="col"/>
        <div className="col-6">

          <TableDubaiAirplanes />
        </div>
        <div className="col"/>
      </div>

    </div>
  );
}

export default App;
