import axios from "axios";

//  axios.defaults.baseURL = "http://myurl";
// axios.defaults.headers.post["Content-Type"] = "application/json;charset=utf-8";
// axios.defaults.headers.post["Access-Control-Allow-Origin"] = "*";

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
      `${apiUrl}/pharmacy/list/address/${addr}/p_x/${p_x}/p_y/${p_y}`
    );
  },
  getPharmacyDetail(id) {
    return axios.get(`${apiUrl}/pharmacy/${id}`);
  },
  getReviews(id) {
    return axios.get(`${apiUrl}/review/all/${id}`);
  },
  postReview(params) {
    return axios.post(`${apiUrl}/review/create`, params);
  },
  putReview(params) {
    return axios.put(`${apiUrl}/review/update`, params)
  },
  deleteReview(params) {
    return axios.delete(`${apiUrl}/review/delete`, {data: params})
  }
};
