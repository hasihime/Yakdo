<template>
  <div style="height:100%;">
    <MedicineList style="height:100%;"/>
    
  </div>
  
</template>

<script>
import MedicineList from "@/components/MedicineList"

export default {
  components: {
      MedicineList,
    },
}
</script>

<style>
  html, body{
    width: 100%; height: 100%;
  }
</style>