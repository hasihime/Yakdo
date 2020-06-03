import Vue from "vue";
import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";
import "@mdi/font/css/materialdesignicons.css";

Vue.use(Vuetify);

const opts = {
  theme: {
    // dark: true, // 다크모드
    themes: {
      light: {
        primary: "#457b3e", // rgb(69, 123, 62)
        secondary: "#70b366", // rgb(112,179,102)
        accent: "black",
        error: "",
        info: "#fafafa",
        success: "",
        warning: "",
      },
      dark: {
        primary: "#70b366", // rgb(69, 123, 62)
        secondary: "#32572d", // rgb(112,179,102)
        accent: "white",
        error: "",
        info: "#303030",
        success: "",
        warning: "",
      },
    },
  },
  icons: {
    iconfont: "mdi",
  },
};

export default new Vuetify(opts);
