<template>
  <div class="col-md-12">
      <div v-if="checkUser">
        <GoogleLogin
            class="big-button"
            :params="params"
            :renderParams="renderParams"
            :onSuccess="onSuccess"
            :onFailure="onFailure"
            
            >
            Login
        </GoogleLogin>
      </div>
      <div v-if="checkUser">
        <GoogleLogin
            class="big-button"
            :params="params"
            :renderParams="renderParams"
            :onSuccess="logout"
            :logoutButton=true
            
            >Logout
        </GoogleLogin>
    </div>

  </div>
</template>

<script>
import GoogleLogin from 'vue-google-login'
// import { mapState, mapActions } from "vuex"

export default {
    data() {
        return {
            params: {
                client_id:"592445092420-mip4ruvi9pnv365l434rnaqpauref12c.apps.googleusercontent.com"
            },
            renderParams: {
                width: 250,
                height: 50,
                logtitle: true,
            }
        }
    },
    components: {
        GoogleLogin,
    },
    computed: {
        // ...mapState({
        //     user: state => state.data.user
        // })
    },
    methods: {
        // ...mapActions("data", ["login"]),
        onSuccess(googleUser) {
            // console.log(googleUser)
            // console.log(googleUser.getBasicProfile())
            /*
                Google Login 하면 주는 정보
            */
            // console.log(googleUser.getBasicProfile().getId()) // 고유id
            // console.log(googleUser.getBasicProfile().getName()) // 성+이름
            // console.log(googleUser.getBasicProfile().getEmail()) // 이메일
            // console.log(googleUser.getBasicProfile().getImageUrl()) // 이미지
            // console.log(googleUser.getBasicProfile().getFamilyName()) // 성
            // console.log(googleUser.getBasicProfile().getGivenName()) // 이름

            sessionStorage.setItem("user",googleUser.getBasicProfile().getEmail())
            console.log(sessionStorage.getItem("user"))

            // 쓸모없는 것 같아서 지움
            // this.login({
            //     id: googleUser.getId(),
            //     name: googleUser.getBasicProfile().getName(), // 성+이름
            //     email: googleUser.getBasicProfile().getEmail(), // 이메일
            //     imageUrl: googleUser.getBasicProfile().getImageUrl(), // 이미지
            //     familyName: googleUser.getBasicProfile().getFamilyName(), // 성
            //     givenName: googleUser.getBasicProfile().getGivenName(), // 이름
            // })
        },
        onFailure(error) {
            console.log(error)
            alert("구글 로그인에 실패했습니다.")
        },
        logout() {
            sessionStorage.clear()
            console.log("로그아웃:"+sessionStorage.getItem("user"))
        },
        checkUser() {
            console.log("체크:"+sessionStorage.getItem('user'))
            if(sessionStorage.getItem('user')!= null) return true;
            else return false;
        },
    },
}
</script>

<style>

</style>