<template>
  <div id="app">
    <toolbar />
    <drawer />
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
    <route-view/>
  </div>
</template>

<script>
import Drawer from "@/components/Drawer";
import Toolbar from "@/components/Toolbar";
import router from "./router"
import RouteView from "./components/RouteView"
// import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  components: {
    Drawer,
    Toolbar,
    // HelloWorld,
    RouteView,
  },
  mounted() {
    router.push("/")
  },
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
