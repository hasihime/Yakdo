<template>
  <v-navigation-drawer
    id="app-drawer"
    v-model="inputValue"
    app
    dark
    floating
    persistent
    touchless
    mobile-break-point="10000"
    width="250"
  >
    <!-- pc 화면에서 메뉴 버튼이 사라지는 버그 mobile-break-point와 연관있음 -->

    <h1 style="width:100%;">
      <div
        style="
              text-align:center;
              padding:12px;
              font-size:24px;
              color:black;
              background-color: darkseagreen;
              "
      >
        약도
        <div style="font-size:20px;">
          슬기로운 약국 생활
        </div>
      </div>
    </h1>

    <div style="padding:22px; color:white; font-size:13px;">
      약도 - 슬기로운 약국 생활은 빠르게 직관적으로 현재 열린 약국을 찾을 수
      있는 서비스를 제공하기 위해 기획되었습니다.
      <br />
      <br />
      주소기반 검색을 지원하며 시/군/구 명으로 검색할 경우 해당 지방자치단체
      시군구청을 중심으로 거리를 계산합니다.
      <br />
      <br />
      검색어가 없을 경우 현위치 기반으로 반경 5km 내의 열린 약국을 표시하게
      됩니다.
      <br />
      <br />
      또한 아래에 링크된 안드로이드 앱을 설치하여 사용하실 경우 나만의 약국
      서비스를 통하여 본인이 가진 약의 개수와 유통기한, 복약 알림 등을 설정하실
      수 있습니다.
      <br />
      <br />
    </div>

    <div style="position:fixed; left:12%; bottom : 5%; color: white;">
      <div>
        <a href="http://naver.me/GP4v7sp9" style="text-decoration:none;"
          >나만의 약국 apk 다운로드</a
        >
      </div>
      <hr />
      <div style="padding-top:10px; padding-bottom:10px;">
        약국 데이터 :
        <a href="https://data.go.kr/" style="text-decoration:none;"
          >공공데이터 포털</a
        >
      </div>
      <div style="padding-top:10px; padding-bottom:20px;">
        기타 유용한 사이트
        <v-btn
          style="background-color:gray;"
          icon
          to="/usefulsites"
          title="usefulsites"
        >
          <v-icon>mdi-information-outline</v-icon></v-btn
        >
      </div>
      <!-- <hr /> -->
      <div style="border-radius: 10px; color: #ffffff;">
        <v-btn
          style="margin-right:15px;background-color:gray;"
          icon
          href="mailto:refresh6724.dev@gmail.com"
          title="contact us"
        >
          <v-icon>mdi-email</v-icon></v-btn
        >
        <v-btn
          style="margin-right:15px;background-color:gray;"
          icon
          to="/aboutus"
          title="about us"
        >
          <v-icon>mdi-account-group</v-icon></v-btn
        >
        <v-btn
          style="margin-right:0px;background-color:gray;"
          icon
          href="https://lab.ssafy.com/s02-final/s02p31a405"
          title="gitlab"
        >
          <v-icon>mdi-gitlab</v-icon></v-btn
        >
      </div>
    </div>
  </v-navigation-drawer>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  props: {
    opened: {
      type: Boolean,
      default: false,
    },
  },
  data: () => ({
    links: [
      {
        to: "/",
        icon: "mdi-home",
        text: "Home",
      },
    ],
  }),
  computed: {
    /* store/modules 의 navbar.js 파일 내부의 isOpen state를 사용하겠다 선언 */
    ...mapState("navbar", ["isOpen"]),
    inputValue: {
      get() {
        /* 열려있는지 여부를 vuex에서 받아온 값으로 읽는다 */
        return this.isOpen;
      },
      set(val) {
        /* 열려있는지 여부를 vuex에서 받아온 값으로 읽는다 */
        this.setOpen(val);
      },
    },
  },
  methods: {
    /* store/modules 의 navbar.js 파일 내부의 setOpen mutation을 사용하겠다 선언 */
    ...mapMutations("navbar", ["setOpen"]),
  },
};
</script>
