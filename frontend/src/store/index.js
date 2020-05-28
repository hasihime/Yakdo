import Vue from 'vue'
import Vuex from 'vuex'
import data from './modules/data'
import createPersistedState from 'vuex-persistedstate'
// import moduleName from './module_modulename'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        data,
    },
    plugins: [
        createPersistedState()
    ]
})