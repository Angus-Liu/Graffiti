let app = new Vue({
  el: '#app',
  data: {
    columns: [
      {
        title: '姓名',
        key: 'name'
      }, {
        title: '年龄',
        key: 'age',
        sortable: true
      }, {
        title: '出生日期',
        key: 'birthday',
        sortable: true
      }, {
        title: '地址',
        key: 'address'
      }
    ],
    data: [{
      name: "Angus-Liu",
      age: 22,
      birthday: '1996-05-27',
      address: '四川省成都市蒲江县'
    }, {
      name: "王小明",
      age: 14,
      birthday: '2004-05-27',
      address: '四川省成都市蒲江县'
    }, {
      name: "黄小琥",
      age: 20,
      birthday: '1998-10-01',
      address: '四川省成都市蒲江县'
    }, {
      name: "赫本",
      age: 18,
      birthday: '2001-01-01',
      address: '四川省成都市蒲江县'
    }]
  },
  methods: {
    handleAddData() {
      this.data.push({
        name: "Mary",
        age: 22,
        birthday: '1997-01-01',
        address: '四川省成都市蒲江县'
      });
    }
  }
});
