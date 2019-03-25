<template>
    <div v-if="product">
        <div class="product">
            <div class="product-image">
                <img :src="product.image">
            </div>
            <div class="product-info">
                <h1 class="product-name">{{product.name}}</h1>
                <div class="product-cost">￥ {{product.cost}}</div>
                <div class="product-add-cart" @click="handleAddToCart">加入购物车</div>
            </div>
        </div>
        <div class="product-desc">
            <h2>产品介绍</h2>
            <img :src="product.image" v-for="i in 10">
        </div>
    </div>
</template>

<script>
  import productData from '../res/product';

  export default {
    name: "product",
    props: {
      id: String
    },
    data() {
      return {
        product: {}
      }
    },
    methods: {
      getProduct() {
        // 真实环境通过 Ajax 获取，这里使用异步模拟
        setTimeout(() => {
          this.product = productData.find(item => item.id === Number(this.id));
          // console.log(this.product);
        }, 500);
      },
      // 加入购物车
      handleAddToCart() {
        this.$store.commit('addCart', this.product.id);
      }
    },
    mounted() {
      // 初始化时请求数据
      this.getProduct();
    }
  }
</script>

<style scoped>
    .product {
        margin: 32px;
        padding: 32px;
        background: #fff;
        border: 1px solid #dddee1;
        border-radius: 10px;
        overflow: hidden;
    }

    .product-image {
        width: 50%;
        height: 550px;
        float: left;
        text-align: center;
    }

    .product-image img {
        height: 100%;
    }

    .product-info {
        width: 50%;
        padding: 150px 0 250px;
        height: 150px;
        float: left;
        text-align: center;
    }

    .product-cost {
        color: #f2352e;
        margin: 8px 0;
    }

    .product-add-cart {
        display: inline-block;
        padding: 8px 64px;
        margin: 8px 0;
        background: #2d8cf0;
        color: #fff;
        border-radius: 4px;
        cursor: pointer;
    }

    .product-desc {
        background: #fff;
        margin: 32px;
        padding: 32px;
        border: 1px solid #dddee1;
        border-radius: 10px;
        text-align: center;
    }

    .product-desc img {
        display: block;
        width: 50%;
        margin: 32px auto;
        padding: 32px;
        border-bottom: 1px solid #dddee1;
    }
</style>
