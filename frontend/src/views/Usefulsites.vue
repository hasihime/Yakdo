<template>
  <div>
    <h1 style="padding-top:25px;">참고 사이트</h1>
    <h2 style="padding:15px;"></h2>
    <div style="text-align:left;">
      <v-card class="mx-auto" max-width="434">
        <v-container>
          <v-row dense>
            <v-col v-for="(site, i) in sites" :key="i" cols="12">
              <v-card :color="site.color">
                <div class="d-flex flex-no-wrap justify-space-between">
                  <v-avatar class="ma-3" size="64" tile>
                    <v-img :src="site.image"></v-img>
                  </v-avatar>

                  <div>
                    <v-card-title
                      class="headline"
                      v-text="site.name"
                    ></v-card-title>

                    <v-card-subtitle v-text="site.owner"></v-card-subtitle>

                    <v-card-text v-text="site.description"> </v-card-text>
                  </div>
                  <v-btn icon :href="site.link">
                    <v-icon>mdi-share</v-icon>
                  </v-btn>
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    sites: [
      {
        name: "휴일지킴이약국",
        color: "white",
        owner: "대한약사회",
        description:
          "지역과 시간을 선택하여 운영중인 약국 리스트를 확인할 수 있습니다.",
        image: require("../assets/static/siteslogo/대한약사회.png"),
        link: "https://www.pharm114.or.kr/main.asp",
      },
      {
        name: "약학정보원",
        color: "white",
        owner: "약학정보원",
        description: "제품명 또는 성분명으로 의약품을 검색할 수 있습니다.",
        image: require("../assets/static/siteslogo/약학정보원.png"),
        link: "http://www.health.kr/main.asp",
      },
      {
        name: "응급실찾기",
        color: "white",
        owner: "중앙응급의료센터",
        description: "지도 기반으로 응급실과 병원, 약국 등을 찾을 수 있습니다.",
        image: require("../assets/static/siteslogo/중앙응급의료센터.png"),
        link: "https://www.e-gen.or.kr/egen/search_emergency_room.do",
      },
      {
        name: "병원 약국 찾기",
        color: "white",
        owner: "건강보험심사평가원",
        description: "검색 조건이 매우 세분화되어 있는 검색사이트입니다.",
        image: require("../assets/static/siteslogo/건강보험심사평가원.png"),
        link:
          "https://www.hira.or.kr/rd/hosp/getHospList.do?pgmid=HIRAA030002000000",
      },
      {
        name: "코로나맵",
        color: "white",
        owner: "공대돌고래",
        description: "코로나바이러스의 국내 현황을 알려주는 지도 서비스입니다.",
        image: require("../assets/static/siteslogo/코로나맵.png"),
        link: "https://coronamap.site/",
      },
      // {
      //   name: "",
      //   color: "white",
      //   owner: "",
      //   description: " ~에서 제공하는 ~한 사이트입니다.",
      //   image: require("../assets/static/siteslogo/logo.png"),
      //   link: " ",
      // },
    ],
  }),
};
</script>

<style></style>
