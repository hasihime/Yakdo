<template>
  <v-container class="mt-5">
    <v-card-text class="text-center" > <!-- v-if="store"-->
    <p class="display-1 pa-2">💊</p>
    <p id="title" class="accent--text">약국 이름</p> <!-- {{store.name}} {{store.branch}} -->
    </v-card-text>
    
    <v-chip
          label
          class="ma-1"
          color="secondary"
          text-color="white"
          v-for="category in (categories.split(','))" :key="category">
          {{category}}
        </v-chip>
    <v-tabs
      style="margin-top:20px"
      background-color="rgb(250,250,250)" 
      color="primary"
      center-active
      class="fixed-tabs-bar primary"
      centered
      grow
      elevation="0"
    >
      <v-tab style="margin:0px">상세정보</v-tab>
      <v-tab>지도</v-tab>
      <v-tab>리뷰</v-tab>
      <v-tab-item> <!-- 상세정보 -->
        <Information
        
        />
      </v-tab-item>
      <v-tab-item> <!-- 지도-->
        <Map
        />
          <!-- :name="store.name"
          :address="store.address"
          :latitude="store.latitude"
          :longitude="store.longitude" -->
      </v-tab-item>
      <v-tab-item> <!--리뷰-->
        <ReviewList
        />
          <!-- :percent="reviewStatistics.percent"
          :count="reviewStatistics.count"
          :review_total_count="reviewStatistics.review_total_count" -->
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import Map from "@/components/Map"
import ReviewList from "@/components/ReviewList"
import Information from "@/components/Information"
// import Map from "@/components/Map"
// import { mapState, mapActions } from "vuex";

export default {
  components : {
    Information,
    Map,
    ReviewList,
  },
  data : function() {
    return {
      categories: "연중무휴, 야간"
    }
  },
  computed: {
    // ...mapState({
    //   store : state => state.data.store,
    //   menus : state => state.data.menus,
    //   reviews: state => state.data.reviews,
    //   reviewStatistics: state => state.data.reviewStatistics,
    //   reviewPhotos: state => state.data.reviewPhotos,
    // })
  },
  mounted() {
    // this.getStoreDetail(this.$route.params.id)
  },
  beforeDestroy() {
    // this.removeStoreInfo()
  },
  methods: {
    // ...mapActions("data", ["getStoreDetail", "removeStoreInfo"]),
    // goCreateReview() {
    //   // router.pus가 작동이 안된다..!
    //   this.$router.push(`/store/${this.$route.params.id}/review`)
    // }
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

  /* .store_score {
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
  } */

  @media screen and (min-width: 601px) {
    #title {
      font-size: 60px;
    }
  }

  @media screen and (max-width: 600px) {
    #title {
      font-size: 40px;
    }
  }
</style>
