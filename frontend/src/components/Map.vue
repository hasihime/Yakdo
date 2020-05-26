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
            📍 상세주소
            <v-btn style="margin-left:5px;" small color="primary">길찾기</v-btn>
            <p style="padding:8px">제주특별시 서귀포구 ~</p>
        </div>
      </v-container>
  </v-card>
</template>

<script>
var map

export default {
    data: () => ({
        width: window.innerWidth
    }),
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
    methods :{
        initMap() {
            var latitude = 33.450701
            var longitude = 126.570667
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new window.kakao.maps.LatLng(latitude, longitude), // 위도, 경도
                level: 3 //지도의 레벨(확대, 축소 정도)
            };

            map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            /////

            var imageSrc = 'https://b.kisscc0.com/20180705/jpq/kisscc0-google-map-maker-drawing-pin-marker-pen-google-map-green-map-marker-5b3e80738f1614.9818166215308227715861.png', //https://i.ya-webdesign.com/images/location-clipart-8.png
                imageSize = new window.kakao.maps.Size(27,40),
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
        addSciprt() {
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
    }
}

</script>

<style>
#map {width: 100%; height: 100%;}
.map_wrap {position:relative;overflow:hidden;width:100%;height: 300px; margin-bottom: 18px;}
.map_wrap {position:relative;overflow:hidden;width:100%;height:350px;}

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