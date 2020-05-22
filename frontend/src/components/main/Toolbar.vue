<template>
    <v-app-bar id="app-toolbar" app flat color="red accent-2">
        <h1 class="fill">
            <v-btn v-if="responsive" dark icon @click.stop="onClickDrawer">
                <v-icon>mdi-view-list</v-icon>
            </v-btn>
            <div style="
      text-align:center;
      position:absolute;
      top:14px;
      left:43%;
      font-size:20px;
      color:white;
      ">약도</div>
        </h1>
        <v-spacer />
    </v-app-bar>
</template>

<script>
    import { mapMutations, mapState } from "vuex";

    export default {
        data: () => ({
            responsive: false,
        }),
        computed: {
            ...mapState("app", ["drawer"])
        },
        mounted() {
            this.onResponsiveInverted();
            window.addEventListener("resize", this.onResponsiveInverted);
        },
        beforeDestroy() {
            window.removeEventListener("resize", this.onResponsiveInverted);
        },

        methods: {
            ...mapMutations("app", ["setDrawer"]),
            onClickDrawer() {
                this.setDrawer(!this.drawer);
            },
            onResponsiveInverted() {
                if (window.innerWidth < 900) {
                    this.responsive = true;
                } else {
                    this.responsive = false;
                }
            },
        }
    };
</script>

<style>
    .fill{
        width: 100%;
    }
</style>
