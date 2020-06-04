<template>
  <v-container class="mt-5">
    <Toolbar></Toolbar>
    <v-card-text class="text-center" > <!-- v-if="store"-->
    <div style="height:60px;"></div>
    <!-- <p class="display-1 pa-2">💊</p> -->
    <!-- <p id="title" class="accent--text">{{this.p_name}}</p> -->
    <p id="title" class="accent--text">{{this.$store.state.data.pharmacy.p_name}}</p>
    <!-- mapState 사용할때 -->
    <!-- <p id="title" class="accent--text">{{pharmacy.p_name}}</p> -->
    </v-card-text>

    <!-- v-if="p_status.length!= 0" -->
    <div v-if="checkChips()">
    
    </div>
    <v-tabs
      style="margin-top: 20px;"
      background-color="rgb(250,250,250)"
      color="primary"
      center-active
      class="fixed-tabs-bar primary"
      centered
      grow
      elevation="0"
    > <!-- class = "fixed-tabs-bar" -->
      <v-tab style="margin:0px">상세정보</v-tab>
      <v-tab>지도</v-tab>
      <v-tab>리뷰</v-tab>
      <v-tab-item>
        <!-- 상세정보 -->
        <Information
          :p_tel="this.$store.state.data.pharmacy.p_tel"
          :p_oper="this.$store.state.data.pharmacy.p_oper"
          :p_special="this.$store.state.data.pharmacy.p_special"
        />
      </v-tab-item>
      <v-tab-item>
        <!-- 지도-->
        <Map
          :p_name="this.$store.state.data.pharmacy.p_name"
          :p_post="this.$store.state.data.pharmacy.p_post"
          :p_addr="this.$store.state.data.pharmacy.p_addr"
          :p_loc="this.$store.state.data.pharmacy.p_loc"
          :latitude="this.$store.state.data.pharmacy.p_x"
          :longitude="this.$store.state.data.pharmacy.p_y"
        />
      </v-tab-item>
      <v-tab-item>
        <ReviewList />
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import Map from "@/components/Map"
import ReviewList from "@/components/ReviewList"
import Information from "@/components/Information"
import {  mapActions, mapState  } from "vuex";
import Toolbar from "@/components/Toolbar"

export default {
  components: {
    Information,
    Map,
    ReviewList,
    Toolbar,
  },
  data: function () {
    return {
      // chips: false,
    }
  },
  computed: {
    // mapState : state의 속성을 가져온다
    ...mapState({
      pharmacy : state => state.data.pharmacy,
    }),
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
    checkChips() {
      if(this.$store.state.data.pharmacy.p_status.length > 0) return true
      else false
    }
  },
  mounted() {
    this.getPharmacyDetail(this.$route.params.id)
  },
};
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
