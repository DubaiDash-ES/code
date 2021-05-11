package com.example.consumerdash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties
public class StateInfo {
    private int time;
    private List<Object> states = new ArrayList<Object>();
    private List<State> state_obj = new ArrayList<State>();



    @Override
    public String toString()
    {
        return "Time -> " + Integer.toString(time) + " States -> " + states.toString();
    }

    public void Fill_States()
    {
        for(int i = 0; i < states.size(); i ++)
        {
            List<Object> object = (List<Object>)states.get(i);
            State state = new State();
            try{state.icao24 = object.get(0).toString();} catch(Exception e){};
            try{state.callsign = object.get(1).toString();} catch(Exception e){};
            try{state.origin_country = object.get(2).toString();}catch(Exception e){};
            try{state.time_position = (int)object.get(3);}catch(ClassCastException e){state.time_position =  (int)(double)object.get(3);}catch(Exception e){};
            try{state.last_contact = (int)object.get(4);}catch(ClassCastException e){state.last_contact = (int)(double)object.get(4);}catch(Exception e){};
            try{state.longitude = (double)object.get(5);}catch(ClassCastException e){state.longitude = (double)(int)object.get(5);}catch(Exception e){};
            try{state.latitude = (double)object.get(6);}catch(ClassCastException e){state.latitude = (double)(int)object.get(6);}catch(Exception e){};;
            try{state.baro_altitude = (double)object.get(7);}catch(ClassCastException e){state.baro_altitude = (double)(int)object.get(7);}catch(Exception e){};
            try{state.on_ground = (boolean)object.get(8);}catch(Exception e){};
            try{state.velocity = (double)object.get(9);}catch(ClassCastException e){state.velocity = (double)(int)object.get(9);}catch(Exception e){};;
            try{state.true_track = (double)object.get(10);}catch(ClassCastException e){state.true_track = (double)(int)object.get(10);}catch(Exception e){};;
            try{state.vertical_rate = (double)object.get(11);}catch(ClassCastException e){state.vertical_rate = (double)(int)object.get(11);}catch(Exception e){};
            try{state.sensors = (int[])object.get(12);}catch(Exception e){}
            try{state.geo_altitude = (double)object.get(13);}catch(ClassCastException e){state.geo_altitude = (double)(int)object.get(13);}catch(Exception e){};
            try{state.squawk = object.get(14).toString().toString();}catch(Exception e){};
            try{state.spi = (boolean)object.get(15);}catch(Exception e){};
            try{state.position_source = (int)object.get(16);}catch(ClassCastException e){state.position_source = (int)(double)object.get(16);}catch(Exception e){};
            state_obj.add(state);
        }
    }

    public int getTime() {
        return time;
    }

    public List<Object> getStates() {
        return states;
    }

    public List<State> getStateObj() {
        return state_obj;
    }
}
