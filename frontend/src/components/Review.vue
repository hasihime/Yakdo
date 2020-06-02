<template>
  <v-card
  outlined
  style="margin-top:10px;margin-bottom:10px" >

  <v-dialog
      v-model="modifyDialog"
      width="500"
    >
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon id="modify" color="#D3D3D3">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </template>

      <v-card>
        <div style="margin:20px;">
          <h2 style="text-align:left;font-size:25px;">
            {{r_writer}} 댓글 수정
          </h2>
          <v-textarea
            outlined
            auto-grow
            v-model="modifiedContent"
          />
          <!-- 로그인 구현 후 로그인여부 함수 작성-->
          <div v-if="true">
            <h3 style="text-align:left;">비밀번호</h3>
            <v-text-field
              outlined
              type="password"
              v-model="newPw"
            />
          </div>
        </div>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="red--text text--darken-1"
            text
            @click="modifyClose"
            style="font-size:20px"
            id="dialog_text"
          >
            취소
          </v-btn>
          <v-btn
            color="primary"
            text
            @click="modifyReview"
            style="font-size:20px"
            id="dialog_text"
          >
            수정
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>


  


  <v-dialog
      v-model="deleteDialog"
      width="500"
    >
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon id="delete" color="#D3D3D3">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </template>

      <v-card>
        <div style="margin:20px;">
          <h2 style="text-align:left;font-size:25px;">
            {{r_writer}} 댓글 삭제
          </h2>
          <div v-if="true">
            <h3 style="text-align:left;">비밀번호</h3>
            <v-text-field
              type="password"
              outlined
              v-model="newPw"
            />
          </div>
        </div>
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            class="red--text text--darken-1"
            text
            @click="deleteClose"
            style="font-size:20px"
            id="dialog_text"
          >
            취소
          </v-btn>
          <v-btn
            color="primary"
            text
            @click="deleteReview"
            style="font-size:20px"
            id="dialog_text"
          >
            삭제
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>




    <v-card-subtitle style="text-align:left;" id="review">
        {{r_writer}}
    </v-card-subtitle>
    <v-card-text style="text-align:left;" id="review">
        {{r_content}}
    </v-card-text>
  </v-card>
</template>

<script>
import api from "../api"
import {  mapActions , mapState } from "vuex";

export default {
  data() {
    return {
      modifyDialog: false,
      deleteDialog: false,
      newPw:"",
      modifiedContent:"",
    }
  },
  props: {
    r_id : {
        type: Number,
        default: () => "",
    },
    r_writer: {
        type: String,
        default: () => "",
    },
    r_content: {
        type: String,
        default: () => "",
    },
  },
  computed: {
    ...mapState({
      reviews : state => state.data.reviews,
    }),
  },
  mounted() {
    this.modifiedContent = this.r_content
  },
  methods: {
    ...mapActions("data", ["getReviews"]),

    modifyReview() {
      if(this.newPw == "" || this.newPw == null) {
        alert("비밀번호를 입력해주세요.")
        return false
      }
      api.putReview({
        r_id: this.r_id,
        r_pw: this.newPw,
        r_content: this.modifiedContent
      })
        .then(response => {
          // 수정되면
          if(response.data.state=="SUCCESS") {
            alert("수정이 완료되었습니다.")
          } else {
            alert("비밀번호가 다릅니다.")
            this.modifiedContent = this.r_content
          }
        })
        .catch(() => {
          alert("수정하는데 오류가 발생했습니다.")
          this.modifiedContent = this.r_content
        })

      this.modifyDialog = false
      this.getReviews(this.$route.params.id)
    },
    deleteReview() {
      if(this.newPw == "" || this.newPw == null) {
        alert("비밀번호를 입력해주세요.")
        return false
      }

      api.deleteReview({
        r_id:this.r_id,
        r_pw:this.newPw
      }) 
        .then(response => {
          // 수정되면
          if(response.data.state=="SUCCESS") {
            alert("삭제가 완료되었습니다.")
          } else {
            alert("비밀번호가 다릅니다.")
          }
        })
        .catch(error => {
          console.log(error)
          alert("삭제하는데 오류가 발생했습니다.")
        })

      this.deleteDialog = false
      this.getReviews(this.$route.params.id)
    },
    modifyClose() {
      this.modifiedContent = this.r_content
      this.newPw = ""
      this.modifyDialog = false
    },
    deleteClose() {
      this.newPw = ""
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