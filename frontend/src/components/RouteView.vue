<template>
  <v-content style="padding:0px;">
    <div id="app-view" style="height:100%;">
      <v-fade-transition mode="out-in">
        <router-view />
      </v-fade-transition>
    </div>
  </v-content>
</template>