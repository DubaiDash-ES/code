import axios from 'axios'

const AIRPLANES_DUBAI_API_URL = 'http://192.168.160.87:20004/data'

class AirplaneService {

    get_dubai_airplanes() {
        return axios.get(AIRPLANES_DUBAI_API_URL);
    }
}

export default new AirplaneService();