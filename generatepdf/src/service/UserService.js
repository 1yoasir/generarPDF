import axios from 'axios'

export default class UserService {
    
    url =  "http://localhost:8080/api/users"

    getAll(){
        return axios.get(this.url);
    }
}