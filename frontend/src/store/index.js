import Vue from "vue";
import Vuex from "vuex";

// 모든 모듈을 modules로 임포트
import * as modules from "./modules";
// import data from './modules/data'
import createPersistedState from "vuex-persistedstate";
// import moduleName from './module_modulename'

//VUEX 사용처리
Vue.use(Vuex);

//VUEX 스토어 생성
const store = new Vuex.Store({
  //모듈이 아니라 전역 상태로 사용할것이 있다면 이곳에 추가
  state: {},
  mutations: {},
  actions: {},
  modules: modules.default,
  plugins: [createPersistedState()],
});

// export default new Vuex.Store({
export default store;
