import axios from "axios";

// const apiUrl = "/api"; // root path
const apiUrl = "http://localhost:8080"; // root path
// const apiUrl = "/api"; // root path
// const apiUrl = "http://k02a4051.p.ssafy.io:8000/api"; // aws rest api => http
// const apiUrl = "http://k02a4051.p.ssafy.io:8443/api"; // aws rest api => https

// var config = {
//     headers: { "Access-Control-Allow-Origin": "*" },
// };

export default {
  getPharmacy(params) {
    return axios.get(`${apiUrl}/pharmacies`, {
      params,
      // errorHandle: false,
    });
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
