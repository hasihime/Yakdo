# backend 

## Intellij 에서 추가 설정
- dependencies lombok을 다음과 같이 설정 

```
compileOnly 'org.projectlombok:lombok:1.18.6'
annotationProcessor 'org.projectlombok:lombok:1.18.6'
출처: https://imasoftwareengineer.tistory.com/31 [삐멜 소프트웨어 엔지니어]
```

- 주의 : IDE에서 terminal로 gradle 명령어를 치지 말것 

## how to run dev

- main/java/com/noyak/yakdo/springboot/Application.java 에서 main 클릭 실행
- 또는 
- Intellij - 오른쪽 Gradle 탭 - yakdo - Tasks - application - bootRun
- gradle init clean bootRun

## how to build

- 백엔드가 web file(html, jsp 같은)을 포함하지 않아 빌드해도 딱히 의미는 없다
- Intellij - 오른쪽 Gradle 탭 - yakdo - Tasks - build - build

## how to deploy (making Runnable Jar)

- Intellij - 오른쪽 Gradle 탭 - yakdo - Tasks - build - assemble
- yakdo/build/libs/ 폴더 아래에 jar 파일이 생성된다
- 실행방법은 java -jar a.jar 이다.