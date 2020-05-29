<template>
  <v-app id="app">
    <route-view />
  </v-app>
</template>

<script>
import RouteView from "./components/RouteView";

export default {
  name: "app",
  components: {
    RouteView,
  },
  computed: {},
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
#app {
  /* font-family: 'Jua', sans-serif !important; */
  font-family: "Jua", Avenir, Helvetica, Arial, sans-serif !important;
  // font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background: rgb(239, 247, 238);
}
</style>
