import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify, {
    theme: {
        // themes: {
        //     light: {
                primary: colors.purple,
                secondary: colors.grey.darken1,
                accent: '',
                error: '',
                info: '',
                success: '',
                warning: '',
            // },
            // dark: {

            // }
        // }
    }
})

const opts = {
    // theme: {
    //     // light: true,
    //     themes: {
    //         light: {
    //             primary: 'red',
    //             secondary: 'blue',
    //             accent: '',
    //             error: '',
    //             info: '',
    //             success: '',
    //             warning: '',
    //         },
    //         dark: {
    //             // primary: 'gray',
    //             // secondary: 'red'
    //         }
    //     }
    // }
}

export default new Vuetify(opts)