Vue.component('vInput', {
  props: {
    value: [String, Number],
    default: ''
  },
  render(h) {
    let _this = this;
    return h('div', [
      h('span', '昵称：'),
      h('input', {
        attrs: {
          type: 'text'
        },
        domProps: {
          value: this.value
        },
        on: {
          input(event) {
            //_this.value = event.target.value;
            _this.$emit('input', event.target.value);
          }
        }
      })
    ]);
  }
});

Vue.component('vTextarea', {
  props: {
    value: {
      type: String,
      default: ''
    }
  },
  render: function(h) {
    let _this = this;
    return h('div', [
      h('span', '留言内容：'),
      h('textarea', {
        attrs: {
          placeholder: '请输入留言内容'
        },
        domProps: {
          value: this.value
        },
        ref: 'message',
        on: {
          input(event) {
            //_this.value = event.target.value;
            _this.$emit('input', event.target.value);
          }
        }
      })
    ]);
  },
  methods: {
    focus() {
      this.$refs.message.focus();
    }
  }
});
