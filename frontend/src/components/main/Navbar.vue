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
              color:white;
              background-color: darkseagreen;
              "
      >
        약도
        <div style="font-size:17px;">
          슬기로운 약국 생활
        </div>
      </div>
    </h1>

    <div style="padding:22px; color:white; font-size:13px;"></div>

    <div style="position:fixed; left:12%; bottom : 5%; color: white;">
      <hr />
      <div style="padding-top:10px; padding-bottom:15px;">
        약국 데이터 :
        <a href="https://data.go.kr/" style="text-decoration:none;"
          >공공데이터 포털</a
        >
      </div>
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
