<template>
  <v-card>
      <v-container>
          <div class="map_wrap">
            <div id="map"></div>
            <div class="custom_zoomcontrol radius_border"> 
            <span @click="zoomIn"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span>  
            <span @click="zoomOut"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
            </div>
        </div>
        <div style="text-align:left;">
            <h3>📍 상세주소</h3>
            <!-- <v-btn style="margin-left:5px;" small color="primary">길찾기</v-btn> -->
            <p style="padding-left:8px;margin:0px">{{p_addr}}</p>
            <p v-if="p_loc.length!=0" class="primary--text" style="padding-left:8px;margin:0px">{{p_loc}}</p>
        </div>
        <v-btn
            :href="'https://map.kakao.com/link/to/'+p_name+','+latitude+','+longitude"
            style="margin-left:5px;position:absolute;right:11px;bottom:11px;"
            small color="primary">
            길찾기
        </v-btn>
      </v-container>
  </v-card>
</template>

<script>
let map

export default {
    props: {
        p_name : {
            type: String,
            default: () => ""
        },
        p_post : {
            type: String,
            default: () => "",
        },
        p_addr: {
            type: String,
            default: () => "",
        },
        p_loc: {
            type: String,
            default: () => "",
        },
        latitude: {
            // type: Number,
            default: 33.450701,
        },
        longitude: {
            // type: Number,
            default: 126.570667,
        },
    },
    data: () => ({
        width: window.innerWidth,
        startlat: 0,
        startlng: 0,
    }),
    methods :{
        initMap() {
            // console.log(this.latitude +" "+this.longitude)
            let latitude = this.latitude;
            let longitude = this.longitude;
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new window.kakao.maps.LatLng(latitude, longitude), // 위도, 경도
                level: 3 //지도의 레벨(확대, 축소 정도)
            };

            map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            // 초록색: https://b.kisscc0.com/20180705/jpq/kisscc0-google-map-maker-drawing-pin-marker-pen-google-map-green-map-marker-5b3e80738f1614.9818166215308227715861.png
            // 빨간색: https://i.ya-webdesign.com/images/location-clipart-8.png
            var imageSrc = 'https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678111-map-marker-512.png', 
                // imageSize = new window.kakao.maps.Size(27,40), // 초록색
                imageSize = new window.kakao.maps.Size(50,50),
                imageOption = {offset: new window.kakao.maps.Point(27,69)}

            var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

            var markerPosition  = new window.kakao.maps.LatLng(latitude, longitude);
            var marker = new window.kakao.maps.Marker({ 
                position: markerPosition,
                image: markerImage,
            }); 

            marker.setMap(map); 

            // 정보 표시
            // var iwContent = `<div class="place_text" style="background-color:black">위치위치</div>`;
            // var iwPosition = new window.kakao.maps.LatLng(latitude, longitude);

            // var infowindow = new window.kakao.maps.InfoWindow({
            //     position : iwPosition, 
            //     content : iwContent 
            // });

            // infowindow.open(map, marker); 
        },
        addScript() {
            const script = document.createElement('script'); 
            script.onload = () => window.kakao.maps.load(this.initMap); 
            script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=6420cd4d34a5e7796808854207ac59f5'; 
            document.head.appendChild(script); 
        },
        setWindowWidth() {
            this.width = window.innerWidth;
        },
        zoomIn() {
            map.setLevel(map.getLevel() - 1);
        },
        zoomOut() {
            map.setLevel(map.getLevel() + 1);
        },
    },
    updated() {
        this.initMap()
        this.addSciprt()
    },
    mounted() {
        window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        this.setWindowWidth();
        window.addEventListener("resize", this.setWindowWidth);
    },
    beforeDestroy() {
        window.removeEventListener("resize", this.setWindowWidth);
    },
}

</script>

<style>
#map {width: 100%; height: 100%;}
.map_wrap {position:relative;overflow:hidden;width:100%;height: 50vh; margin-bottom: 18px;}
.map_wrap {position:relative;overflow:hidden;width:100%;height:50vh;}

.radius_border{border:1px solid #919191;border-radius:5px;}     
.custom_zoomcontrol {
    position:absolute;
    top:10px;
    right:10px;
    width:36px;
    height:80px;
    overflow:hidden;
    z-index:1;
    color:#457b3e;
    background-color:#f5f5f5; /* rgb(239,247,238) 연두색 */
} 
.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}     
.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;box-sizing: content-box;}             
.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}   
</style>