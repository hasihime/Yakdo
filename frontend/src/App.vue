<template>
  <v-app id="app">
    <toolbar />
    <drawer />
    <route-view/>
  </v-app>
</template>

<script>
import Drawer from "@/components/main/Drawer";
import Toolbar from "@/components/main/Toolbar";
import RouteView from "./components/RouteView"

export default {
  name: 'app',
  components: {
    Drawer,
    Toolbar,
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
