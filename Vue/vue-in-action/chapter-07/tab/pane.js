Vue.component('pane', {
  name: 'pane',
  template: `<div class="pane" v-show="show">
                <slot></slot>
            </div>`,
  props: {
    // name 用以标识不同的 pane
    name: {
      type: String
    },
    // label 为标签页标题
    label: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      show: true
    }
  },
  methods: {
    updateNav() {
      this.$parent.updateNav();
    }
  },
  watch: {
    label() {
      this.updateNav();
    }
  },
  mounted() {
    this.updateNav();
  }
});
