export default {
    namespaced: true,
    state: {
        // 서울시청
        mylat: "37.566663",
        mylng: "126.978426",
    },
    mutations: {
        setMyPos(state, latlng) {
            // console.log("setMyPos", latlng);
            state.mylat = latlng.latitude;
            state.mylng = latlng.longitude;
        },
    },
    actions: {
        async getMyPos({ commit }) {
            // console.log("data js async get my pos");
            if (navigator.geolocation) {
                // 위치 정보를 얻기
                // console.log("Search vue methods getMyPos");
                navigator.geolocation.getCurrentPosition((pos) => {
                    const latlng = {
                        latitude: pos.coords.latitude,
                        longitude: pos.coords.longitude,
                    };
                    // console.log("geolocation으로 받아온 내 위치");
                    // console.log(latlng);
                    commit("setMyPos", latlng);
                    return latlng;
                });
            } else {
                alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
            }
        },
    },
};
