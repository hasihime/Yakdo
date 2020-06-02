import axios from "axios";

const apiUrl = "http://localhost:8080"; // root path
// const apiUrl = "http://k02a4051.p.ssafy.io:8000/api"; // aws rest api => http
// const apiUrl = "http://k02a4051.p.ssafy.io:8443/api"; // aws rest api => https

// var config = {
//     headers: { "Access-Control-Allow-Origin": "*" },
// };

export default {
  getPharmacy(p_x, p_y) {
    return axios.get(`${apiUrl}/pharmacy/list/p_x/${p_x}/p_y/${p_y}`);

    // axios
    //   .get(`${apiUrl}/stores`, {
    //     params,
    //     errorHandle: false,
    //   })
    //   .then((response) => {
    //     console.log(response);
    //     return response;
    //   })
    //   .catch((error) => {
    //     alert("검색 결과가 없습니다");
    //   });
  },
  getPharmacyDetail(id) {
    return axios.get(`${apiUrl}/pharmacies/${id}`);
  },
};
