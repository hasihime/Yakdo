<template>
  <v-app-bar id="app-toolbar" app flat class="primary">
    <v-btn dark icon @click.stop="onClickOpener">
        <v-icon>mdi-view-list</v-icon>
    </v-btn>
    <h1 class="fill">
      <!-- <v-app-bar-nav-icon>
          <v-icon>mdi-view-list</v-icon>
      </v-app-bar-nav-icon> -->
    <div style="
      text-align:center;
      position:absolute;
      top:14px;
      left:48%;
      font-size:20px;
      color:white;
      ">약도</div>
    </h1>
    <!-- <v-btn dark icon v-on:click="user_url()">
      <v-icon>mdi-account-circle</v-icon>
    </v-btn> -->
    <v-spacer />
    <navbar></navbar>
  </v-app-bar>
</template>

<script>
import Navbar from "@/components/main/Navbar";
import { mapMutations, mapState } from "vuex";

export default {
  data: () => ({
  }),
  components: {
    Navbar
  },
  computed: {
    ...mapState("navbar", ["isOpen"]),
  },
  mounted() {
    // this.onResponsiveInverted();
    // window.addEventListener("resize", this.onResponsiveInverted);
  },
  beforeDestroy() {
    // window.removeEventListener("resize", this.onResponsiveInverted);
  },

  methods: {
    ...mapMutations("navbar", ["setOpen"]),
    onClickOpener() {
        this.setOpen(!this.isOpen);
    },
    // onResponsiveInverted() {
    //   if (window.innerWidth < 900) {
    //     this.responsive = true;
    //   } else {
    //     this.responsive = false;
    //   }
    // },
    user_url(){
      this.$router.push(sessionStorage.getItem('user') ? '나만의약국 이동' : '/login')
    }
  }
};
</script>

<style>
  .fill{
    width: 100%;
  }
</style>
