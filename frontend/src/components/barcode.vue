<template>
<div>
    <button>
    <StreamBarcodeReader
    @decode="onDecode"
    @loaded="onLoaded"
></StreamBarcodeReader>
    </button>

     <button>
<ImageBarcodeReader
    @decode="onDecode"
    @error="onError"
></ImageBarcodeReader>
 </button>
</div>
</template>
<script>
import { StreamBarcodeReader } from "vue-barcode-reader";
import { ImageBarcodeReader } from "vue-barcode-reader";

export default {
    methods: {
    onDecode (result) {
    console.log(result)
  }
}
}
</script>