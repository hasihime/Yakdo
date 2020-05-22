<template>
  <div
    v-infinite-scroll="loadMore"
    infinite-scroll-disabled="loading"
    infinite-scroll-distance="20"
  >
    <v-container fill-height fluid grid-list-xl>
      <v-layout row justify-center wrap mt-5>
        <v-flex xs12 md8>
          <div id="notice" @click="fold">
            <span style="font-weight:bold">현재 위치 관련 공지사항 ▼</span>
            <div id="contents" style="display:none;padding:5px">
              구글 크롬에서 html5 geolocation 함수의 http 지원이 종료되었습니다.
              <br />
              현재 위치를 사용하려면 크롬이 아닌 다른 브라우저를 사용해주세요
            </div>
          </div>
        </v-flex>

        <v-flex xs12 md8>
          <div id="map" style="height: 500px" />
        </v-flex>

        <v-flex xs12 md8>
          <card style="text-align:center" title="약국 목록">
            <v-form onsubmit="return false;">
              <v-container py-0>
                <v-layout wrap>
                  <v-flex xs12 md12>
                    <v-layout style="padding-left:5%;padding-right:5%;">
                      <v-text-field
                        v-model="pharmacyLoc"
                        label="약국 위치"
                        color="red accent-2"
                        hint="검색 위치 반경 2km 이내를 검색합니다!"
                        clearable
                        style="margin-top:20px;margin-bottom:20px"
                        @keyup.enter="onSubmit"
                      />
                      <!-- color="red accent-2" -->
                      <v-btn
                        icon
                        @click="onSubmit"
                        style="margin-top:33px;margin-bottom:20px"
                      >
                        <v-icon>mdi-magnify</v-icon>
                      </v-btn>
                    </v-layout>
                  </v-flex>
                  <!-- <v-flex xs12 text-center>
                    <v-btn
                      large
                      class="red accent-2 white--text ma-5"
                      rounded
                      color="red accent-2"
                      @click="onSubmit"
                    >찾기</v-btn>
                  </v-flex> -->
                </v-layout>
              </v-container>
            </v-form>
          </card>
          <v-divider class="mx-4" />
        </v-flex>

        <v-flex xs12 md8>
          <v-flex v-for="pharmacy in pharmacies" :key="pharmacy.id" pa-4>
            <pharmacy-list-card
              :id="pharmacy.id"
              :name="pharmacy.name"
              :address="pharmacy.address"
              :tel="pharmacy.tel"
            />
          </v-flex>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Card from "@/components/main/Card";
import PharmacyListCard from "@/components/main/PharmacyListCard";

import { mapState, mapActions, mapMutations } from "vuex";

export default {
  components: {
    // 하위 컴포넌트 등록
    Card,
    PharmacyListCard,
  },
  data() {
    return {
      pharmacyLoc: "",
      loading: true,
      centerlat: 0,
      centerlng: 0,
    };
  },
  computed: {
    ...mapState({
      pharmacies: (state) => state.data.PharmacySearchList,
      page: (state) => state.data.PharmacySearchPage,
      mylat: function() {
        return this.$store.state.map.mylat;
      },
      mylng: function() {
        return this.$store.state.map.mylng;
      },
      keepgoing: function() {
        return this.$store.state.data.pharmacySearchKeepGoing;
      },
    }),
  },
  created() {
    this.getMyPos();
    // console.log("this.mylat : ", this.mylat);
    // console.log("this.mylng : ", this.mylng);
  },
  mounted() {
    this.getMyPos();
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    this.centerlat = this.mylat;
    this.centerlng = this.mylng;
  },
  methods: {
    ...mapActions("map", ["getMyPos"]),
    ...mapActions("data", ["getPharmacies"]),
    ...mapMutations("data", ["setPharmacySearchKeepGoing"]),
    onSubmit: async function() {
      this.setPharmacySearchKeepGoing(true);
      const params = {
        name: this.pharmacyLoc,
        page: 1,
        append: false,
        centerlat: this.centerlat,
        centerlng: this.centerlng,
      };
      await this.getPharmacies(params);

      /* 검색 결과를 맵에 표시 */

      var container = document.getElementById("map");
      container.innerHTML = "";

      // console.log("state에 저장된 위치");
      // console.log(this.mylat, this.mylng);

      var options = {
        center: new kakao.maps.LatLng(this.mylat, this.mylng),
        level: 3,
      };
      var map = new kakao.maps.Map(container, options);
      // 마커 추가시 객체 추가
      var marker = new kakao.maps.Marker({
        title: "현위치",
        position: map.getCenter(),
      });
      marker.setMap(map);

      var here = new kakao.maps.InfoWindow({
        zIndex: 1,
        content:
          '<div id="infowindow" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">현위치</div>',
      });

      here.open(map, marker);

      var wincss = document.getElementById("infowindow");
      wincss.parentNode.style.position = "";

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < this.pharmacies.length; i++) {
        // console.log(this.pharmacies[i]);
        // console.log(this.pharmacies[i].lat, this.pharmacies[i].lng);
        var y = this.pharmacies[i].lat;
        var x = this.pharmacies[i].lng;

        if (y === null || y === undefined || x === null || x === undefined)
          continue;

        var name = this.pharmacies[i].name;
        var num = this.pharmacies[i].id;

        // 마커를 생성하고 지도에 표시합니다
        var addMarker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(y, x),
          title: name,
        });
        addMarker.setMap(map);

        // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
        var infowindow = new kakao.maps.InfoWindow({
          zIndex: 1,
          content:
            '<div id="infowindow' +
            num +
            '" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">' +
            name +
            "</div>",
        });

        kakao.maps.event.addListener(
          addMarker,
          "click",
          this.makeListener(map, addMarker, infowindow, num)
        );

        // 클로저 사용 불가 => 마지막에만 등록된다
        // 마커에 클릭이벤트를 등록합니다
        // addMarker.addListener(
        //   "click",
        //   function() {
        //     // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        //     infowindow.setContent(
        //       '<div style="padding:5px;font-size:12px;">' + name + "</div>"
        //     );
        //     infowindow.open(map, marker);
        //   }()
        // );

        bounds.extend(new kakao.maps.LatLng(y, x));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);

      this.loading = false;
    },
    loadMore: async function() {
      this.loading = true;
      const params = {
        name: this.pharmacyLoc,
        page: this.page,
        append: true,
        centerlat: this.centerlat,
        centerlng: this.centerlng,
      };
      if (this.keepgoing) {
        await this.getPharmacies(params);

        /* 검색 결과를 맵에 표시 */

        var container = document.getElementById("map");
        container.innerHTML = "";

        // console.log("state에 저장된 위치");
        // console.log(this.mylat, this.mylng);

        var options = {
          center: new kakao.maps.LatLng(this.mylat, this.mylng),
          level: 3,
        };
        var map = new kakao.maps.Map(container, options);
        // 마커 추가시 객체 추가
        var marker = new kakao.maps.Marker({
          title: "현위치",
          position: map.getCenter(),
        });
        marker.setMap(map);

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < this.pharmacies.length; i++) {
          // console.log(this.pharmacies[i]);
          // console.log(this.pharmacies[i].lat, this.pharmacies[i].lng);
          var y = this.pharmacies[i].lat;
          var x = this.pharmacies[i].lng;

          if (y === null || y === undefined || x === null || x === undefined)
            continue;

          var name = this.pharmacies[i].name;
          var num = this.pharmacies[i].id;

          // 마커를 생성하고 지도에 표시합니다
          var addMarker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(y, x),
            title: name,
          });
          addMarker.setMap(map);

          // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
          var infowindow = new kakao.maps.InfoWindow({
            zIndex: 1,
            content:
              '<div id="infowindow' +
              num +
              '" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">' +
              name +
              "</div>",
          });

          kakao.maps.event.addListener(
            addMarker,
            "click",
            this.makeListener(map, addMarker, infowindow, num)
          );

          // 클로저 사용 불가 => 마지막에만 등록된다
          // 마커에 클릭이벤트를 등록합니다
          // addMarker.addListener(
          //   "click",
          //   function() {
          //     // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
          //     infowindow.setContent(
          //       '<div style="padding:5px;font-size:12px;">' + name + "</div>"
          //     );
          //     infowindow.open(map, marker);
          //   }()
          // );

          bounds.extend(new kakao.maps.LatLng(y, x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);

        setTimeout(() => {
          this.loading = false;
        }, 1000);
      }
    },
    initMap() {
      var container = document.getElementById("map");
      // console.log("state 기본 저장된 서울 시청 위치");
      // console.log(this.mylat, this.mylng);
      var options = {
        center: new kakao.maps.LatLng(this.mylat, this.mylng),
        level: 3,
      };
      var map = new kakao.maps.Map(container, options);
      // 마커 추가시 객체 추가
      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
        title: "현위치",
      });
      marker.setMap(map);

      var here = new kakao.maps.InfoWindow({
        zIndex: 1,
        content:
          '<div id="infowindow" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">현위치</div>',
      });

      here.open(map, marker);

      var wincss = document.getElementById("infowindow");
      wincss.parentNode.style.position = "";
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      // https://apis.map.kakao.com/web/documentation/#load_load
      script.src="https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7ecec229ce36d02dc4edee1fe7e13efd&libraries=services"

      document.head.appendChild(script);
    },
    makeListener(map, addMarker, infowindow, num) {
      return function() {
        infowindow.open(map, addMarker);

        // console.log("infowindow", num);
        var wincss2 = document.getElementById("infowindow" + num);
        // console.log(wincss2);
        // console.log(wincss2.parentNode);
        wincss2.parentNode.style.position = "";
      };
    },
    fold() {
      var con = document.getElementById("contents");
      if (con.style.display == "none") {
        con.style.display = "block";
      } else {
        con.style.display = "none";
      }
    },
  },
};
</script>
<style></style>
