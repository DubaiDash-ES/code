package com.example.consumerdash;

import java.util.Arrays;

public class State {
    private Long flightID;
    public String icao24;
    public String callsign;
    public String origin_country;
    public int time_position;
    public int last_contact;
    public double longitude;
    public double latitude;
    public double baro_altitude;
    public boolean on_ground;
    public double velocity;
    public double true_track;
    public double vertical_rate;
    public int[] sensors;
    public double geo_altitude;
    public String squawk;
    public boolean spi;
    public int position_source;


    public State(){

    }

    @Override
    public String toString() {
        return "State [baro_altitude=" + baro_altitude + ", callsign=" + callsign + ", geo_altitude=" + geo_altitude
                + ", icao24=" + icao24 + ", last_contact=" + last_contact + ", latitude=" + latitude + ", longitude="
                + longitude + ", on_ground=" + on_ground + ", origin_country=" + origin_country + ", position_source="
                + position_source + ", sensors=" + Arrays.toString(sensors) + ", spi=" + spi + ", squawk=" + squawk
                + ", time_position=" + time_position + ", true_track=" + true_track + ", velocity=" + velocity
                + ", vertical_rate=" + vertical_rate + "]";
    }
    public String getIcao24() {
        return icao24;
    }
    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    public String getOrigin_country() {
        return origin_country;
    }
    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
    public int getTime_position() {
        return time_position;
    }
    public void setTime_position(int time_position) {
        this.time_position = time_position;
    }
    public int getLast_contact() {
        return last_contact;
    }
    public void setLast_contact(int last_contact) {
        this.last_contact = last_contact;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getBaro_altitude() {
        return baro_altitude;
    }
    public void setBaro_altitude(double baro_altitude) {
        this.baro_altitude = baro_altitude;
    }
    public boolean isOn_ground() {
        return on_ground;
    }
    public void setOn_ground(boolean on_ground) {
        this.on_ground = on_ground;
    }
    public double getVelocity() {
        return velocity;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public double getTrue_track() {
        return true_track;
    }
    public void setTrue_track(double true_track) {
        this.true_track = true_track;
    }
    public double getVertical_rate() {
        return vertical_rate;
    }
    public void setVertical_rate(double vertical_rate) {
        this.vertical_rate = vertical_rate;
    }
    public int[] getSensors() {
        return sensors;
    }
    public void setSensors(int[] sensors) {
        this.sensors = sensors;
    }
    public double getGeo_altitude() {
        return geo_altitude;
    }
    public void setGeo_altitude(double geo_altitude) {
        this.geo_altitude = geo_altitude;
    }
    public String getSquawk() {
        return squawk;
    }
    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }
    public boolean isSpi() {
        return spi;
    }
    public void setSpi(boolean spi) {
        this.spi = spi;
    }
    public int getPosition_source() {
        return position_source;
    }
    public void setPosition_source(int position_source) {
        this.position_source = position_source;
    }

    public Long getFlightID() {
        return flightID;
    }

    public void setFlightID(Long flightID) {
        this.flightID = flightID;
    }

    public Coordinates getCoordinates()
    {
        return new Coordinates(this.latitude, this.longitude);
    }
}

