<script setup>
    import GoodsShow from '../Goods/GoodsShow.vue'
</script>

<template>
    <div class="wrap" >
        <div class="edit">
            <h2>待审核</h2>
            <div class="goods">
                <div v-for="goods in registeringGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
            <div class="pages">
                <el-button class="pageBtn">上一页</el-button>
                <span>1/10</span>
                <el-button class="pageBtn">下一页</el-button>
            </div>
        </div>

        <div class="edit">
            <h2>注册失败</h2>
            <div class="goods">
                <div v-for="goods in registerationDeniedGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
            <div class="pages">
                <el-button class="pageBtn">上一页</el-button>
                <span>1/10</span>
                <el-button class="pageBtn">下一页</el-button>
            </div>
        </div>

        <div class="edit">
            <h2>注册成功</h2>
            <div class="goods">
                <div v-for="goods in registeredGoodsList" :key="goods.goodsId">
                    <GoodsShow :goods="goods"/>
                </div>
            </div>
            <div class="pages">
                <el-button class="pageBtn">上一页</el-button>
                <span>1/10</span>
                <el-button class="pageBtn">下一页</el-button>
            </div>
        </div>

    </div>
</template>


<script>
export default {
    components: {
        GoodsShow
    },
    data() {
        return {
            registeringGoodsList: [],
            registerationDeniedGoodsList: [],
            registeredGoodsList:[],
            goods:{
                goodsname: "goodsname",
                goodsId: "id",
                registerStatus: 0,
                modifyStatus: 2,
                status: 2,
                goodsPrice:100,
                goodsStock:20,
                introduction:"intro",
                goodsCategory: ['电脑数码', '家用电器']
            }
        }
    },
    created (){
        this.getRegisteringGoods();
        this.getRegisteredGoods();
        this.getRregisterationDeniedGoods();
    },
    methods: {
        getRegisteringGoods() {
            this.registeringGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisteringGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registeringGoodsList = res.data.data;
            })
        },
        getRegisteredGoods() {
            this.registeredGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisteredGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registeredGoodsList = res.data.data;
            })
        },
        getRregisterationDeniedGoods() {
            this.registerationDeniedGoodsList = [this.goods];
            var localStorage = window.localStorage;
            this.$axios.post("/getRegisterationDeniedGoodsByShopname", {
                shopname: localStorage.getItem("shopname")
            }).then(res => {
                this.registerationDeniedGoodsList = res.data.data;
            })
        }
    }
}


/* 这是一段chatgpt给出的翻页的js代码，可以参考
export default {
  data() {
    return {
      products: [],
      currentPage: 1,
      totalPages: 1,
      pageSize: 10, // 每页显示的商品数量
    };
  },
  methods: {
    loadProducts(page) {
      // 发送 GET 请求到 PHP 接口，检索指定页数的商品信息
      axios
        .get("/api/products.php", {
          params: {
            page: page,
            pageSize: this.pageSize,
          },
        })
        .then((response) => {
          // 更新商品信息和页数信息
          this.products = response.data.products;
          this.currentPage = response.data.currentPage;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.loadProducts(this.currentPage - 1);
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.loadProducts(this.currentPage + 1);
      }
    },
  },
  mounted() {
    // 加载第一页的商品信息
    this.loadProducts(1);
  },
};
*/
</script>



<style scoped>
/*小标题*/
.wrap h2{
  text-align: left;
  font-family:"Lucida Console", "Courier New", monospace;
  font-size: 30px;
  font-weight: bolder;
  color:#81A18B;
  margin-bottom: 30px;
}
/*包装框*/
.edit{
    padding: 30px;
    background-color: #fff;
    border-radius: 15px;
    color: #303133;
    border: 3px solid #81A18B;
    transition: .3s;
    margin-bottom: 40px;
}

/*翻页按钮*/
.pages{
    display: flex;
    margin:50px auto 0px;
}
.pages span{
    align-content:end;
    color:#81A18B;
    font-weight: bolder;
}
.pageBtn{
    display: flex;
    margin:0 auto;
}
</style>