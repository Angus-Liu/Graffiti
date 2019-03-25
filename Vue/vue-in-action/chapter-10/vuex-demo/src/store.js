import Vue from 'vue'
import Vuex from 'vuex'

// 使用 Vuex
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        count: 0,
        list: [1, 5, 8, 10, 30, 50]
    },
    getters: {
        filteredList: state => state.list.filter(item => item < 10),
        listCount: (state, getters) => getters.filteredList.length
    },
    mutations: {
        increase(state, n = 1) {
            state.count += n;
        },
        decrease(state, n = 1) {
            state.count -= n;
        }
    },
    actions: {
        increase(context) {
            context.commit('increase');
        }
    }
});
