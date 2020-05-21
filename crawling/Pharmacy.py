from selenium import webdriver
from urllib.request import urlopen
from bs4 import BeautifulSoup
import pymysql
import sys
import io
import datetime
from selenium.webdriver.common.alert import Alert
from selenium.common.exceptions import UnexpectedAlertPresentException
import time

# sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')    #utf-8 인코딩
# sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')    #utf-8 인코딩

db=pymysql.connect("localhost","root","ssafy","yakdo",charset="utf8")
cursor = db.cursor()
driver = webdriver.Chrome('C:/Users/multicampus/Downloads/chromedriver_win32/chromedriver') #Chrome 드라이버 사용
driver.implicitly_wait(3)                           # 3초 기다림(로딩)
for p in range(164,922):
    name=[]
    addr=[]
    tel=[]
    oper=[]
    status=[]
    loc=[]
    special=[]
    post=[]
    driver.get('https://www.pharm114.or.kr/main.asp')   # main 사이트 접속
    html = driver.page_source                           # 페이지 소스 받아오기
    soup = BeautifulSoup(html, 'html.parser')           # BeautifulSoup 파싱
    data_result=str(soup.find('input',{'name':'ss_key'}))   #ss_key 찾기
    ss_key=data_result.split(' ')[3]                        
    ss_key=ss_key.replace('value="','').replace('"/>','')   #ss_key만 뽑기
    print(str(p)+"번째 "+ss_key)
    driver.get('https://www.pharm114.or.kr/search/search_result.asp?addr1=&addr2=&m_year=2020&m_month=05&m_day=14&time_s1=06:00&time_e1=30:30&addr3=&tmp_c_name=&s_type=&realtime_TF=&OnlyDangBun_TF=&pubNight_TF=&ss_key='+ss_key+'&page='+str(p))
    html2 = driver.page_source
    soup = BeautifulSoup(html2, 'html.parser')
    data_result=soup.find('div',{'id':'printZone'}).findAll('table')[1].findAll('tr')
    bloc=False
    bspe=False
    for datas in data_result:
        # print(str(datas).encode('utf-8'))
        tds=datas.findAll('td')
        if(len(tds)>1):
            cnt=0
            for td in tds:
                cnt=cnt+1
                if(cnt==1):
                    if(not bloc):
                        loc.append('')
                    if(not bspe):
                        special.append('')
                    bloc=False
                    bspe=False
                    name.append(" ".join(td.text.replace('\xa0','').split()))
                if(cnt==3):
                    tmp=" ".join(td.text.replace('\xa0','').split())
                    post.append(tmp.split(")")[0].replace('(',''))
                    tmp=tmp.replace(tmp.split(")")[0],"").replace(")","")
                    addr.append(tmp)
                if(cnt==4):
                    tel.append(" ".join(td.text.replace('\xa0','').split()))
                if(cnt==5):
                    oper.append(" ".join(td.text.replace('\xa0','').split()))
                if(cnt==6):
                    status.append(" ".join(td.text.replace('\xa0','').split()))
                else:
                    continue
        else:
            for td in tds:
                if(td.text.replace('\xa0','').split(" ")[0]=="[위치정보"):
                    bloc=True
                    loc.append(" ".join(td.text.replace('\xa0','').split()))
                elif(td.text.replace('\xa0','').split(" ")[0]=="[특이사항"):
                    bspe=True
                    special.append(" ".join(td.text.replace('\xa0','').split()))
    data=[]
    for i in range(len(name)):
        data.append((name[i],addr[i],tel[i],oper[i],status[i],special[i],loc[i],post[i]))
    print(data)
    query="""insert into pharmacy(p_name,p_addr,p_tel,p_oper,p_status,p_special,p_loc,p_post) values (%s,%s,%s,%s,%s,%s,%s,%s)"""
    cursor.executemany(query, tuple(data))
    db.commit()