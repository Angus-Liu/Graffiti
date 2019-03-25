<template>
    <div id="app">
        <!--<vTitle title="Vue组件化"></vTitle>-->
        <!--<vButton @click="handleClick">点击按钮</vButton>-->
        <!--<p>-->
            <!--<img src="./images/logo.png" alt="vue-logo">-->
        <!--</p>-->
        <router-view></router-view>
    </div>
</template>

<script>
  import vTitle from './components/vTitle';
  import vButton from './components/vButton';

  export default {
    name: 'app',
    components: {
      vTitle,
      vButton
    },
    methods: {
      handleClick(e) {
        console.log(e);
      }
    }
  }
</script>

<style>
    #app {
        font-size: 24px;
        color: #f50;
    }
</style>
