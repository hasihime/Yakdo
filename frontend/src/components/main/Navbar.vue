<template>
    <v-navigation-drawer
            id="app-drawer"
            v-model="inputValue"
            app
            dark
            floating
            persistent
            mobile-break-point="900"
            width="250"
    >
        <h1 style="width:100%;">
            <div style="
              text-align:center;
              padding:12px;
              font-size:20px;
              color:white;
              background-color: darkseagreen;
              ">약도
            </div>
        </h1>
        <v-layout column>
            <v-list rounded>
                <v-list-item
                        v-for="(link, i) in links"
                        :key="i"
                        :to="link.to"
                        active-class="red accent-2 white--text"
                        class="v-list-item ma-3"
                >
                    <v-list-item-action>
                        <v-icon>{{ link.icon }}</v-icon>
                    </v-list-item-action>
                    <v-list-item-title v-text="link.text"/>
                </v-list-item>
            </v-list>
        </v-layout>
    </v-navigation-drawer>
</template>

<script>
    import {mapMutations, mapState} from "vuex";

    export default {
        props: {
            opened: {
                type: Boolean,
                default: false
            }
        },
        data: () => ({
            links: [
                {
                    to: "/",
                    icon: "mdi-home",
                    text: "Home"
                },
            ]
        }),
        computed: {
            /* store/modules 의 navbar.js 파일 내부의 isOpen state를 사용하겠다 선언 */
            ...mapState("navbar", ["isOpen"]),
            inputValue: {
                get() {
                    /* 열려있는지 여부를 vuex에서 받아온 값으로 읽는다 */
                    return this.isOpen;
                },
                set(val) {
                    /* 열려있는지 여부를 vuex에서 받아온 값으로 읽는다 */
                    this.setOpen(val);
                }
            }
        },
        methods: {
            /* store/modules 의 navbar.js 파일 내부의 setOpen mutation을 사용하겠다 선언 */
            ...mapMutations("navbar", ["setOpen"])
        }
    };
</script>
