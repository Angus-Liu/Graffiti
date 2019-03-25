<template>
    <div id="app">
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
