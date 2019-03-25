<template>
  <div>
    <div>首页</div>
    <router-link
      to="/about"
      tag="button"
    >跳转到 About</router-link>
    <p>
      {{count}}
      <button @click="handleIncrease">+{{num}}</button>
      <button @click="handleActionIncrease">+1</button>
      <button @click="handleDecrease">-1</button>
    </p>
    <p>
      {{listCount}}: {{list}}
    </p>
  </div>
</template>

<script>
export default {
  name: "index",
  data () {
    return {
      num: 10
    }
  },
  computed: {
    count () {
      return this.$store.state.count;
    },
    list () {
      return this.$store.getters.filteredList;
    },
    listCount() {
      return this.$store.getters.listCount;
    }
  },
  methods: {
    handleIncrease () {
      this.$store.commit('increase', this.num);
    },
    handleDecrease () {
      this.$store.commit('decrease');
    },
    handleActionIncrease() {
      this.$store.dispatch('increase');
    }
  }
}
</script>

<style scoped>
</style>
