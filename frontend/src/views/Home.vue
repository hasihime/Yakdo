<template>
    <div>
        <div id="notice" @click="fold">
            <span style="font-weight:bold">클릭 ▼</span>
            <div id="contents" style="display:none;padding:5px">
                이 페이지에 필요한 기능
                <ol>
                    <li>현재 위치 다시 부르기 버튼과 함수</li>
                    <li>메뉴 보이기 버튼</li>
                    <li>검색창과 검색된 리스트 화면에 띄우기</li>
                </ol>
            </div>
        </div>
        <br>
        <div id="map" style="height: 500px"/>
    </div>

</template>

<script>

    import {mapState, mapActions} from "vuex";

    export default {
        components: {},
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
        created() {
            this.getMyPos();
            // console.log("this.mylat : ", this.mylat);
            // console.log("this.mylng : ", this.mylng);
        },
        mounted() {
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

                /* 커스텀 오버레이 버튼 */

                // 1. 메뉴 버튼
                var menuBtn = '<div class="label" >' +
                    '<button class="center" @click="menutoggle = !menutoggle">메뉴 버튼</button>' +
                    '</div>';
                // 메뉴 버튼이 표시될 위치
                var position = new kakao.maps.LatLng(this.mylat + 0.0001, this.mylng + 0.0007);
                // 메뉴 버튼의 오버레이 생성
                var customOverlay = new kakao.maps.CustomOverlay({
                    position: position,
                    content: menuBtn
                });
                // 커스텀 오버레이를 지도에 표시
                customOverlay.setMap(map);

                // 2. 검색창
                var searchBar = '<div v-show="menutoggle" class="label" id="searchBar">' +
                    '<span class="center">검색창</span>' +
                    '</div>';
                // 검색창이 표시될 위치
                position = new kakao.maps.LatLng(this.mylat + 0.002, this.mylng);
                // 검색창의 오버레이 생성
                customOverlay = new kakao.maps.CustomOverlay({
                    position: position,
                    content: searchBar
                });
                // 커스텀 오버레이를 지도에 표시
                customOverlay.setMap(map);

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
            menufold() {
                console.log("클릭");
                var con = document.getElementById("searchBar");
                if (con.style.display == "none") {
                    con.style.display = "block";
                } else {
                    con.style.display = "none";
                }
            },
        },
    };
</script>
<style>

    .label .center {
        padding: 10px;
    }

    .label * {
        background-color: antiquewhite;
        display: inline-block;
        vertical-align: top;
    }

    #searchBar {
        display :none;
    }

</style>
