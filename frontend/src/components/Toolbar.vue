<template>
  <v-app-bar id="app-toolbar" app flat class="primary">
    
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
    <v-btn dark icon @click="goHome">
        <v-icon>mdi-home</v-icon>
    </v-btn>
    <v-spacer />
  </v-app-bar>
</template>

<script>
export default {
  methods: {
    goHome(){
      this.$router.push('/')
    }
  }
};
</script>

<style>
  .fill{
    width: 100%;
  }
</style>
