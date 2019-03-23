Vue.directive('clickoutside', {
  bind(el, binding, vnode) {
    function documentHandler(e) {
      if (e && e.keyCode === 27) { // 按 Esc
        if (binding.expression) {
          binding.value(e);
        }
        return true;
      }
      if (el.contains(e.target)) {
        return false;
      }
      if (binding.expression) {
        binding.value(e);
      }
    }

    el.__vueClickOutside__ = documentHandler;
    document.addEventListener('click', documentHandler);
    document.addEventListener('keyup', documentHandler);
  },

  unbind(el, binding) {
    document.removeEventListener('click', el.__vueClickOutside__);
    document.addEventListener('keyup', el.__vueClickOutside__);
    delete el.__vueClickOutside__;
  }
});
