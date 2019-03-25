<template>
    <div>Hello {{name}}</div>
</template>

<script>
  export default {
    data() {
      return {
        name: "Angus Liu"
      }
    }
  }
</script>

<style scoped>
    div {
        color: #f60;
        font-size: 24px;
    }
</style>
