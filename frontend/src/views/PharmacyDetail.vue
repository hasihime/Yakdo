<template>
  <v-container class="mt-5">
    <v-card-text class="text-center" v-if="store">
      <!-- <p class="display-3 pa-2">🍜</p> -->
      <p class="display-2">{{store.name}} {{store.branch}}</p>
      <p v-if="store.categories">
        <v-chip
          label
          class="ma-1"
          color="red accent-1"
          text-color="white"
          v-for="category in (store.categories.split('|'))" :key="category">
          {{category}}
        </v-chip>
      </p>
      <div class="store_score" v-if="reviews.length != 0">
        <span>{{store.avg}}</span>
        <v-rating
          dense
          readonly
          half-increments
          :value="store.avg_dot5"
          background-color="orange lighten-3"
          color="orange"
          halfIcon='mdi-star-half-full'
        />
      </div>
      <br>
      <v-btn @click="goCreateReview" small color="red accent-2 white--text ma-5" rounded>리뷰 작성하기</v-btn>
      <!-- <v-btn large color="blue lighten-1 white--text ma-5" rounded to="/search">검색하기</v-btn> -->
    </v-card-text>
    <v-tabs
      background-color="rgb(250,250,250)"
      color="red accent-2"
      center-active
      class="fixed-tabs-bar"
      centered
      grow
      elevation="0"
    ><!--deep-purple accent-4 -->
      <!--
        https://vuetifyjs.com/ko/components/tabs/
    -->
      <v-tab style="margin:0px">메뉴</v-tab>
      <v-tab>지도</v-tab>
      <v-tab>리뷰</v-tab>
      <v-tab>사진</v-tab>
      <v-tab>통계</v-tab>
      <v-tab-item>
        <Menu 
          :menus="menus"
        />
      </v-tab-item>
      <v-tab-item>
        <Map
          :name="store.name"
          :address="store.address"
          :latitude="store.latitude"
          :longitude="store.longitude"
        /> <!-- 지도 -->
      </v-tab-item>
      <v-tab-item>
        <ReviewList
          :percent="reviewStatistics.percent"
          :count="reviewStatistics.count"
          :review_total_count="reviewStatistics.review_total_count"
          :reviews="reviews"
          :store_id="store.id"
          :store_name="store.name"
          :store_address="store.address"
          :store_avg="store.avg"
          :store_avg_dot5="store.avg_dot5"
        />
      </v-tab-item>
      <v-tab-item>
        <ReviewPhotos 
          :photos="reviewPhotos"
        /><!-- 리뷰 사진 -->
      </v-tab-item>
      <v-tab-item>
        <Menu /><!-- 통계 -->
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
// import Menu from "@/components/common/Menu"
// import ReviewList from "@/components/common/ReviewList"
// import ReviewPhotos from "@/components/common/ReviewPhotos"
// import Map from "@/components/Map"
import { mapState, mapActions } from "vuex";

export default {
  components : {
    // Menu,
    // ReviewList,
  },
  data : function() {
    return {
      
    }
  },
  computed: {
    ...mapState({
      store : state => state.data.store,
      menus : state => state.data.menus,
      reviews: state => state.data.reviews,
      reviewStatistics: state => state.data.reviewStatistics,
      reviewPhotos: state => state.data.reviewPhotos,
    })
  },
  mounted() {
    this.getStoreDetail(this.$route.params.id)
  },
  beforeDestroy() {
    this.removeStoreInfo()
  },
  methods: {
    ...mapActions("data", ["getStoreDetail", "removeStoreInfo"]),
    goCreateReview() {
      // router.pus가 작동이 안된다..!
      this.$router.push(`/store/${this.$route.params.id}/review`)
    }
  },
}
</script>

<style>
  .fixed-tabs-bar .v-tabs-bar {
    position: sticky;
    /* 
      PC 버전일 경우 toolbar의 height가 달라져 top도 달라져야 함.... 
      toolbar의 크기를 고정하거나 toolbar의 height를 얻어와서 적용해줘야 할듯....
      일단은 모바일로 맞춰놓고 다른 기능들이 얼추 완성되면 하자....
     */
    top: 56px;
    z-index: 2;
  }

  .store_score {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .store_score > span {
    color: #e7711b;
    white-space: nowrap;
    font-size: 1.2rem;
    font-weight: 600;
    margin-right: 10px;
  }
</style>
