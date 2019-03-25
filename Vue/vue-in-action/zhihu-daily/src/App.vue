<template>
    <div id="app" class="daily">
        <div class="daily-menu">
            <div class="daily-menu-item"
                 :class="{on : type === 'recommend'}"
                 @click="handleToRecommend">
                每日推荐
            </div>
            <div class="daily-menu-item"
                 :class="{on : type === 'daily'}"
                 @click="showThemes = !showThemes">
                主题日报
            </div>
            <ul v-show="showThemes">
                <li v-for="item in themes">
                    <a :class="{on :item.id === themeId && type === 'daily'}"
                       @click="handleToTheme(item.id)">
                        {{item.name}}
                    </a>
                </li>
            </ul>
        </div>
        <div class="daily-list" ref="list" @scroll="handleScroll">
            <template v-if="type === 'recommend'">
                <div v-for="list in recommendList">
                    <div class="daily-date"> {{formatDay(list.date)}}</div>
                    <item v-for="item in list.stories"
                          :data="item"
                          :key="item.id"
                          @click.native="handleClick(item.id)">
                    </item>
                </div>
            </template>
            <template v-if="type === 'daily'">
                <item v-for="item in list"
                      :data="item"
                      :key="item.id"
                      @click.native="handleClick(item.id)">
                </item>
            </template>
        </div>
        <daily-article :id="articleId"></daily-article>
    </div>
</template>

<script>
  import $ from './lib/util'
  import Item from './components/Item'
  import DailyArticle from './components/DailyArticle'

  export default {
    name: 'app',
    components: {
      Item,
      DailyArticle
    },
    data() {
      return {
        type: 'recommend',
        themes: [],
        themeId: 0,
        showThemes: false,
        list: [],
        recommendList: [],
        dailyTime: $.getTodayTime(),
        isLoading: false,
        articleId: 0
      }
    },
    methods: {
      getThemes() {
        // axios 发起 get 请求
        $.ajax.get('themes').then(res => {
          this.themes = res.others;
        });
      },
      handleToTheme(id) {
        // 改变菜单分类
        this.type = 'daily';
        // 设置当前点击子类的主题日报 id
        this.themeId = id;
        // 清空中间栏的数据
        this.list = [];
        $.ajax.get(`theme/${id}`).then(res => {
          // 过滤掉类型为 1 的文章，该类型下的文章为空
          this.list = res.stories.filter(item => item.type !== 1);
        });
      },
      handleToRecommend() {
        this.type = 'recommend';
        this.recommendList = [];
        this.dailyTime = $.getTodayTime();
        this.getRecommendList();
      },
      getRecommendList() {
        // 加载时设置为 true，加载完成后设置为 false
        this.isLoading = true;
        const prevDay = $.prevDay(this.dailyTime + 86400000);
        $.ajax.get(`news/before/${prevDay}`).then(res => {
          this.recommendList.push(res);
          this.isLoading = false;
        });
      },
      // 转换为带汉字的月日
      formatDay(date) {
        let month = date.substr(4, 2);
        let day = date.substr(6, 2);
        if (month.substr(0, 1) === '0') month = month.substr(1, 1);
        if (day.substr(0, 1) === '0') day = day.substr(1, 1);
        return `${month} 月 ${day} 日`;
      },
      handleScroll() {
        // 获取到 DOM
        const $list = this.$refs.list;
        // 在“主题日报”或正在加载推荐列表时停止操作
        if (this.type === 'daily' || this.isLoading) return;
        // 已经滚动的距离加页面的高度等于整个内容区域高度时，视为接触底部
        if ($list.scrollTop + document.body.clientHeight >= $list.scrollHeight) {
          // 时间相对减少一天
          this.dailyTime -= 86400000;
          this.getRecommendList();
        }
      },
      handleClick(id) {
        this.articleId = id;
      }
    },
    mounted() {
      // 初始化时调用
      this.getThemes();
      this.getRecommendList();
    }
  }
</script>
