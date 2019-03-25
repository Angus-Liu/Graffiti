<template>
  <div>
    <div>首页</div>
    <Counter :number="number"></Counter>
  </div>
</template>

<script>
import Counter from './counter'

export default {
  name: "index",
  components: {
    Counter
  },
  data () {
    return {
      number: 10
    }
  },
  methods: {
    handleAddRandom (num) {
      this.number += num;
    }
  },
  created () {
    this.$bus.on('add', this.handleAddRandom);
  },
  beforeDestroy () {
    this.$bus.off('add', this.handleAddRandom);
  }
}
</script>

<style scoped>
</style>
