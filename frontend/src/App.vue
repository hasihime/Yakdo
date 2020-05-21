<template>
  <v-app id="app">
    <!-- <toolbar />
    <drawer /> -->
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <route-view/>
  </v-app>
</template>

<script>
// import Drawer from "@/components/Drawer";
// import Toolbar from "@/components/Toolbar";
// import router from "./router"
import RouteView from "./components/RouteView"
// import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  components: {
    // Drawer,
    // Toolbar,
    // HelloWorld,
    RouteView,
  },
  computed: {

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
