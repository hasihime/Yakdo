<template>
  <v-container class="mt-5">
    <v-card-text class="text-center" > <!-- v-if="store"-->
    <p class="display-1 pa-2">💊</p>
    <p id="title" class="accent--text">{{this.p_name}}</p>
    <!-- <p id="title" class="accent--text">{{this.$store.state.data.pharmacy.p_name}}</p> -->
    <!-- mapState 사용할때 -->
    <!-- <p id="title" class="accent--text">{{pharmacy.p_name}}</p> -->
    </v-card-text>
    
      <!-- v-if="p_status.length!= 0" -->
    <v-chip
      label
      class="ma-1"
      color="secondary"
      text-color="white"
      v-for="category in (p_status.split(','))" :key="category">
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
          :p_tel="this.p_tel"
          :p_oper="this.p_oper"
          :p_special="this.p_special"
        />
      </v-tab-item>
      <v-tab-item> <!-- 지도-->
        <Map
          :p_post="this.p_post"
          :p_addr="this.p_addr"
          :p_loc="this.p_loc"
          :latitude="this.p_x"
          :longitude="this.p_y"
        />
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
import {  mapActions  } from "vuex"; //mapState,

export default {
  components : {
    Information,
    Map,
    ReviewList,
  },
  data : function() {
    return {
      p_id: 0,
      p_name: "",
      p_addr: "",
      p_tel: "",
      p_oper: "",
      p_status: "",
      p_special: "",
      p_loc: "",
      p_post: "",
      p_x: 0,
      p_y: 0,
      // categories: "연중무휴, 야간"
    }
  },
  computed: {
    // mapState : state의 속성을 가져온다

    // ...mapState({
    //   pharmacy : state => state.data.pharmacy,
    // }),

    // // mapGetter : getters에 정의된 메서드를 가져온다
    // ...mapGetters('modulename', {
    //   getId: 'get_id'
    // })
    
  },
  beforeDestroy() {
    // this.removeStoreInfo()
  },
  methods: {
    // actions에 정의된 메서드를 가져온다

    ...mapActions("data", ["getPharmacyDetail"]),
    // goCreateReview() {
      //   // router.pus가 작동이 안된다..!
    //   this.$router.push(`/store/${this.$route.params.id}/review`)
    // }
    
  },
  mounted() {
    this.getPharmacyDetail(this.$route.params.id)

    const pharmacy = this.$store.state.data.pharmacy
    // console.log(pharmacy)
    this.p_id = pharmacy.p_id
    this.p_name = pharmacy.p_name
    this.p_addr = pharmacy.p_addr
    this.p_tel = pharmacy.p_tel
    this.p_oper = pharmacy.p_oper

    // 문자열 포함 여부
    // includes("aaa") : ES6부터 사용 가능
    // search("aaa") : 문자 포함하고있으면 인덱스리턴, 없으면 -1 리턴
    if(pharmacy.p_status.includes("연중")) {    // "연중"
      this.p_status = "연중무휴"
    }else if(pharmacy.p_status.includes(".")){  // "연.야"
      this.p_status = "연중무휴,야간"
    }else {                                     // "야간"
      this.p_status = pharmacy.p_status
    }
    if(pharmacy.p_special.length) {
      var specialArr = pharmacy.p_special.split(":")
      // 공백제거, ]제거
      var special_blank = specialArr[1],
          special = special_blank.replace(' ',''),
          p_special = special.replace(']','')
      this.p_special = p_special
    }else{
      this.p_special  = pharmacy.p_special
    } 
    if(pharmacy.p_loc.length) {
      var locArr = pharmacy.p_loc.split(":")
      // 공백제거, ]제거
      var loc_blank = locArr[1],
          loc = loc_blank.replace(' ',''),
          p_loc = loc.replace(']','')
      this.p_loc = p_loc
    }else{
      this.p_loc  = pharmacy.p_loc
    } 
    this.p_post = pharmacy.p_post
    this.p_x = pharmacy.p_x
    this.p_y = pharmacy.p_y
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
