<template>
  <v-card id="review" style="padding:20px">
    <h3>N개의 리뷰</h3>

    <Review></Review>
    <Review></Review>
    <Review></Review>
    <!-- <div v-for="review in reviews"
        :key="review.revew_no">
        <Review
          :profile="review.profile"
          :nickname="review.nickname"
          :regdate="review.reg_date"
          :before="review.before"
          :contents="review.contents"
          :score="review.score"
          :store_name="store_name"
          :store_address="store_address"
          :images="review.images"
        />
      </div>
    <div v-if="reviews.length == 0" class="store_not_review">리뷰가 없습니다.</div> -->
    <v-dialog
      v-model="dialog"
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
import Review from "./Review" // 지승 : @/components가 안먹힘 왠지모르겠다.
import {  mapActions  } from "vuex";

export default {
    data() {
        return {
            dialog: false,
            r_writer: "작성자",   // 작성자(로그인:구글ID, 비로그인:IP)
            r_pw: "1234",       // 비밀번호(비로그인)
            r_content: "댓글입니다..",  // 댓글 내용
            p_id: ""        // 약국 ID
        }
    },
    components: {
        Review,
    },
    methods: {
      ...mapActions("data", ["postReview"]),

      async createReview() {
        console.log("writer:"+this.r_writer)
        console.log("pw:"+this.r_pw)
        console.log("content:"+this.r_content)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // 비밀번호 채워졌는지, 내용 채워졌는지 검사
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        await this.postReview({
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
      }
    },
    mounted() {
      this.p_id = this.$route.params.id
      console.log("약국번호 : "+this.p_id)
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