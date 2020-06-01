# 약도 - 슬기로운 약국 생활

![약도](design/picture/약도아이콘.png){: width="100" height="100"}{: .center}
![슬기로운약국생활](design/picture/슬기로운약국생활아이콘.png){: width="100" height="100"}{: .center}

## 기획 배경 및 목적

## 설치 및 실행

1. git clone https://lab.ssafy.com/s02-final/s02p31a405.git

### Backend

1. Intellij - File - Open - yakdo 
2. gradle compileJava (Intellij에서 자동 실행)
3. 그 외 yakdo/README.md 참조

### Frontend

1. Intellij - File - Open - frontend
2. yarn install (Intellij에서 자동 실행)
3. 그 외 frontend/README.md 참조

## Server

1. 기본 설정
2. SSL 적용

Let’s Encrypt를 이용해서 SSL을 적용해보자.

2-1. certbot 설치

```bash
$ sudo add-apt-repository ppa:certbot/certbot
$ sudo apt-get update
$ sudo apt-get install python-certbot-nginx``
```

