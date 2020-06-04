<template>
    <v-card class="text-center vcard" >
        <p id="title" class="accent--text">{{this.$store.state.data.medicine.m_name}}</p>
        약이 있다고 생각하자.
    </v-card>
</template>
<script>
import {  mapActions, mapState  } from "vuex";

export default {
    data: function () {
        return {
        
        }
    },
    computed: {
    // mapState : state의 속성을 가져온다
        ...mapState({
        medicine : state => state.data.medicine,
        }),
    }, 
    methods: {
    // actions에 정의된 메서드를 가져온다
        ...mapActions("data", ["getMedicine"]),
    },
}
</script>
<style>
    .vcard{
        height:0;
        padding-bottom:calc(100% - 20px);
        border-radius : 10px;
        margin : 0 10px;
    }
</style>