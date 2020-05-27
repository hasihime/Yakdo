<template>
    <div class="z-index-structure">
            <toolbar />
            <drawer />
        <div id="notice" @click="fold">
            <span style="font-weight:bold">클릭 ▼</span>
            <div id="contents" style="display:none;padding:5px">
                이 페이지에 필요한 기능
                <ol>
                    <li>현재 위치 다시 부르기 버튼과 함수</li>
                    <li>메뉴 보이기 버튼</li>
                    <li>검색창과 검색된 리스트 화면에 띄우기</li>
                    <li></li>
                </ol>
            </div>
        </div>
        <br>
        <br>
        <!-- 검색결과가 반영되는 Main Map -->
        <div id="map" class="z-fixed-map"/>
        <!-- Modal -->
        <div id="openModal" class="modal" v-show='menutoggle'>
            <div class="modal__content">
                <a href="#" class="close" @click='menutoggle = !menutoggle'>+</a>
                <b>MEOW MEOW MEOW</b>
                <div class="label" id="searchBar">
                    <span class="center">검색창</span>
                </div>
            </div>
        </div>
        <!-- Menu Button -->
        <div class="menu-btn">
            <button @click='menutoggle = !menutoggle'> Menu </button>
        </div>
    </div>
</template>

<script>
    import Drawer from "@/components/main/Drawer";
    import Toolbar from "@/components/main/Toolbar";
    import {mapState, mapActions} from "vuex";

    export default {
        components: {
            Drawer,
            Toolbar
        },
        data() {
            return {
                menutoggle : false,
                centerlat: 0,
                centerlng: 0,
            };
        },
        computed: {
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
            this.getMyPos();
            window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
            this.centerlat = this.mylat;
            this.centerlng = this.mylng;
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
                // 마커 추가시 객체 추가
                var marker = new kakao.maps.Marker({
                    position: map.getCenter(),
                    title: "현위치",
                });
                marker.setMap(map);

                var here = new kakao.maps.InfoWindow({
                    zIndex: 1,
                    content:
                        '<div id="infowindow" style="padding:5px;font-size:12px;text-align:center;" onclick="this.parentNode.parentNode.style.display=\'none\';">현위치</div>',
                });

                here.open(map, marker);

                var wincss = document.getElementById("infowindow");
                wincss.parentNode.style.position = "";

            },
            addScript() {
                const script = document.createElement("script");
                /* global kakao */
                script.onload = () => kakao.maps.load(this.initMap);
                // https://apis.map.kakao.com/web/documentation/#load_load
                script.src = "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7ecec229ce36d02dc4edee1fe7e13efd&libraries=services"

                document.head.appendChild(script);
            },
            fold() {
                var con = document.getElementById("contents");
                if (con.style.display == "none") {
                    con.style.display = "block";
                } else {
                    con.style.display = "none";
                }
            },
        },
    };
</script>
<style scoped>
    .z-index-structure{
        height:100%;
    }

    .z-fixed-map {
        height:500px;
    }

    .modal {
        position: fixed;
        z-index: 100;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background-color: rgba(0, 0, 0, 0.2);
        transition: opacity 300ms ease-in-out;
    }
    :target {
         display: block;
    }

    .modal__content {  /* modal content*/
         position: absolute;
         width: 100%;
         max-width: 240px;
         margin: 20px auto;
         padding: 20px;
         background-color: #ffffff;
         top: 45%;
         left: 50%;
         border-radius: 20px;
         box-shadow: 0 0 10px rgba(0,0,0,0.25);
         transform: translateX(-50%) translateY(-50%);
         pointer-events: auto;
    }

    img {
        width: 140px;
    }

    .close {
        display: block;
        position: absolute;
        top: -26px;
        right: 3px;
        padding: 40px 5px;
        font-size: 60px;
        line-height: 23px;
        color: rgba(0,0,0,0.75);
        transform: rotate(45deg);
        cursor: pointer;
        transition: all 50ms ease-in-out;
    }

    :hover {
        color: rgba(0,0,0,0.25);
    }

    .menu-btn {
        position: fixed;
        z-index: 5;
        top: 5%;
        right: -15px;
        padding: 15px 30px;
        border-radius: 10px;
        background-color: magenta;
        color: #ffffff;
        box-shadow: 0 -3px 7px rgba(0,0,0,0.3);
    }


    .label .center {
        padding: 10px;
    }

    .label * {
        background-color: antiquewhite;
        display: inline-block;
        vertical-align: top;
    }


</style>
