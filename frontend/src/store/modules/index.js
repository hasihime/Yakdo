const files = require.context(".", false, /\.js$/);
const modules = {};

// modules 폴더 안의 자신을 제외한 모든 js파일을 읽어와 파일명-모듈 쌍으로 객체화해 배포
// index.js를 제외한 모든 js를 file명을 키로 modules에 담아 리턴
files.keys().forEach((key) => {
    if (key === "./index.js") return;
    modules[key.toLowerCase().replace(/(\.\/|\.js)/g, "")] = files(key).default;
});

export default modules;
