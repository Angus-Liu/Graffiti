Vue.component('tabs', {
  template: `<div class="tabs">
                <div class="tabs-bar">
                    <!-- 标签页标题 -->
                    <div :class="tabCls(item)"
                         v-for="(item, index) in navList"
                         @click="handleChange(index)">
                        {{item.label}}
                    </div>
                </div>
                <div class="tabs-content">
                    <!-- 这里的 slot 就是嵌套的 pane -->
                    <slot></slot>
                </div>
            </div>`,
  props: {
    // 这里的 value 是为了可以使用 v-model
    value: {
      type: [String, Number]
    }
  },
  data() {
    return {
      // 因为不能修改 value，所以复制一份自己维护
      currentValue: this.value,
      // 用于渲染 tabs 的标题
      navList: []
    }
  },
  methods: {
    getTabs() {
      // 通过遍历子组件，得到所有的pane组件
      return this.$children.filter(item => item.$options.name = 'pane');
    },
    updateNav() {
      this.navList = [];
      this.getTabs().forEach((pane, index) => {
          this.navList.push({
            label: pane.label,
            name: pane.name || index
          });
          // 如果没有给 pane 设置 name，默认设置它的索引
          if (!pane.name) {
            pane.name = index;
          }
          // 设置当前选中的 tab 的索引
          if (index === 0) {
            if (!this.currentValue) {
              this.currentValue = pane.name || index;
            }
          }
        }
      );

      this.updateStatus();
    },
    updateStatus() {
      let tabs = this.getTabs();
      tabs.forEach(tab => tab.show = (tab.name === this.currentValue));
    },
    tabCls(item) {
      return [
        'tabs-tab',
        {
          // 给当前选中的 tab 加一个 class
          'tabs-tab-active': item.name === this.currentValue
        }
      ]
    },
    // 点击 tab 标题是触发
    handleChange(index) {
      let nav = this.navList[index];
      let name = nav.name;
      // 改变当前选中的 tab，并触发下面的 watch
      this.currentValue = name;
      // 更新 value
      this.$emit('input', name);
      // 触发一个自定义事件，供父级使用
      this.$emit('on-click', name);
    }
  },
  watch: {
    value(val) {
      this.currentValue = val;
    },
    currentValue() {
      // 当前选中的 tab 发生变化时，更新 pane 的显示状态
      this.updateStatus();
    }
  }
});
