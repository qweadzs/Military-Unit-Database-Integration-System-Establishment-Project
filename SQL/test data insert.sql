insert into class values('이등병',408000);
insert into class values('일병',441000);
insert into class values('상병',488000);
insert into class values('병장',540000);
insert into class values('하사',1555000);
insert into class values('중사',1710000);
insert into class values('상사',2120000);

insert into position values('101','보병');
insert into position values('201','포병');
insert into position values('301','운전병');
insert into position values('401','통신병');
insert into position values('501','방공운용병');
insert into position values('601','헌병');

//부대 테이블에서 부대장 속성 지워야함
insert into company values('2918','2사단','양구', '양구', '대', '5000');
insert into company values('1816','3사단','철원', '철원', '대', '7000');
insert into company values('1817','항공작전사령부','경기도 하남', '서울', '중', '3000');
insert into company values('1803','헌병단','서울', '서울', '소', '1000');

insert into physical values(1,'1급',65,60,13,16);
insert into physical values(2,'특급',105,84,12,20);
insert into physical values(3,'2급',54,67,14,14);
insert into physical values(4,'3급',45,55,15,11);


insert into medical values(1,'부상X', null, 'X');
insert into medical values(2,'무릎 인대', to_date('2019/05/05','yyyy/mm/dd'), '일동병원 이송');
insert into medical values(3,'턱근육 뭉침', to_date('2019/04/30','yyyy/mm/dd'), '얼음 마사지');
insert into medical values(4,'부상X', null, 'X');

insert into train values('한미연합작전','2918','2019/07/03','2019/07/08','양구');
insert into train values('KCTC','1816','2019/08/14','2019/08/18','연천');
insert into train values('RCT','1816','2019/09/01','2019/09/07','철원');
insert into train values('방공전술훈련','1817','2019/11/11','2019/11/12','춘천');
insert into train values('유격','1803','2019/06/06','2019/06/12','서울');

insert into soldat values('201512918','김민호','상병','남자', '95/08/22','010-5104-0882','서울','2918','101','병사','2018/08/02','2020/05/01',1,1);
insert into soldat values('201511816','박지훈','병장','남자', '95/08/03','010-8703-2174','서울','1816','301','병사','2018/05/10','2020/02/09',2,2);
insert into soldat values('201511817','박현우','이등병','남자', '95/09/12','010-9653-5847','서울','1817','501','병사','2018/06/28','2020/03/27',3,3);
insert into soldat values('201511803','김태현','일병','남자', '94/07/20','010-2033-8327','용인','1803','601','병사','2018/08/08','2020/05/07',4,4);

insert into family values('201512918','김길동', '010-2424-1515', '아버지');
insert into family values('201511816','박흥부', '010-2468-1357', '아버지');
insert into family values('201511817','이춘향', '010-8513-5600', '어머니');
insert into family values('201511803','김몽룡', '010-7381-6191', '아버지');

insert into vacation values('19-1001','201511803','외박', '2019/06/14','2019/06/15');
insert into vacation values('19-1002','201511816','휴가', '2019/07/11','2019/07/25');
insert into vacation values('19-1003','201511817','휴가', '2019/06/28','2019/07/05');
insert into vacation values('19-1004','201512918','외출', '2019/06/21','2019/06/21');

insert into benefit values('마음의 전화', '이등병', '신병위로휴가 + 1박2일');
insert into benefit values('진정한 군인', '병장', 'PX물품 20% 할인');


insert into equipment values('KA-101', '1816', '레토나', 2, '중장비');
insert into equipment values('K-1212', '2918', 'K2 소총', 20, '화기');
insert into equipment values('K-1111', '2918', 'K1 소총', 15, '화기');
insert into equipment values('KM167', '1817', '발칸포', 6, '중장비');
insert into equipment values('KA-501', '1803', '대형 군용트럭', 10, '중장비');

alter table oil drop constraint oil_pk;
alter table oil drop column oil_time;
alter table oil
add constraint oil_pk PRIMARY KEY(oil_date, oil_trader);
insert into oil values(to_date('2019/06/06 11:50:00','yyyy/mm/dd hh24:mi:ss'), '201511816', '군용차주유', 20, 210, '경유');
insert into oil values('2019/05/30','201511817', '군용차 주유', 30, 300, '휘발유');
insert into oil values('2019/05/30','201511803', '난방 주유', 25, 125, '등유');

alter table duty modify(d_worktime VARCHAR2(20));
insert into duty values('경계근무', '201512918','2019/06/05','13:00 ~ 15:00' ,'초소');
insert into duty values('경계근무', '201511816','2019/06/05', '09:00 ~ 11:00','위병소');
insert into duty values('CCTV근무', '201511817','2019/06/06', '16:00 ~ 18:00' ,'지휘통제실');
insert into duty values('FDC근무', '201511803','2019/06/06' , '01:00 ~ 03:00','지휘통제실');


insert into facility values('풋살장', '201511816');
insert into facility values('체육관', '201511817');
insert into facility values('노래방', '201511803');
insert into facility values('흡연장', '201512918');


commit;

