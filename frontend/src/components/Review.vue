<template>
  <v-card
  outlined
  style="margin-top:10px;margin-bottom:10px" >
    <v-card-subtitle style="text-align:left;" id="review">
        IP
    </v-card-subtitle>
    <v-card-text style="text-align:left;" id="review">
        쥐약도 파나요? 우리 쥐가 오늘 아픈데 약을 먹여야할것같아요<div class="">
            아 그리고 약사선생님 너무 친절하세요
        </div>
    </v-card-text>
    <!-- <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">OVERLINE</div>
        <v-list-item-title class="headline mb-1">Headline 5</v-list-item-title>
        <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item> -->
  </v-card>
</template>

<script>
export default {

}
</script>

<style>
@media screen and (min-width: 601px) {
    #review {
      font-size: 26px;
    }
  }

  @media screen and (max-width: 600px) {
    #review {
      font-size: 20px;
    }
  }
</style>