import urllib.request
import urllib.parse
import pandas
import pymysql
import ast

conn = pymysql.connect(host='localhost',user='root',password='ssafy', db='yakdo', charset='utf8')
curs = conn.cursor(pymysql.cursors.DictCursor)
job_seq = 0
# key와 full 도로명 주소를 쿼리 합니다.
sql = "SELECT p_id, p_addr FROM pharmacy where p_x is null"
curs.execute(sql)
#쿼리한 결과를 p_val에 저장
p_val = curs.fetchall()
#판다스에 저장 합니다.
df = pandas.DataFrame(p_val, columns = ["p_id","p_addr"])
print(df)
for t in range(0,590):
    print(str(t)+"번째 주소 처리 중...")
    p_id = df.p_id[t]
    p_addr = df.p_addr[t]
    # Geocoder API 2.0
    ApiKey = "769E9E11-EA06-3016-8340-8580E867A9F4"
    address = p_addr
    print(address)
    # API 호출 부분
    apiUrl = 'http://api.vworld.kr/req/address?service=address&request=getCoord&key='+ApiKey+'&'
    values = { 'address':address, 'type':'ROAD' }
    param = urllib.parse.urlencode(values)
    Adding = apiUrl+param
    req = urllib.request.Request(Adding)
    res = urllib.request.urlopen(req)
    respon_data = res.read().decode()
    DataDict = ast.literal_eval(respon_data)
    v_check = DataDict['response']['status']
    print(v_check)
    if v_check=='OK':
        longtitude = DataDict['response']['result']['point']['x']
        latitude = DataDict['response']['result']['point']['y']
        sqlUpdate = "UPDATE pharmacy SET p_x = %s, p_y = %s WHERE p_id = %s"
        val = (float(latitude),float(longtitude),int(p_id))
        curs.execute(sqlUpdate,val)
        conn.commit()
    else : 
        continue
conn.close()
