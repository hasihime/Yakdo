import axios from "axios";

const apiUrl = "/api"; // root path
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
