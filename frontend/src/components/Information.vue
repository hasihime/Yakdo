<template>
  <v-card style="padding:20px">
    <!-- <v-list>
          <v-list-item>
              <v-list-item-title>
                  영업시간
              </v-list-item-title>
              <v-list-item-subtitle>
                  00:00 ~ 00:00
              </v-list-item-subtitle>
          </v-list-item>
          <v-list-item>
              <v-list-item-title>
                  전화번호
              </v-list-item-title>
              <v-list-item-subtitle style="color:black;">
                  📞 02-2323-232 <v-btn small color="primary">전화걸기</v-btn>
              </v-list-item-subtitle>
          </v-list-item>
      </v-list> -->
    <div id="list">
      <div>
        <h3>영업시간</h3>
        🕛 {{ p_oper }}
      </div>
      <div v-if="p_tel.length">
        <h3>전화번호</h3>
        📞
        <a :href="`tel: + ${p_tel}`"
          ><v-btn small color="primary">{{ p_tel }}</v-btn></a
        >
      </div>
      <div v-if="p_special.length">
        <h3>특이사항</h3>
        {{ p_special }}
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  props: {
    p_tel: {
      type: String,
      default: () => "",
    },
    p_oper: {
      type: String,
      default: () => "",
    },
    p_special: {
      type: String,
      default: () => "",
    },
  },
};
</script>

<style>
#list div {
  margin: 30px;
}

@media screen and (min-width: 601px) {
  #list {
    font-size: 30px;
  }
}

@media screen and (max-width: 600px) {
  #list {
    font-size: 20px;
  }
}
</style>
