import axios from "axios";

// const apiUrl = "/api"; // root path
const apiUrl = "http://localhost:8080"; // root path
// const apiUrl = "http://i02a104.p.ssafy.io:8000/api"; // aws rest api
// var config = {
//   headers: { "Access-Control-Allow-Origin": "*" },
// };

export default {
//   getStores(params) {
//     return axios.get(`${apiUrl}/stores`, {
//       params,
//     });
//   },
  getPharmacyDetail(id) {
    return axios.get(`${apiUrl}/pharmacy/${id}`);
  },
//   getStoreMenu(params) {
//     return axios.get(`${apiUrl}/menus`, {
//       params,
//     });
//   },
//   getStoreReview(params) {
//     return axios.get(`${apiUrl}/reviews`, {
//       params,
//     });
//   },
//   getReviewUser(id) {
//     return axios.get(`${apiUrl}/users/${id}`);
//   },
  postReview(params) {
    return axios.post(`${apiUrl}/review/create`, params);
  },
//   postStoreReviewImages(params) {
//     return axios.post(`${apiUrl}/review_images`, params);
//   },
//   getReviewImage(params) {
//     return axios.get(`${apiUrl}/review_images`, {
//       params,
//     });
//   },
//   getLogin(params) {
//     return axios.get(`${apiUrl}/users/login`, {
//       params,
//     });
//   },
//   postSignUp(params) {
//     return axios.post(`${apiUrl}/users`, params);
//   },
//   putEditInfo(params) {
//     return axios.put(`${apiUrl}/users/${sessionStorage.getItem("user_no")}`, 
//       params
//     );
//   },
//   getUser(id) {
//     return axios.get(`${apiUrl}/users/${id}`);
//   },
//   loadWorldcup(params) {
//     return axios.get(`${apiUrl}/stores/loadWorldcup`, {
//       params,
//     });
//   },
//   getWorldcup(params) {
//     return axios.get(`${apiUrl}/stores/getWorldcup`, {
//       params,
//     });
//   },
};
