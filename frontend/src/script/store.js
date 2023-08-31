// 모듈 시스템을 사용하는 경우 Vue.use(Vuex)를 먼저 호출해야합니다.

import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            sharedData: '',
        };
    },
    mutations: {
        setSharedData(state, data) {
            state.sharedData = data;
        },
    },
    actions: {
        updateSharedData({ commit }, data) {
            commit('setSharedData', data);
        },
    },
    getters: {
        sharedData: (state) => state.sharedData,
    },
});

export default store;