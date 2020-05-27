<template>
    <v-app id="app">
        <route-view/>
    </v-app>
</template>

<script>

    import RouteView from "./components/RouteView"

    export default {
        name: 'app',
        components: {
            RouteView,
        },
        computed: {}
    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>
