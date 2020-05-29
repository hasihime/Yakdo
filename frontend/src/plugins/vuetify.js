import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import '@mdi/font/css/materialdesignicons.css'
// import colors from 'vuetify/lib/util/colors'
import 'material-design-icons-iconfont/dist/material-design-icons.css' // Ensure you are using css-loader

Vue.use(Vuetify);

const opts = {
    icons : {
        iconfont: 'mdi',
    },
    theme: {
        light: true,
        themes: {
            light: {
                primary: 'red',
                secondary: 'blue',
                accent: '',
                error: '',
                info: '',
                success: '',
                warning: '',
            },
            dark: {
                primary: 'gray',
                secondary: 'red'
            }
        }
    },
}

export default new Vuetify(opts)