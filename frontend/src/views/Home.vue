<template>
  <div>
    <!-- 카카오맵 -->
    <div id="map" style="height:100vh;"></div>
    <!-- 상단바는 맵 위로 올라가야한다 -->

    <div class="menus">
      <v-btn class="menu-btn" icon @click="onClickOpener">
        <!--                <span class="mdi mdi-view-list"></span>-->
        <v-icon>mdi-view-list</v-icon>
      </v-btn>

      <input
        id="text"
        type="text"
        class="search-bar"
        name="search"
        v-on:keyup.enter="submit"
        placeholder="도로명 주소 검색(강동구 or 올림픽로)"
      />

      <v-btn class="search-btn" icon @click.stop="doSearch()">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <!-- https://apis.map.kakao.com/web/sample/moveMap/ -->
      <v-btn class="pos-btn" icon @click.stop="panTo(mylat, mylng)">
        <v-icon>mdi-crosshairs-gps</v-icon>
      </v-btn>
    </div>
    <!--        <div class="notice-bar content-width">-->
    <!--            &lt;!&ndash;안내 메시지 &ndash;&gt;-->
    <!--            (예시) 가장 가까운 약국은 500m 떨어진 XX 약국이며, 운영종료시간은 22시입니다.-->
    <!--            <br>-->
    <!--            (예시) 반경 1km 내에 열린 약국이 없습니다.-->
    <!--        </div>-->

    <v-btn class="left-btn" icon @click.stop="minusIdx()">
      <v-icon>mdi-chevron-left</v-icon>
    </v-btn>

    <!-- <v-layout v-for="i in 3" :key="i">
      <div class="yg content-width">
        <span class="left-align">{{ pharmacies[idx + i].p_name }}</span>
        <span class="left-align">{{ pharmacies[idx + i].distance }} m</span>
        <v-btn
          class="call-btn right-align"
          icon
          @click="document.location.href = `tel:${pharmacies[idx + i].p_tel}`"
        >
          <v-icon>mdi-phone</v-icon>
        </v-btn>
        <v-btn
          class="road-btn right-align"
          icon
          @click.stop="panTo(pharmacies[idx + i].p_x, pharmacies[idx + i].p_y)"
        >
          <v-icon>mdi-map-search</v-icon>
        </v-btn>
      </div>
    </v-layout> -->

    <div v-if="pharmacies[idx] != null" class="yg-list yg1 content-width">
      <span class="left-align">{{ pharmacies[idx].p_name }}</span>
      <span class="left-align" style="font-size : small; padding-top:14px;"
        >{{ pharmacies[idx].distance }} m</span
      >
      <v-btn
        class="call-btn right-align"
        icon
        @click="document.location.href = `tel:${pharmacies[idx + i].p_tel}`"
      >
        <v-icon>mdi-phone</v-icon>
      </v-btn>
      <v-btn
        class="road-btn right-align"
        icon
        @click.stop="panTo(pharmacies[idx].p_x, pharmacies[idx].p_y)"
      >
        <v-icon>mdi-map-search</v-icon>
      </v-btn>
    </div>

    <div v-if="pharmacies[idx + 1] != null" class="yg-list yg2 content-width">
      <span class="left-align">{{ pharmacies[idx + 1].p_name }}</span>
      <span class="left-align" style="font-size : small; padding-top:14px;"
        >{{ pharmacies[idx + 1].distance }} m</span
      >
      <v-btn class="call-btn right-align" icon @click="telephone(idx)">
        <v-icon>mdi-phone</v-icon>
      </v-btn>
      <v-btn
        class="road-btn right-align"
        icon
        @click.stop="panTo(pharmacies[idx + 1].p_x, pharmacies[idx + 1].p_y)"
      >
        <v-icon>mdi-map-search</v-icon>
      </v-btn>
    </div>

    <div v-if="pharmacies[idx + 2] != null" class="yg-list yg3 content-width">
      <span class="left-align">{{ pharmacies[idx + 2].p_name }}</span>
      <span class="left-align" style="font-size : small; padding-top:14px;"
        >{{ pharmacies[idx + 2].distance }} m</span
      >
      <v-btn class="call-btn right-align" icon @click="telephone(idx + 2)">
        <v-icon>mdi-phone</v-icon>
      </v-btn>
      <v-btn
        class="road-btn right-align"
        icon
        @click.stop="panTo(pharmacies[idx + 2].p_x, pharmacies[idx + 2].p_y)"
      >
        <v-icon>mdi-map-search</v-icon>
      </v-btn>
    </div>

    <v-btn class="right-btn" icon @click.stop="plusIdx()">
      <v-icon>mdi-chevron-right</v-icon>
    </v-btn>

    <!-- 네비게이션바는 기본적으로 감춰져있어야한다 -->
    <navbar></navbar>
    <!--  -->
  </div>
</template>

<script>
import api from "@/api";
import Navbar from "@/components/main/Navbar";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  components: {
    Navbar,
  },
  data() {
    return {
      menutoggle: false,
      kmap: null,
      pharmacies: [],
      idx: 0,
      maxIdx: 0,
    };
  },
  computed: {
    ...mapState("navbar", ["isOpen"]),
    ...mapState({
      mylat: function() {
        return this.$store.state.map.mylat;
      },
      mylng: function() {
        return this.$store.state.map.mylng;
      },
    }),
  },
  created() {
    // 상위 App created => 하위 child created
    console.log("Home vue created");
    this.getMyPos();
    // console.log("this.mylat : ", this.mylat);
    // console.log("this.mylng : ", this.mylng);
  },
  mounted() {
    // child 인 Home 의 mounted가 끝나고 => 상위 mounted 실행
    console.log("Home vue mounted");

    // this.onResponsiveInverted();
    // window.addEventListener("resize", this.onResponsiveInverted);

    this.getMyPos();
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    this.autoSearch(this.mylat, this.mylng);
  },
  beforeDestroy() {
    // window.removeEventListener("resize", this.onResponsiveInverted);
  },
  methods: {
    ...mapActions("map", ["getMyPos"]),
    initMap() {
      var container = document.getElementById("map");
      // console.log("state 기본 저장된 서울 시청 위치");
      // console.log(this.mylat, this.mylng);
      var options = {
        center: new kakao.maps.LatLng(this.mylat, this.mylng),
        level: 4,
      };
      var map = new kakao.maps.Map(container, options);
      this.setMap(map);
      // 마커 추가시 객체 추가
      var marker = new kakao.maps.Marker({
        position: this.kmap.getCenter(),
        title: "현위치",
      });
      marker.setMap(this.kmap);

      var here = new kakao.maps.InfoWindow({
        zIndex: 1,
        content:
          '<div id="infowindow" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">현위치</div>',
      });

      here.open(this.kmap, marker);

      var wincss = document.getElementById("infowindow");
      wincss.parentNode.style.position = "";
    },
    setMap(map) {
      this.kmap = map;
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      // https://apis.map.kakao.com/web/documentation/#load_load
      script.src =
        "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7ecec229ce36d02dc4edee1fe7e13efd&libraries=services";

      document.head.appendChild(script);
    },
    panTo(lat, lng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.kmap.panTo(moveLatLon);
    },
    submit() {
      // console.log("enter 입력");
      // console.log(document.getElementsByName("search")[0].value)
      var searchText = document.getElementsByName("search")[0].value;
      if (searchText != null && searchText != "") {
        // this.doSearch();
        api
          .findWithAddress(searchText, this.mylat, this.mylng)
          .then((result) => {
            console.log(result.data.length);
            this.maxIdx = result.data.length;
            if (this.maxIdx == 0) {
              alert("반경 3km 내에 열린 약국이 없습니다");
            } else {
              this.pharmacies = result.data;
            }
          })
          .catch((err) => {
            console.log(err);
            alert("검색 결과가 없습니다");
          });
      }
    },
    autoSearch(lat, lng) {
      console.log("autoSearch");
      // axios로 현위치를 전송하고
      api
        .findByPosition(lat, lng)
        .then((result) => {
          console.log(result.data);
          this.maxIdx = result.data.length;
          if (this.maxIdx == 0) {
            alert("반경 3km 내에 열린 약국이 없습니다");
          } else {
            this.pharmacies = result.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
      // 돌려받은 약국 리스트를 보여주는 방식 => 3개씩 슬라이드 방식 완성 ㅇㅇㅇ
    },
    plusIdx() {
      if (this.idx <= this.maxIdx - 4) {
        // 172보다 4 작으면 68 69 70 | 69 70 71 | 70 71 | 71
        this.idx += 3;
      }
      //   console.log(this.idx);
      // idx에 따라서 화면에 마커 뿌려주기
      this.marking();
    },
    minusIdx() {
      if (this.idx >= 3) {
        this.idx -= 3;
      }
      //   console.log(this.idx);
      // idx에 따라서 화면에 마커 뿌려주기
      this.marking();
    },
    telephone(idx) {
      console.log(this.pharmacies[idx].p_tel);
      var str = String(this.pharmacies[idx].p_tel);
      this.href = "`tel:${str}`";
      console.log("tel:" + str);
    },
    marking() {
      // 기존 마커를 지우고
      // 현위치, idx, idx+1, idx+2 마커를 새로 만든다
    },
    /* 햄버거 버튼 */
    ...mapMutations("navbar", ["setOpen"]),
    onClickOpener() {
      this.setOpen(!this.isOpen);
    },
  },
};
</script>
<style scoped>
.menus {
  position: fixed;
  z-index: 3;
  top: 2%;
  left: 5%;
  width: 90%;
  height: 50px;
  text-align: center;
  display: inline-block;
}

.menu-btn {
  display: inline-block;
  /* float: left; */
  /*padding: 15px 30px;*/
  border-radius: 10px;
  background-color: white;
  color: black;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.search-bar {
  display: inline-block;
  /* float: left; */
  width: calc(100% - 108px);
  /* 버튼 3개 * 각 36px */
  height: 36px;
  border: 3px solid #200400;
  border-radius: 10px;
  background: white;
  padding-left: 15px;
  padding-right: 15px;
}

::placeholder {
  font-size: x-small;
}

.search-btn {
  display: inline-block;
  /* float: left; */
  border-radius: 10px;
  color: #ffffff;
  background-color: white;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.pos-btn {
  display: inline-block;
  /* float: left; */
  border-radius: 10px;
  color: #ffffff;
  background-color: white;
  box-shadow: 0 -3px 7px rgba(0, 0, 0.3, 0.1);
}

.notice-bar {
  position: fixed;
  z-index: 3;
  top: 8%;
  display: inline-block;
  height: 50px;
  background: white;
}

.content-width {
  left: 14%;
  width: 72%;
}

.left-align {
  float: left;
  padding-left: 10px;
  padding-top: 10px;
}

.right-align {
  float: right;
}

.yg {
  position: fixed;
  z-index: 3;
  display: block;
  border-radius: 10px;
  height: 42px;
  background: white;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.yg-list {
  position: fixed;
  z-index: 3;
  display: inline-block;
  border-radius: 10px;
  height: 42px;
  background: white;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.yg1 {
  bottom: 17%;
}

.yg2 {
  bottom: 10%;
}

.yg3 {
  bottom: 3%;
}

.left-btn {
  position: fixed;
  z-index: 3;
  bottom: 10%;
  left: 3%;
  border-radius: 10px;
  background-color: white;
  color: #ffffff;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.right-btn {
  position: fixed;
  z-index: 3;
  bottom: 10%;
  right: 3%;
  border-radius: 10px;
  background-color: white;
  color: #ffffff;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}
</style>
