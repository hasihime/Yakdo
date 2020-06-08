<template>
  <div>
    <h1 style="padding-top:25px;">About Us</h1>
    <h2 style="padding:15px;">Team 약하지않아요</h2>
    <div style="text-align:left;">
      <v-card class="mx-auto" max-width="434">
        <v-container>
          <v-row dense>
            <v-col v-for="(developer, i) in developers" :key="i" cols="12">
              <v-card :color="developer.color">
                <div class="d-flex flex-no-wrap justify-space-between">
                  <div>
                    <v-card-title
                      class="headline"
                      v-text="developer.name"
                    ></v-card-title>

                    <v-card-subtitle
                      v-text="developer.position"
                    ></v-card-subtitle>
                  </div>

                  <v-avatar class="ma-3" size="125" tile>
                    <v-img :src="developer.image"></v-img>
                  </v-avatar>
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    developers: [
      {
        name: "이은택",
        color: "green",
        position: "팀장, 안드로이드 개발자",
        image: require("../assets/static/images/이은택.jpg"),
      },
      {
        name: "이정훈",
        color: "orange",
        position: "백엔드 개발자, UCC 담당",
        image: require("../assets/static/images/이정훈.jpg"),
      },
      {
        name: "오석빈",
        color: "blue",
        position: "백엔드 개발자, 서버, AWS 배포 담당",
        image: require("../assets/static/images/오석빈.png"),
      },
      {
        name: "김지승",
        color: "purple",
        position: "프론트엔드 개발자, 디자인 담당",
        image: require("../assets/static/images/김지승.jpg"),
      },
      {
        name: "권순국",
        color: "pink",
        position: "프론트엔드 개발자, 공공api 담당",
        image: require("../assets/static/images/권순국.jpg"),
      },
    ],
  }),
};
</script>

<style></style>
