import axios from "axios";

const apiUrl = "http://localhost:8080"; // root path
// const apiUrl = "http://k02a4051.p.ssafy.io:8000/api"; // aws rest api => http
// const apiUrl = "http://k02a4051.p.ssafy.io:8443/api"; // aws rest api => https

// var config = {
//     headers: { "Access-Control-Allow-Origin": "*" },
// };

export default {
  findByPosition(p_x, p_y) {
    return axios.get(`${apiUrl}/pharmacy/list/p_x/${p_x}/p_y/${p_y}`);
  },
  findWithAddress(addr, p_x, p_y) {
    return axios.get(
      `${apiUrl}/pharmacy/address/${addr}/list/p_x/${p_x}/p_y/${p_y}`
    );
  },
  getPharmacyDetail(id) {
    return axios.get(`${apiUrl}/pharmacies/${id}`);
  },
};
