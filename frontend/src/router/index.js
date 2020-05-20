import Vue from "vue";
import Router from "vue-router";

import Home from "../views/Home.vue"

// Routes
// import paths from "./paths";

// function route(path, view, name) {
//   return {
//     name: name || view,
//     path,
//     component: resolve => import(`@/views/${view}.vue`).then(resolve)
//   };
// }

Vue.use(Router);

// Create a new router
// const router = new Router({
//   mode: "history",
//   routes: paths
//     .map(path => route(path.path, path.view, path.name))
//     ,// .concat([{ path: "*", redirect: "/" }]),
//   scrollBehavior(to, from, savedPosition) {
//     if (savedPosition) {
//       return savedPosition;
//     }
//     if (to.hash) {
//       return { selector: to.hash };
//     }
//     return { x: 0, y: 0 };
//   }
// });

// export default router;


export default new Router({
  mode: "history",
  routes: [
    // {
    //   path: "",
    //   view: "Home",
    //   name: "home"
    // },
    {
      path: "/",
      component: Home,
    },
    // {
    //   path: "/pharmacy",
    //   component: PharmacyDetail,
    // },
  ]
});