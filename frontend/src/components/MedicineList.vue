<template>
    <v-layout>
    <v-container xs12>
        <v-layout row>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
            <v-flex xs4 md3 lg2>
                <Medicine/>
            </v-flex>
        <div v-if="medicines.length == 0">등록된 약이 없습니다.</div>
        <v-btn style="position:fixed;right:7vw;bottom:7vh;" class="mx-2 primary" fab dark>
          <v-icon>mdi-plus</v-icon>
        </v-btn>
        </v-layout>
    </v-container>
  </v-layout>
</template>
<script>
import Medicine from "@/components/Medicine";
import { mapState, mapActions } from "vuex";

export default {
    data: () => ({
        loading: false,
        m_name:"",
    }),
    computed: {
        ...mapState({
        medicines: state => state.data.medicines
    })
  },
  components : {
    Medicine
  },
  methods: {
    ...mapActions("data", ["getMedicines"]),
  }
}
</script>
<style>
    v-flex{
        height : 300px;
    }
</style>