<template>
    <div>
        <div>介绍页</div>
        <button @click="handleRouter">跳转到 User</button>
        {{count}}
    </div>
</template>

<script>
  export default {
    name: "about",
    computed: {
      count() {
        return this.$store.state.count;
      }
    },
    methods: {
      handleRouter() {
        this.$router.push('/user/Angus-Liu');
      }
    }
  }
</script>

<style scoped>

</style>
