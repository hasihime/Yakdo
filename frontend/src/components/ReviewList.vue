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
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
        댓글
        </v-card-title>

        <v-card-text>
          ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            I accept
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import Review from "./Review" // @/components가 안먹힘 왠지모르겠다.

export default {
    data() {
        return {
            dialog: false,
        }
    },
    components: {
        Review,
    }
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
</style>