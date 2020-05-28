<template>
    <div>
        <!-- 카카오맵 -->
        <div id="map" style="height:100vh;"></div>
        <!-- 상단바는 맵 위로 올라가야한다 -->

        <v-btn class="menu-btn" v-if="responsive" icon @click.stop="onClickOpener">
            메뉴버튼
            <v-icon>mdi-view-list</v-icon>
        </v-btn>

        <input id=text
               type="text"
               class='search-bar'
               name="search"
               v-on:keyup.enter="submit"
               placeholder="검색을 원하는 주소를 입력해주세요"
        />

        <v-btn class="search-btn" icon @click.stop="doSearch()">
            검색
            <v-icon>mdi-view-list</v-icon>
        </v-btn>

        <!-- https://apis.map.kakao.com/web/sample/moveMap/ -->
        <v-btn class="pos-btn"
               icon
               @click.stop="panTo()">
            현위치
            <v-icon>mdi-view-list</v-icon>
        </v-btn>

        <div class="notice-bar">
            <!--안내 메시지 -->
            (예시) 가장 가까운 약국은 500m 떨어진 XX 약국이며, 운영종료시간은 22시입니다.
            <br>
            (예시) 반경 1km 내에 열린 약국이 없습니다.
        </div>



        <!-- 네비게이션바는 기본적으로 감춰져있어야한다 -->
        <navbar></navbar>
        <!--  -->
    </div>
</template>

<script>
    import Navbar from "@/components/main/Navbar";
    import {mapState, mapActions, mapMutations} from "vuex";

    export default {
        components: {
            Navbar,
        },
        data() {
            return {
                responsive: false,
                menutoggle: false,
                centerlat: 0,
                centerlng: 0,
                kmap: null,
            };
        },
        computed: {
            ...mapState("navbar", ["isOpen"]),
            ...mapState({
                mylat: function () {
                    return this.$store.state.map.mylat;
                },
                mylng: function () {
                    return this.$store.state.map.mylng;
                },
            }),
        },
        created() { // 상위 App created => 하위 child created
            console.log("Home vue created");
            this.getMyPos();
            // console.log("this.mylat : ", this.mylat);
            // console.log("this.mylng : ", this.mylng);
        },
        mounted() { // child 인 Home 의 mounted가 끝나고 => 상위 mounted 실행
            console.log("Home vue mounted");

            this.onResponsiveInverted();
            window.addEventListener("resize", this.onResponsiveInverted);

            this.getMyPos();
            window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
            this.centerlat = this.mylat;
            this.centerlng = this.mylng;
        },
        beforeDestroy() {
            window.removeEventListener("resize", this.onResponsiveInverted);
        },
        methods: {
            ...mapActions("map", ["getMyPos"]),
            initMap() {
                var container = document.getElementById("map");
                // console.log("state 기본 저장된 서울 시청 위치");
                // console.log(this.mylat, this.mylng);
                var options = {
                    center: new kakao.maps.LatLng(this.mylat, this.mylng),
                    level: 3,
                };
                var map = new kakao.maps.Map(container, options);
                this.setMap(map);
                // 마커 추가시 객체 추가
                var marker = new kakao.maps.Marker({
                    position: this.kmap.getCenter(),
                    title: "현위치",
                });
                marker.setMap(this.kmap);

                var here = new kakao.maps.InfoWindow({
                    zIndex: 1,
                    content:
                        '<div id="infowindow" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">현위치</div>',
                });

                here.open(this.kmap, marker);

                var wincss = document.getElementById("infowindow");
                wincss.parentNode.style.position = "";

            },
            setMap(map) {
                this.kmap = map;
            },
            addScript() {
                const script = document.createElement("script");
                /* global kakao */
                script.onload = () => kakao.maps.load(this.initMap);
                // https://apis.map.kakao.com/web/documentation/#load_load
                script.src = "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7ecec229ce36d02dc4edee1fe7e13efd&libraries=services"

                document.head.appendChild(script);
            },
            panTo() {
                // 이동할 위도 경도 위치를 생성합니다
                var moveLatLon = new kakao.maps.LatLng(this.mylat, this.mylng);

                // 지도 중심을 부드럽게 이동시킵니다
                // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
                this.kmap.panTo(moveLatLon);
            },
            submit() {
                console.log("enter 입력");
                this.doSearch();
            },
            doSearch() {
                console.log("doSearch");
                // axios로 현위치와 시간을 전송하고

                // 돌려받은 약국 리스트를 보여주는 방식 => 3개씩 슬라이드 방식?

                //

            },


            /* 햄버거 버튼 */
            ...mapMutations("navbar", ["setOpen"]),
            onClickOpener() {
                this.setOpen(!this.isOpen);
            },
            onResponsiveInverted() {
                if (window.innerWidth < 10000) {
                    this.responsive = true;
                } else {
                    this.responsive = false;
                }
            },
        },
    };
</script>
<style scoped>

    .menu-btn {
        position: fixed;
        z-index: 3;
        top: 2%;
        left: 5%;
        padding: 15px 30px;
        border-radius: 10px;
        background-color: magenta;
        color: #ffffff;
        box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
    }

    .search-bar {
        position: fixed;
        z-index: 3;
        top: 2%;
        left: 15%;
        display: inline-block;
        width: 60%;
        height: 36px;
        border: 3px solid #200400;
        border-radius: 25px;
        background: white;
        padding-left: 15px;
        padding-right: 15px;
    }

    .search-btn {
        position: fixed;
        z-index: 3;
        top: 2%;
        right: 15%;
        padding: 15px 30px;
        border-radius: 10px;
        background-color: magenta;
        color: #ffffff;
        box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
    }

    .pos-btn {
        position: fixed;
        z-index: 3;
        top: 2%;
        right: 5%;
        padding: 15px 30px;
        border-radius: 10px;
        background-color: magenta;
        color: #ffffff;
        box-shadow: 0 -3px 7px rgba(0, 0, 0, 0.3);
    }

    .notice-bar {
        position: fixed;
        z-index: 3;
        top: 8%;
        left: 5%;
        display: inline-block;
        width: 90%;
        height: 50px;
        background: white;
    }

</style>
