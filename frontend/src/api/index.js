import axios from "axios";

//  axios.defaults.baseURL = "http://myurl";
// axios.defaults.headers.post["Content-Type"] = "application/json;charset=utf-8";
// axios.defaults.headers.options["Access-Control-Allow-Origin"] = "*";
// axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
// const apiUrl = "https://localhost:8080";
const apiUrl = "https://k02a4051.p.ssafy.io:8080"; // root path
// const apiUrl = "http://k02a4051.p.ssafy.io:8000/api"; // aws rest api => http
// const apiUrl = "http://k02a4051.p.ssafy.io:8443/api"; // aws rest api => https
const dataApiKey =
  "7dEJS48PwXN6rT93XTdOBCbSFVmjlgThU%2B3wWEhAvICcb44j9UGJ6MqqwNUnm%2FHdKgSJoOJSVz9WFUyNBoYyDw%3D%3D";
const dataApiUrl =
  "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire?serviceKey=";
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
  findWithGeoCoder(p_x, p_y) {
    return axios.get(`${apiUrl}/pharmacy/list/geocoder/p_x/${p_x}/p_y/${p_y}`);
  },
  getPharmacyDetail(id) {
    return axios.get(`${apiUrl}/pharmacy/${id}`);
  },
  getPharmacyDetailApi(p_sido, p_gugun, p_name) {
    var xhr = new XMLHttpRequest();
    var url = `${dataApiUrl}${dataApiKey}&Q0=${p_sido}&Q1=${p_gugun}&QN=${p_name}`;
    xhr.open("get", url, true);
    var doc = null;
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        doc = xhr.responseXML;
        console.log(doc);
        // …
      }
    };
    xhr.send(null);
    return doc;
  },
  getReviews(id) {
    return axios.get(`${apiUrl}/review/all/${id}`);
  },
  postReview(params) {
    return axios.post(`${apiUrl}/review/create`, params);
  },
  putReview(params) {
    return axios.put(`${apiUrl}/review/update`, params);
  },
  deleteReview(params) {
    return axios.delete(`${apiUrl}/review/delete`, { data: params });
  },
};
