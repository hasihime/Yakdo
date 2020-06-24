# 약도 - 슬기로운 약국 생활

![약도](design/picture/약도아이콘.png)

![슬기로운약국생활](design/picture/슬기로운약국생활아이콘.png)

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
$ sudo apt-get install python-certbot-nginx
```
2-2.  SSL 인증 획득하기

```bash
$ sudo certbot --nginx -d k02a4051.p.ssafy.io
```

이후 나오는 화면에서 본인 이메일 입력

```
 Saving debug log to /var/log/letsencrypt/letsencrypt.log
 Plugins selected: Authenticator nginx, Installer nginx
 Enter email address (used for urgent renewal and security notices) (Enter 'c' to cancel)
```

다음을 화면에서  A 입력

```
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 Please read the Terms of Service at
 https://letsencrypt.org/documents/LE-SA-v1.2-November-15-2017.pdf. You must 
 agree in order to register with the ACME server at
 https://acme-v02.api.letsencrypt.org/directory
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 (A)gree/(C)ancel:
```

다음화면에서 Y 입력

```
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 Would you be willing to share your email address with the Electronic Frontier
 Foundation, a founding partner of the Let's Encrypt project and the non-profit
 organization that develops Certbot? We'd like to send you email about our work
 encrypting the web, EFF news, campaigns, and ways to support digital freedom.
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 (Y)es/(N)o:
```

다음은 Http로 접근 했을 때 자동으로 https로 리다이렉트 할 수 있게 해주게 설정을 위해 2번을 클릭
```
 Please choose whether or not to redirect HTTP traffic to HTTPS, removing HTTP access.
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 1: No redirect - Make no further changes to the webserver configuration.
 2: Redirect - Make all requests redirect to secure HTTPS access. Choose this for
 new sites, or if you're confident your site works on HTTPS. You can undo this
 change by editing your web server's configuration.
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 Select the appropriate number [1-2] then [enter] (press 'c' to cancel): 
```

성공적으로 완료 되면 다음과 같이 나온다.
```
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -   
 new certificate deployed with reload of nginx server; fullchain is
 /etc/letsencrypt/live/k02a4051.p.ssafy.io/fullchain.pem
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 ** DRY RUN: simulating 'certbot renew' close to cert expiry
 **          (The test certificates below have not been saved.)

 Congratulations, all renewals succeeded. The following certs have been renewed:
   /etc/letsencrypt/live/k02a4051.p.ssafy.io/fullchain.pem (success)
 ** DRY RUN: simulating 'certbot renew' close to cert expiry
 **          (The test certificates above have not been saved.)
 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

 IMPORTANT NOTES:
  - Your account credentials have been saved in your Certbot
    configuration directory at /etc/letsencrypt. You should make a
    secure backup of this folder now. This configuration directory will
    also contain certificates and private keys obtained by Certbot so
    making regular backups of this folder is ideal.
```


완료가 되면 nginx 를 재시작 해준다.

```bash
$ sudo systemctl restart nginx
```
2-3 방화벽 설정

https 포트인 443 port를 열어주자.
```bash
$ sudo firewall-cmd --add-port 443/tcp --permanent
$ sudo firewall-cmd --reload
```
https://www.ssllabs.com/ssltest
