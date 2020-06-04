export default [
  {
    path: "",
    view: "Home",
    name: "home"
  },
  {
    path: "/pharmacy/:id", // /:id
    view: "PharmacyDetail",
    name: "pharmacyDetail"
  },
  {
    path: "/mypharmacy", // /:id
    view: "MyPharmacy",
    name: "MyPharmacy"
  },
];
