<template>
  <div>
    <!-- 카카오맵 -->
    <div id="map" style="height:100vh;"></div>
    <!-- 상단바는 맵 위로 올라가야한다 -->

    <div class="menus">
      <v-btn class="menu-btn" icon @click="onClickOpener" title="메뉴">
        <v-icon>mdi-view-list</v-icon>
      </v-btn>

      <input
        id="search-bar-text"
        type="text"
        class="search-bar"
        name="search"
        v-on:keyup.enter="submit"
        placeholder="주소 검색을 지원합니다"
      />

      <v-btn class="search-btn" icon @click.stop="submit()" title="검색">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <!-- https://apis.map.kakao.com/web/sample/moveMap/ -->
      <v-btn
        class="pos-btn"
        icon
        @click.stop="panTo(mylat, mylng)"
        title="현위치"
      >
        <v-icon>mdi-crosshairs-gps</v-icon>
      </v-btn>
    </div>

    <v-btn class="left-btn" icon @click.stop="minusIdx()" title="이전">
      <v-icon>mdi-chevron-left</v-icon>
    </v-btn>

    <!-- 약국 리스트 시작 -->

    <div v-if="pharmacies[idx] != null" class="yg-list yg1 content-width">
      <div style="float:left;padding-top:5px; padding-left:10px;">
        <div>{{ pharmacies[idx].p_name }}</div>

        <div v-if="pharmacies[idx].distance > 1000" style="font-size : small;">
          {{ (pharmacies[idx].distance / 1000.0).toFixed(1) }} km |
          {{ pharmacies[idx].p_oper }}
        </div>
        <div v-else style="font-size : small;">
          {{ pharmacies[idx].distance }} m | {{ pharmacies[idx].p_oper }}
        </div>
      </div>

      <div class="yg-list-btn">
        <!-- <v-btn        title="전화"        class="call-btn right-align"        icon        @click="telephone(idx)"      >
        <v-icon>mdi-phone</v-icon>
      </v-btn> -->
        <v-btn
          title="표시된 자리로 이동"
          class="road-btn right-align"
          icon
          x-large
          @click.stop="panTo(pharmacies[idx].p_x, pharmacies[idx].p_y)"
        >
          <v-icon>mdi-map-search</v-icon>
        </v-btn>
        <v-btn
          title="상세페이지 열기"
          class="router-btn right-align"
          icon
          x-large
          :to="'/pharmacy/' + pharmacies[idx].p_id"
        >
          <v-icon>mdi-dock-window</v-icon>
        </v-btn>
      </div>
    </div>

    <!--  -->

    <div v-if="pharmacies[idx + 1] != null" class="yg-list yg2 content-width">
      <div style="float:left;padding-top:5px; padding-left:10px;">
        <div>{{ pharmacies[idx + 1].p_name }}</div>

        <div
          v-if="pharmacies[idx + 1].distance > 1000"
          style="font-size : small;"
        >
          {{ (pharmacies[idx + 1].distance / 1000.0).toFixed(1) }} km |
          {{ pharmacies[idx + 1].p_oper }}
        </div>
        <div v-else style="font-size : small;">
          {{ pharmacies[idx + 1].distance }} m |
          {{ pharmacies[idx + 1].p_oper }}
        </div>
      </div>

      <div class="yg-list-btn">
        <!-- <v-btn        title="전화"        class="call-btn right-align"        icon        @click="telephone(idx)"      >
        <v-icon>mdi-phone</v-icon>
      </v-btn> -->
        <v-btn
          title="표시된 자리로 이동"
          class="road-btn right-align"
          icon
          x-large
          @click.stop="panTo(pharmacies[idx + 1].p_x, pharmacies[idx + 1].p_y)"
        >
          <v-icon>mdi-map-search</v-icon>
        </v-btn>
        <v-btn
          title="상세페이지 열기"
          class="router-btn right-align"
          icon
          x-large
          :to="'/pharmacy/' + pharmacies[idx + 1].p_id"
        >
          <v-icon>mdi-dock-window</v-icon>
        </v-btn>
      </div>
    </div>

    <!--  -->

    <div v-if="pharmacies[idx + 2] != null" class="yg-list yg3 content-width">
      <div style="float:left;padding-top:5px; padding-left:10px;">
        <div>{{ pharmacies[idx + 2].p_name }}</div>

        <div
          v-if="pharmacies[idx + 2].distance > 1000"
          style="font-size : small;"
        >
          {{ (pharmacies[idx + 2].distance / 1000.0).toFixed(1) }} km |
          {{ pharmacies[idx + 2].p_oper }}
        </div>
        <div v-else style="font-size : small;">
          {{ pharmacies[idx + 2].distance }} m |
          {{ pharmacies[idx + 2].p_oper }}
        </div>
      </div>

      <div class="yg-list-btn">
        <!-- <v-btn        title="전화"        class="call-btn right-align"        icon        @click="telephone(idx)"      >
        <v-icon>mdi-phone</v-icon>
      </v-btn> -->
        <v-btn
          title="표시된 자리로 이동"
          class="road-btn right-align"
          icon
          x-large
          @click.stop="panTo(pharmacies[idx + 2].p_x, pharmacies[idx + 2].p_y)"
        >
          <v-icon>mdi-map-search</v-icon>
        </v-btn>
        <v-btn
          title="상세페이지 열기"
          class="router-btn right-align"
          icon
          x-large
          :to="'/pharmacy/' + pharmacies[idx + 2].p_id"
        >
          <v-icon>mdi-dock-window</v-icon>
        </v-btn>
      </div>
    </div>

    <!-- 약국 리스트 종료 -->

    <v-btn class="right-btn" icon @click.stop="plusIdx()" title="이후">
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
      maploading: true,
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
    // console.log("Home vue created");
  },
  beforeMount() {
    // console.log("Home vue before mounted");
  },
  mounted() {
    // child 인 Home 의 mounted가 끝나고 => 상위 mounted 실행
    // console.log("Home vue mounted");
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  beforeUpdate() {
    if (this.loading) {
      setTimeout(function() {
        // location.reload();
      }, 1000);
      this.loading = false;
    }
  },
  methods: {
    ...mapActions("map", ["getMyPos"]),
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(this.mylat, this.mylng),
        level: 4,
      };
      var map = new kakao.maps.Map(container, options);
      this.mapSetter(map);
      this.autoSearch();
    },
    mapSetter(map) {
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
      // var searchText = document.getElementsByName("search")[0].value;
      var searchText = document.getElementById("search-bar-text").value;
      if (searchText != null && searchText != "") {
        // console.log(this);
        var geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(searchText, (results, status) => {
          // console.log(results);
          // console.log(status);
          if (status === kakao.maps.services.Status.OK) {
            api
              .findWithGeoCoder(results[0].y, results[0].x)
              .then((result) => {
                // console.log(result);
                // console.log(result.data);
                // console.log(result.data.length);
                // console.log(this);
                // 화살표 함수가 아니라 functions을 쓰면 this가 Vue 객체를 지칭하지 못하고 undefined가 된다
                this.maxIdx = result.data.length;
                if (this.maxIdx == 0) {
                  alert("검색한 주소 반경 5km 내에 열린 약국이 없습니다");
                  this.alterSearch();
                } else {
                  this.pharmacies = result.data;
                  this.marking();
                }
              })
              .catch((err) => {
                console.log(err);
                alert("에러가 발생했습니다. 관리자에게 문의바랍니다.");
              });
          }
        });
      } else {
        this.autoSearch();
      }
    },
    alterSearch() {
      console.log("alterSearch");
      document.getElementById("search-bar-text").value = "서울";
      api
        .findWithAddress("서울", this.mylat, this.mylng)
        .then((result) => {
          // console.log(result.data.length);
          this.maxIdx = result.data.length;
          if (this.maxIdx == 0) {
            alert("서울 내에 열린 약국이 없습니다");
            this.pharmacies = [];
          } else {
            this.pharmacies = result.data;
            this.marking();
          }
        })
        .catch((err) => {
          console.log(err);
          alert("에러가 발생했습니다. 관리자에게 문의바랍니다.");
        });
    },
    autoSearch() {
      this.getMyPos();
      var lat = this.mylat;
      var lng = this.mylng;
      //   console.log("autoSearch");
      // axios로 현위치를 전송하고
      api
        .findByPosition(lat, lng)
        .then((result) => {
          // console.log(result.data);
          this.maxIdx = result.data.length;
          if (this.maxIdx == 0) {
            alert("반경 5km 내에 열린 약국이 없습니다");
            this.alterSearch();
          } else {
            this.pharmacies = result.data;
            this.marking();
          }
        })
        .catch((err) => {
          console.log(err);
          alert("에러가 발생했습니다. 관리자에게 문의바랍니다.");
        });
      // 돌려받은 약국 리스트를 보여주는 방식 => 3개씩 슬라이드 방식 완성 ㅇㅇㅇ
    },
    plusIdx() {
      if (this.idx <= this.maxIdx - 4) {
        // 172보다 4 작으면 68 69 70 | 69 70 71 | 70 71 | 71
        this.idx += 3;
        //   console.log(this.idx);
        // idx에 따라서 화면에 마커 뿌려주기
        this.marking();
      }
    },
    minusIdx() {
      if (this.idx >= 3) {
        this.idx -= 3;
        //   console.log(this.idx);
        // idx에 따라서 화면에 마커 뿌려주기
        this.marking();
      }
    },
    telephone(idx) {
      //   console.log(this.pharmacies[idx].p_tel);
      var str = String(this.pharmacies[idx].p_tel);
      this.href = "`tel:${str}`";
      console.log("클릭한 전화번호 : " + str);
    },
    async marking() {
      this.mapSetter(null);
      //   this.addScript();
      // 현위치, idx, idx+1, idx+2 마커를 새로 만든다

      var container = document.getElementById("map");
      container.innerHTML = "";

      // console.log("state에 저장된 위치");
      // console.log(this.mylat, this.mylng);

      var options = {
        center: new kakao.maps.LatLng(this.mylat, this.mylng),
        level: 4,
      };
      var map = new kakao.maps.Map(container, options);
      this.mapSetter(map);

      // 마커 추가시 객체 추가
      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.mylat, this.mylng),
        title: "현위치",
      });
      marker.setMap(this.kmap);

      var here = new kakao.maps.CustomOverlay({
        position: new kakao.maps.LatLng(this.mylat, this.mylng),
        zIndex: 10,
        yAnchor: 2.5,
        content:
          '<div id="CustomOverlay"' +
          'style="background-color:white;padding:5px;font-size:12px;text-align:center;border:1px solid black; border-radius:1px;">' +
          "현위치" +
          "</div>",
      });
      here.setMap(this.kmap);
      //   here.open(this.kmap, marker); // 이 지도의 marker 위치에 info Window 현위치를 표시
      //   var wincss = document.getElementById("CustomOverlay");
      //   wincss.parentNode.style.position = "";

      // 리스트 3개 마커

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = this.idx; i < this.idx + 3 && i < this.maxIdx; i++) {
        var y = this.pharmacies[i].p_x;
        var x = this.pharmacies[i].p_y;

        if (y === null || y === undefined || x === null || x === undefined)
          continue;

        var name = this.pharmacies[i].p_name;
        // console.log(name);
        var num = this.pharmacies[i].p_id;

        // 마커를 생성하고 지도에 표시합니다
        var addMarker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(y, x),
          title: name,
        });
        addMarker.setMap(this.kmap);

        bounds.extend(new kakao.maps.LatLng(y, x));

        // 장소명을 표출할 인포윈도우 입니다
        var infowindow = new kakao.maps.CustomOverlay({
          position: new kakao.maps.LatLng(y, x),
          zIndex: 10,
          yAnchor: 2.5,
          content:
            '<div id="CustomOverlay' +
            num +
            '" style="background-color:white;padding:5px;font-size:12px;text-align:center;border:1px solid black; border-radius:1px;">' +
            name +
            "</div>",
        });
        infowindow.setMap(this.kmap);
        // infowindow.open(this.kmap, addMarker); // 이 지도의 marker 위치에 info Window 현위치를 표시
        //   var wincss = document.getElementById("infowindow"+num);
        //   wincss.parentNode.style.position = "";
      }

      this.kmap.setBounds(bounds); // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    },
    ...mapMutations("navbar", ["setOpen"]),
    onClickOpener() {
      /* 햄버거 버튼 */
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
  left: 12%;
  width: 76%;
}

.left-align {
  float: left;
  padding-top: 3px;
  padding-left: 3px;
}

.right-align {
  float: right;
  width: 50px;
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
  height: 50px;
  background: white;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.yg1 {
  bottom: 19%;
}

.yg2 {
  bottom: 11%;
}

.yg3 {
  bottom: 3%;
}

.left-btn {
  position: fixed;
  z-index: 3;
  bottom: 12%;
  left: 1.5%;
  border-radius: 10px;
  background-color: white;
  color: #ffffff;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.right-btn {
  position: fixed;
  z-index: 3;
  bottom: 12%;
  right: 1.5%;
  border-radius: 10px;
  background-color: white;
  color: #ffffff;
  box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
}

.router-btn {
  width: 36px;
  height: 36px;
}

.road-btn {
  width: 36px;
  height: 36px;
}

.yg-list-btn {
  padding-top: 5px;
  float: right;
  width: 72px;
}
</style>
