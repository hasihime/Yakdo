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
  </v-card>
</template>

<script>
import Review from "./Review" // @/components가 안먹힘 왠지모르겠다.

export default {
    data() {
        return {
            
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