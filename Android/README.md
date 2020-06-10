# 안드로이드 개발 방법

- 기본적으로 윈도우에서 안드로이드 스튜디오 작업

## jenkins CI

- 리눅스에 android studio 설치

- https://developer.android.com/studio/intro/update.html#download-with-gradle

- 윈도우와 리눅스 간의 설정 차이로 빌드가 되지 않는 문제를 방지하기 위해 gitignore에 추가된 local.properties를 직접 생성

```
sdk.dir = /usr/lib/android-sdk
```

- 윈도우에서 sdk licenses 폴더를 리눅스 sdk.dir로 지정된 홈에 복사

- gradle build
