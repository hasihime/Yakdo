import api from "../../api";

// initial state
const state = {
    pharmacySearchList: [],
    pharmacySearchPage: "1",
    pharmacy: {
        id: 0,
        name: "",
        tel: "",
        address: "",
        lat: 0,
        lng: 0,
    },
    pharmacySearchKeepGoing: true,
};

// actions
const actions = {
    async getPharmacies({ commit }, params) {
        const append = params.append;
        const resp = await api.getPharmacies(params);
        // console.log(resp);
        if (resp.data.results.length == 0) {
            alert("검색 결과가 없습니다");
            location.reload();
        } else {
            const pharmacies = resp.data.results.map((d) => ({
                id: d.id,
                name: d.name,
                tel: d.tel,
                address: d.address,
                lat: d.latitude,
                lng: d.longitude,
            }));

            if (append) {
                commit("addPharmacySearchList", pharmacies);
            } else {
                commit("setPharmacySearchList", pharmacies);
            }

            if (resp.data.next !== null) {
                commit("setPharmacySearchPage", resp.data.next);
            } else {
                const onoff = false;
                commit("setPharmacySearchKeepGoing", onoff);
            }
        }
    },
    async getPharmacyDetail({ commit }, id) {

        const resp = await api.getPharmacyDetail(id);
        const data = resp.data;
        const pharmacy = {
            id: data.id,
            name: data.name,
            tel: data.tel,
            address: data.address,
            latitude: data.latitude,
            longitude: data.longitude,
        }
        console.log(pharmacy)

        commit("setPharmacyDetailInfo", pharmacy)
    }
};

// mutations
const mutations = {
    setPharmacySearchList(state, pharmacies) {
        state.PharmacySearchList = pharmacies.map((s) => s);
    },
    addPharmacySearchList(state, pharmacies) {
        state.PharmacySearchList = state.PharmacySearchList.concat(pharmacies);
    },
    setPharmacySearchPage(state, url) {
        // console.log(url);
        state.PharmacySearchPage = new URL(url).searchParams.get("page");
    },
    setPharmacyDetailInfo(state, Pharmacy) {
        state.Pharmacy = Pharmacy;
    },
    changePharmacyDetailInfo(state, Pharmacy) {
        state.Pharmacy.avg = Pharmacy.avg;
        state.Pharmacy.avg_dot5 = Pharmacy.avg_dot5;
    },
};


export default {
    namespaced: true,
    state,
    actions,
    mutations,
};
