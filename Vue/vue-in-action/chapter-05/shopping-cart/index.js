var app = new Vue({
  el: '#app',
  data: {
    list: [
      {
        id: 1,
        name: 'Kindle 1',
        price: 1200,
        count: 1
      }, {
        id: 2,
        name: 'Kindle 2',
        price: 1300,
        count: 1
      }, {
        id: 2,
        name: 'iReader Note',
        price: 699,
        count: 1
      }, {
        id: 1,
        name: 'iReader Plus',
        price: 999,
        count: 1
      }
    ]
  },
  computed: {
    totalPrice() {
      let total = 0;
      this.list.forEach(item => total += item.price * item.count);
      return total.toString().replace(/\B(?=(\d{3})+$)/g, ',');
    }
  },
  methods: {
    handleReduce(index) {
      if (this.list[index].count === 1) return;
      this.list[index].count--;
    },
    handleAdd(index) {
      this.list[index].count++;
    },
    handleRemove(index) {
      this.list.splice(index, 1);
    }
  }
});
