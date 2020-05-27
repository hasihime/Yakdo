import { set, toggle } from "@/utils/vuex";

const state = {
    isOpen: null
};

// mutations
const mutations = {
    setOpen: set("isOpen"),
    toggleOpen: toggle("isOpen")
};

export default {
    namespaced: true,
    state,
    mutations
};
