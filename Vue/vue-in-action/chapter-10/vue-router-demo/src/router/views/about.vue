<template>
    <div>
        <div>介绍页</div>
        <button @click="handleRouter">跳转到 User</button>
    </div>
</template>

<script>
  export default {
    name: "about",
    methods: {
      handleRouter() {
        this.$router.push('/user/Angus-Liu');
      }
    }
  }
</script>

<style scoped>

</style>
