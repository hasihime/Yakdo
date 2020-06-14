<template>
  <v-card id="review" style="padding:20px">
    <h3 v-if="this.$store.state.data.reviews.length != 0">
      {{this.$store.state.data.reviews.length}}개의 리뷰
    </h3>
    <div v-for="review in this.$store.state.data.reviews"
        :key="review.r_id">
        <Review
          :r_id="review.r_id"
          :r_content="review.r_content"
          :r_writer="review.r_writer"
        />
    </div>
    <div v-if="this.$store.state.data.reviews.length == 0" class="store_not_review">리뷰가 없습니다.</div>
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" style="position:fixed;right:20px;bottom:20px;" class="mx-2 primary" fab dark>
          <v-icon>mdi-plus</v-icon>
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
              type="password"
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
import Review from "./Review" // 지승 : @/components가 안먹힘 왠지모르겠다.
import api from "../api"
import axios from "axios"
import {  mapActions , mapState } from "vuex";

export default {
    data() {
        return {
            dialog: false,
            r_writer: "",   // 작성자(로그인:구글ID, 비로그인:IP)
            r_pw: "",       // 비밀번호(비로그인)
            r_content: "",  // 댓글 내용
            p_id: ""        // 약국 ID
        }
    },
    components: {
        Review,
    },
    computed: {
      ...mapState({
        reviews : state => state.data.reviews,
      }),
    },
    methods: {
      ...mapActions("data", ["getReviews"]),
      async createReview() {
        if(this.r_content == "" || this.r_content == null) {
          alert("내용을 입력해주세요.")
          return false
        } else if(this.r_pw == "" || this.r_pw == null) {
          alert("비밀번호를 입력해주세요.")
          return false
        }

        await api.postReview({
          r_writer: this.r_writer,
          r_pw: this.r_pw,
          r_content: this.r_content,
          p_id: this.p_id,
        })

        // 알림창 닫기
        this.dialog = false
        // 등록 후 비우기
        this.r_content = ""
        this.r_pw = ""
        alert("리뷰가 등록되었습니다.")

        this.getReviews(this.$route.params.id)
      },
      async getIP() {
        var ip
        await axios.get("https://extreme-ip-lookup.com/json")
          .then(response => {
            ip = response.data.query;
          })
          .catch(() => {
            console.log("실패")
          })
        console.log("ip:"+ip)
        this.r_writer = ip.split('.')[0] + '.' + ip.split('.')[1] + ".*.*"
      }
    },
    mounted() {
      this.getReviews(this.$route.params.id)
      this.p_id = this.$route.params.id
      // 로그인일 경우, this.r_writer는 구글 아이디
      // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

      // 비로그인일 경우
      this.getIP()
    },
}
</script>

<style>
 @media screen and (min-width: 601px) {
    #review {
      font-size: 30px;
    }
  }

  @media screen and (max-width: 600px) {
    #review {
      font-size: 20px;
    }
  }

  @media screen and (min-width: 601px) {
    #dialog {
      font-size: 26px;
    }
  }

  @media screen and (max-width: 600px) {
    #dialog {
      font-size: 20px;
    }
  }
</style>