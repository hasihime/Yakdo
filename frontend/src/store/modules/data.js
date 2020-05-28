import api from "../../api"

// initial state
const state = {
    pharmacy: {
        p_id: 0,
        p_name: "약국",
        p_addr: "",
        p_tel: "",
        p_oper: "", // "09:00 ~ 19:00"
        p_status: "", // "연.야"
        p_special: "",
        p_loc: "", // [위치정보 : 초량6거리버스정류소]
        p_post: "", // 우편번호
        p_x: 0,
        p_y: 0,
    },
    review: {
        r_id: 0,
        r_writer: "",   // 작성자(로그인:구글ID, 비로그인:IP)
        r_pw: "",       // 비로그인시 패스워드
        r_conetent: "", // 리뷰 내용
        p_id: 0,        // 약국 id
    },
    reviews: {},
    
}

// Mutations의 주요 목적은 state를 변경시키는 역할
// (Mutations을 통해서만 state를 변경해야 함)
// 비동기 처리가 아니라 동기처리를 합니다.
// 위의 함수가 실행되고 종료된 후 그 다음 아래의 함수가 실행됩니다.
// commit('함수명', '전달인자')으로 실행 시킬 수 있습니다.
// mutations 내에 함수 형태로 작성합니다.
export const mutations = {
    setPharmacy(state, pharmacy) {
        state.pharmacy = pharmacy
    }
}

// Actions의 주요 목적은 Mutations를 실행시키는 역할
// Mutations이 실행되면 state도 변경됨
// 동기 처리가 아니라 비동기처리를 합니다.
// 순서에 상관없이 먼저 종료된 함수의 피드백을 받아 후속 처리를 하게 됩니다.
// dispatch('함수명', '전달인자')으로 실행 시킬 수 있습니다.
// ex) dispatch('함수명', '전달인자', {root:true})
// actions 내에 함수 형태로 작성합니다.
// 비동기 처리이기 때문에 콜백함수로 주로 작성합니다.
export const actions = {
    getPharmacyDetail({commit}, id) { // {commit, dispatch}
        api.getPharmacyDetail(id)
            .then(response => {
                // console.log(pharmacy)
                // console.log(response.data.info)
                commit('setPharmacy', response.data.info)
            })
            .catch(() => {
                console.log("받아오기 실패")
            })
        // dispatch()
    },

    // 안되는 코드! 따라하지마세유
    // postReview(review) {
    //     console.log("store-data.js : reviewㅡㅡㅡㅡ")
    //     console.log(review)
    //     console.log("type:"+typeof(review))
    //     api.postReview(review)
    //         .then(response =>{
    //             console.log(response.data)
    //         })
    //         .catch(() => {
    //             console.log("리뷰 등록 실패")
    //         })
    // }
}

// 각 Components의 계산된 속성(computed)의 공통 사용 정의
// 여러 Components에서 동일한 computed가 사용 될 경우
// Getters에 정의하여 공통으로 쉽게 사용할 수 있습니다.
// 하위 모듈의 getters를 불러오기 위해서는
// 특이하게 this.$store.getters["경로명/함수명"]; 을 사용해야 합니다.
export const getters = {
    // getPharmacy(state) {
    //     return !!state.pharmacy // boolean return
    // }
    getPharmacy(state) {
        return state.pharmacy
    },
}

export default {
    namespaced: true,
    state,
    actions,
    mutations,
}
