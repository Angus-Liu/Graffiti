<template>
  <div class="header">
    <router-link to="/">
      <div class="iconfont back-icon">&#xe624;</div>
    </router-link>
    <div>城市选择</div>
  </div>
</template>

<script>
export default {
  name: 'CityHeader'
}
</script>

<style lang="stylus" scoped>
@import '~styles/variables.styl';

.header {
  .back-icon {
    position: absolute;
    float: left;
    width: 0.64rem;
    font-size: 0.4rem;
    color: #fff
  }

  overflow: hidden;
  height: $headerHight;
  line-height: $headerHight;
  text-align: center;
  color: #fff;
  background: $bgColor;
  font-size: 0.32rem;
}
</style>
