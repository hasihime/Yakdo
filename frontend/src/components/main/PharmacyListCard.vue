<template>
    <v-card @click="showDetailPage">
        <v-card-text class="text-center">
            <p class="display-1 font-weight-bold">{{ name }} </p>
        </v-card-text>
        <v-footer color="white">
            <v-col class="text-center">
                <span class="grey--text caption font-italic"
                >{{ address }} / 📞 {{ tel }}</span
                >
            </v-col>
        </v-footer>
    </v-card>
</template>

<script>
    // import { mapState, mapActions } from "vuex";
    // import axios from "axios";
    export default {
        data: function() {
            return {
                count: 0,
                score: 0,
            };
        },
        props: {
            id: {
                type: Number,
                default: 0,
            },
            name: {
                type: String,
                default: "",
            },
            address: {
                type: String,
                default: "",
            },
            tel: {
                type: String,
                default: "",
            },
        },
        computed: {
            // tags: function() {
            //   return this.categories.reduce((acc, v) => {
            //     return `${acc} #${v}`;
            //   }, "");
            // }
        },
        mounted() {
            // axios.get(`/api/reviews/getStatistics?store_id=${this.id}`)
            //     .then(response => {
            //         this.count = response.data.total
            //         this.score = response.data.avg_dot5
            //     })
            //     .catch(() => {});
            // // console.log("axios완료");
        },
        beforeDestroy() {},
        methods: {
            showDetailPage() {
                this.$router.push(`/pharmacy/${this.id}`);
            },
        },
    };
</script>
