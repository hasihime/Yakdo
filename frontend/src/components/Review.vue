<template>
  <v-card
  outlined
  style="margin-top:10px;margin-bottom:10px" >
    <v-btn icon id="modify" @click="showModify">
      <v-icon>mdi-pencil</v-icon>
    </v-btn>
  <v-btn icon id="delete" @click="showDelete">
    <v-icon>mdi-delete</v-icon>
  </v-btn>
    <v-card-subtitle style="text-align:left;" id="review">
        IP
    </v-card-subtitle>
    <v-card-text style="text-align:left;" id="review">
        쥐약도 파나요? 우리 쥐가 오늘 아픈데 약을 먹여야할것같아요<div class="">
            아 그리고 약사선생님 너무 친절하세요
        </div>
    </v-card-text>
    <v-text-field style="margin:10px;" outlined>비밀번호</v-text-field>
    <!-- <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">OVERLINE</div>
        <v-list-item-title class="headline mb-1">Headline 5</v-list-item-title>
        <v-list-item-subtitle>Greyhound divisely hello coldly fonwderfully</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item> -->
    <v-dialog
      v-model="modifyDialog"
      width="500"
    >
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" style="position:fixed;right:20px;bottom:20px;" class="mx-2 primary" fab dark>
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </template>

      <v-card>
        <!-- <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
        댓글
        </v-card-title> -->

        <!-- <v-card-text>
          ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
        </v-card-text> -->
        <div style="margin:20px;">
          <h2 style="text-align:left;font-size:25px;">
            댓글
          </h2>
          <v-textarea
            outlined
            auto-grow
            v-model="r_content"
          />
            <!-- clearable
            clear-icon="a" -->
          <!-- 로그인 구현 후 로그인여부 함수 작성-->
          <div v-if="true">
            <h3 style="text-align:left;">비밀번호</h3>
            <v-text-field
              outlined
              v-model="r_pw"
            />
          </div>
        </div>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="red--text text--darken-1"
            text
            @click="dialog = false"
            style="font-size:20px"
            id="dialog_text"
          >
            취소
          </v-btn>
          <v-btn
            color="primary"
            text
            @click="createReview"
            style="font-size:20px"
            id="dialog_text"
          >
            등록
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      modifyDialog: false,
      deleteDialog: false,
    }
  },
  methods: {
    showModify() {

      this.modifyDialog = false
    },
    showDelete() {

      this.deleteDialog = false
    }
  },
}
</script>

<style>
#modify {
  position:absolute;
  top:10px;
  right:40px;
}
#delete {
  position:absolute;
  top:10px;
  right:10px;
}

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