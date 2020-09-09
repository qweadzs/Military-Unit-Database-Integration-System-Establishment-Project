-- 출타 삭제 프로시저
create or replace procedure delete_vacation(
    vv_sol vacation.v_sol%TYPE,
    result OUT VARCHAR2
)
is
begin
    delete from vacation
    where v_sol = vv_sol;
    commit;
    
    result := '출타가 삭제되었습니다.';
    
end;



-- 신병 등록 프로시저
create or replace procedure Enroll_soldat(
    vs_id soldat.s_id%TYPE,
    vs_name soldat.s_name%TYPE,
    vs_class soldat.s_class%TYPE,
    vs_sex soldat.s_sex%TYPE,
    vs_birth soldat.s_birth%TYPE,
    vs_pnum soldat.s_pnum%TYPE,
    vs_addr soldat.s_addr%TYPE,
    vs_co soldat.s_co%TYPE,
    vs_po soldat.s_po%TYPE,
    vs_duty soldat.s_duty%TYPE,
    vs_transfer soldat.s_transfer%TYPE,
   vs_discharge soldat.s_discharge%TYPE)
--    result OUT VARCHAR2
--)
is
begin
    insert into soldat(s_id,s_name,s_class,s_sex,s_birth,s_pnum,s_addr, s_co, s_po,s_duty,s_transfer,s_discharge)
    values(vs_id,vs_name,vs_class, vs_sex, vs_birth, vs_pnum, vs_addr, vs_co, vs_po, vs_duty, vs_transfer, vs_discharge);
    commit;
    
--    result := '신병 등록이 완료되었습니다.';
    
end;



-- 새로운 출타 등록 프로시저
create or replace procedure enroll_vacation(
    vv_id vacation.v_id%TYPE,
    vv_sol vacation.v_sol%TYPE,
    vv_kind vacation.v_kind%TYPE,
    vv_start vacation.v_start%TYPE,
    vv_end vacation.v_end%TYPE,
    result OUT VARCHAR2
)
is
begin
    insert into vacation
    values(vv_id, vv_sol, vv_kind, vv_start, vv_end);
    commit;
    
    result := '출타 등록이 완료되었습니다.';
    
end;




--새로운 장비 등록 프로시저
create or replace procedure in_equipment(
    veq_id equipment.eq_id%TYPE,
    veq_co_id equipment.eq_co_id%TYPE,
    veq_name equipment.eq_name%TYPE,
    veq_stock equipment.eq_stock%TYPE,
    veq_kind equipment.eq_kind%TYPE,
    result OUT VARCHAR2
)
is
begin
    insert into equipment(eq_id, eq_co_id, eq_name, eq_stock, eq_kind)
    values(veq_id, veq_co_id, veq_name, veq_stock, veq_kind);
    
    result := '장비 등록이 완료되었습니다.';
    
    commit;
end;




-- 군인 조회 프로시저
create or replace procedure sol_infor(v_sid IN char, result OUT VARCHAR2)
is
    sid soldat.s_id%type;
    sname soldat.s_name%type;
    sclass soldat.s_class%type;
    ssex soldat.s_sex%type;
    sbirth soldat.s_birth%type;
    spnum soldat.s_pnum%type;
    saddr soldat.s_addr%type;
    sco company.co_name%type;
    spo position.p_name%type;
    sduty soldat.s_duty%type;
    stransfer soldat.s_transfer%type;
    sdischarge soldat.s_discharge%type;
    sphysic physical.ps_class%type;
    smedical medical.m_name%type;
begin
    select s_id,s_name,s_class, s_sex, s_birth, s_pnum, s_addr, c.co_name, p.p_name, s_duty, s_transfer, s_discharge, y.ps_class, m.m_name
    into sid,sname,sclass, ssex, sbirth, spnum, saddr, sco, spo, sduty, stransfer, sdischarge, sphysic, smedical
    from soldat s, company c, position p, medical m, physical y
    where v_sid = s_id and c.co_id = s.s_co and s.s_po = p.p_id and m.m_docunum = s.s_medical_docunum and y.ps_docunum = s.s_physic_docunum;

    result := '군번 : '||sid||CHR(13)||CHR(10)||
                '이름 : '||sname||CHR(13)||CHR(10)||
                '계급 : '||sclass||CHR(13)||CHR(10)||
                '성별 : '||ssex||CHR(13)||CHR(10)||
                '생년월일 : '||sbirth||CHR(13)||CHR(10)||
                'H.P : '||spnum||CHR(13)||CHR(10)||
                '주소 : '||saddr||CHR(13)||CHR(10)||
                '부대이름 : '||sco||CHR(13)||CHR(10)||
                '보직 : '||spo||CHR(13)||CHR(10)||
                '의무구분 : '||sduty||CHR(13)||CHR(10)||
                '전입일 : '||stransfer||CHR(13)||CHR(10)||
                '전역일 : '||sdischarge||CHR(13)||CHR(10)||
                '체력등급 : '||sphysic||CHR(13)||CHR(10)||
                '부상 및 질환 : '||smedical||CHR(13)||CHR(10);
end;


-- 군번, 이름으로 로그인 프로시저
create or replace procedure soldier_login(vs_id IN CHAR,vs_name IN VARCHAR2, result OUT VARCHAR2)
IS
    ss_id   soldat.s_id%TYPE;
    ss_name soldat.s_name%TYPE;

begin
    select s_id, s_name
    into ss_id, ss_name
    from soldat
    where s_id = vs_id and s_name = vs_name;

    result := '로그인에 성공하였습니다.';

EXCEPTION
    when no_data_found then
    result := '군번 or 이름이 일치하지 않습니다.';
end;


-- 장비 재고 변경 프로시저
create or replace procedure update_equipment(
    veq_id  equipment.eq_id%TYPE,
    veq_stock equipment.eq_stock%TYPE,
    result OUT VARCHAR2
)
is
    user_define_count_error EXCEPTION;
begin
    if veq_stock < 0 then
        RAISE user_define_count_error;
    end if;

    update equipment
    set eq_stock = veq_stock
    where eq_id = veq_id;
    commit;
    
    result := veq_id||'의 재고가 변경되었습니다.';

    EXCEPTION
    WHEN user_define_count_error then
        RAISE_APPLICATION_ERROR(-20001, '잘못된 숫자 입력입니다.');
end;



-- 출타 출발일 변경 프로시저
create or replace procedure update_vacation_start(
    vv_sol vacation.v_sol%TYPE,
    vv_start vacation.v_start%TYPE,
    result OUT VARCHAR2
)
is
begin
    update vacation
    set v_start = vv_start
    where v_sol = vv_sol;
    
    result := '출타 출발일이 변경되었습니다.';
end;




-- 출타 복귀일 변경 프로시저
create or replace procedure update_vacation_end(
    vv_sol vacation.v_sol%TYPE,
    vv_end vacation.v_end%TYPE,
    result OUT VARCHAR2
)
is
begin
    update vacation
    set v_end = vv_end
    where v_sol = vv_sol;
    
    result := '출타 복귀일이 변경되었습니다.';
end;





-- 출타 종류 변경 프로시저
create or replace procedure update_vacation_kind(
    vv_sol vacation.v_sol%TYPE,
    vv_kind vacation.v_kind%TYPE,
    result OUT VARCHAR2
)
is
begin
    update vacation
    set v_kind = vv_kind
    where v_sol = vv_sol;
    
    result := '출타 종류 변경이 완료되었습니다.';
end;




-- 출타 조회 프로시저
create or replace procedure vac_infor(v_sid IN char, result OUT VARCHAR2)
is 
    sname soldat.s_name%type;
    vkind vacation.v_kind%type;
    vstart vacation.v_start%type;
    vend vacation.v_end%type;
begin
    select s.s_name, v.v_kind, v.v_start, v.v_end
    into sname, vkind, vstart, vend
    from vacation v, soldat s
    where v_sid = s.s_id and s.s_id = v.v_sol;

    result := sname||'님의 다음출타는 ' ||vkind||'이며 기간은 '||vstart||' ~ '||vend||'입니다.';
end;



--장비 조회 프로시저
create or replace procedure equip_infor(v_eqid IN char, result OUT VARCHAR2)
is 
    eqid equipment.eq_id%type;
    eqcoid equipment.eq_co_id%type;
    eqname equipment.eq_name%type;
    eqstock equipment.eq_stock%type;
    eqkind equipment.eq_kind%type;
begin
    select *
    into eqid,eqcoid,eqname,eqstock,eqkind
    from equipment
    where v_eqid = eq_id;

    result := '장비번호 : '||eqid||CHR(13)||CHR(10)||
        '부대번호 : '||eqcoid||CHR(13)||CHR(10)||
        '장비명 : '||eqname||CHR(13)||CHR(10)||
        '재고 : '||eqstock||CHR(13)||CHR(10)||
        '종류 : '||eqkind||CHR(13)||CHR(10);
end;


-- 보호자 정보 조회 프로시저
create or replace procedure family_infor(vf_sol family.f_sol%TYPE, result OUT VARCHAR2)
is
    vs_name soldat.s_name%TYPE;
    vf_name family.f_name%TYPE;
    vf_pnum family.f_pnum%TYPE;
    vf_rel family.f_rel%TYPE;
    
begin
    select s.s_name, f.f_name, f.f_pnum, f.f_rel
    into vs_name, vf_name, vf_pnum, vf_rel
    from family f, soldat s
    where s.s_id = f.f_sol and f.f_sol = vf_sol;
    
    result := '<'||vs_name||'의 보호자 정보>'||CHR(13)||CHR(10)||
              vf_rel||'의 성함 : '||vf_name||CHR(13)||CHR(10)||
              '보호자 H.P : '||vf_pnum;
end;




--근무표 조회 프로시저
create or replace procedure duty_infor(v_sid IN char, vd_date duty.d_date%TYPE , result OUT VARCHAR2)
is 
    sname soldat.s_name%type;
    dname duty.d_name%type;
    ddate duty.d_date%type;
    dworktime duty.d_worktime%type;
    daddr duty.d_addr%type;
begin
    select s.s_name, d.d_name, d.d_date, d_worktime, d_addr
    into sname, dname, ddate, dworktime, daddr
    from duty d, soldat s
    where v_sid = s.s_id and s.s_id = d.d_sol and d_date = vd_date;

    result := sname||'님은 ' ||dname||'이며 근무날짜는 '||ddate||' 근무시간은 '||dworktime||' 위치는 '||daddr||'입니다.';
end;


--근무지 추가 프로시저
create or replace procedure duty_add(v_sid IN char, v_dname varchar2, v_ddate date, 
                                        v_dworktime varchar2, v_daddr varchar2, result OUT VARCHAR2)
is
begin
    insert into duty
    values(v_dname, v_sid, v_ddate, v_dworktime, v_daddr);
    result := '근무표가 추가되었습니다.';
end;



-- 군번으로 체력등급 상세 정보 조회
create or replace procedure physical_infor(vs_id soldat.s_id%TYPE, result OUT VARCHAR2)
is
    vs_name soldat.s_name%TYPE;
    vps_pu physical.ps_pu%TYPE;
    vps_su physical.ps_su%TYPE;
    vps_run physical.ps_run%TYPE;
    vps_fire physical.ps_fire%TYPE;
    vps_class physical.ps_class%TYPE;
    
begin
    select s.s_name, p.ps_pu, p.ps_su, p.ps_run, p.ps_fire, p.ps_class
    into vs_name, vps_pu, vps_su, vps_run, vps_fire, vps_class
    from soldat s, physical p
    where p.ps_docunum = s.s_physic_docunum and s.s_id = vs_id;
    
    result := vs_name||'의 정보'||CHR(13)||CHR(10)||
              '체력등급 : '||vps_class||CHR(13)||CHR(10)||
              '팔굽혀펴기 : '||vps_pu||CHR(13)||CHR(10)||
              '윗몸일의키기 : '||vps_su||CHR(13)||CHR(10)||
              '3km 뜀걸음(분) : '||vps_run||CHR(13)||CHR(10)||
              '사격(20발 만점) : '||vps_fire||CHR(13)||CHR(10);
    
end;



--계급 및 월급조회
create or replace procedure class_infor(v_cname IN char, result OUT VARCHAR2)
is 
    sname soldat.s_name%type;
    cname class.c_name%type;
    csal class.c_sal%type;
begin
    select s.s_name, c.c_name, c.c_sal
    into sname, cname, csal
    from class c, soldat s
    where v_cname = c.c_name;

    result := sname||'님의 계급은 ' ||cname||'이며 월급은 '||csal||'입니다.';
end;




-- 체력 테이블에 데이터 삽입 후 군인 테이블의 문서번호 수정
create or replace procedure insert_physical(
    vps_docunum physical.ps_docunum%TYPE,
    vps_pu physical.ps_pu%TYPE,
    vps_su physical.ps_su%TYPE,
    vps_run physical.ps_run%TYPE,
    vps_fire physical.ps_fire%TYPE,
    result OUT VARCHAR2)
is
    vps_class physical.ps_class%TYPE;
begin
    if vps_pu >= 78 and vps_su >= 70 and vps_run >= 12 and vps_fire >= 18 THEN
        vps_class := '특급';
    elsif vps_pu >= 64 and vps_su >= 60 and vps_run >= 13 and vps_fire >= 16 THEN
        vps_class := '1급';
    elsif vps_pu >= 52 and vps_su >= 50 and vps_run >= 14 and vps_fire >= 14 THEN
        vps_class := '2급';
    elsif vps_pu >= 44 and vps_su >= 40 and vps_run >= 15 and vps_fire >= 10 THEN
        vps_class := '3급';
    else
        vps_class := '불합격';
    end if;

    insert into physical
    values(vps_docunum, vps_class, vps_pu, vps_su, vps_run, vps_fire);
    commit;
    

    result := '체력 문서번호가 등록되었습니다.';
end;



-- 군번으로 부상정보 확인
create or replace procedure medical_infor(v_sid char, result OUT VARCHAR2)
is
    sname soldat.s_name%TYPE;
    mname medical.m_name%type;
    mdate medical.m_date%type;
    mcon medical.m_con%type;
    
begin
    select s.s_name, m.m_name, m.m_date, m.m_con
    into sname, mname, mdate, mcon
    from soldat s, medical m
    where m.m_docunum = s.s_medical_docunum and v_sid = s.s_id;
    
    result := sname||'님의 부상은 ' ||mname||'이며 '||mdate||'부터 시작됬으며 치료방법은 '||mcon||'입니다.';
    
end;



-- 의료 테이블 데이터 삽입 후 군인 테이블의 문서번호 수정
create or replace procedure medical_add(v_mdocunum number, v_mname varchar2, v_mdate date, v_mcon varchar2,result OUT VARCHAR2)
is
    
begin

    insert into medical
    values(v_mdocunum, v_mname, v_mdate, v_mcon);
    
    commit;    

    result := '의료 정보가 등록되었습니다.';
end;




-- 복지 삭제 프로시저
create or replace procedure delete_benefit(vb_name benefit.b_name%TYPE, result OUT VARCHAR2)
is
begin
    delete from benefit
    where b_name = vb_name;
    commit;
    result := '복지가 삭제되었습니다.';
end;



-- 복지 추가 프로시저
create or replace procedure insert_benefit(
    vb_name benefit.b_name%TYPE,
    vb_class benefit.b_class%TYPE,
    vb_con benefit.b_con%TYPE,
    result OUT VARCHAR2
)
is
begin
    insert into benefit
    values(vb_name, vb_class, vb_con);
    commit;
    result := '새로운 복지가 등록되었습니다.';
end;





-- 훈련 삭제 프로시저
create or replace procedure delete_train(
    vt_name train.t_name%TYPE,
    vt_co train.t_co%TYPE,
    result OUT VARCHAR2
)
is
begin
    delete from train
    where t_name = vt_name and t_co = vt_co;
    
    result := '훈련이 삭제되었습니다.';

end;


--훈련 삽입 프로시저
create or replace procedure insert_train(
    vt_name train.t_name%TYPE,
    vt_co train.t_co%TYPE,
    vt_start train.t_start%TYPE,
    vt_end train.t_end%TYPE,
    vt_addr train.t_addr%TYPE,
    result OUT VARCHAR2
)
is
begin
    insert into train
    values(vt_name, vt_co, vt_start, vt_end, vt_addr);
    
    result := '새로운 훈련이 삽입되었습니다.';

end;




-- oil 등록 프로시저 (잔고량 +)
create or replace procedure oil_add(v_oildate date, v_oiltrader char, v_oilrefer varchar2,
                                    v_oilvolume number, v_oilkind varchar2)
is
    oilbalance oil.oil_balance%type;

begin
    select oil_balance
    into oilbalance
    from oil
    where rownum = 1 and oil_kind = v_oilkind
    order by oil_date desc;

    oilbalance := oilbalance + v_oilvolume;

    insert into oil
    values(v_oildate, v_oiltrader, v_oilrefer, v_oilvolume, oilbalance, v_oilkind);
    commit;

end;



-- oil 등록 프로시저 (잔고량 -)
create or replace procedure oil_minus(v_oildate date, v_oiltrader char, v_oilrefer varchar2,
                                    v_oilvolume number, v_oilkind varchar2)
is
    oilbalance oil.oil_balance%type;

begin
    select oil_balance
    into oilbalance
    from oil
    where rownum = 1 and oil_kind = v_oilkind
    order by oil_date desc;

    oilbalance := oilbalance - v_oilvolume;

    insert into oil
    values(v_oildate, v_oiltrader, v_oilrefer, v_oilvolume, oilbalance, v_oilkind);
    commit;

end;



-- 시설, 시설책임자 등록 프로시저
create or replace procedure add_facility(
    vfac_name facility.fac_name%TYPE,
    vfac_manager facility.fac_manager%TYPE,
    result OUT VARCHAR2
)
is

begin
    insert into facility
    values(vfac_name, vfac_manager);
    commit;
    result := '새로운 시설, 시설책임자가 등록되었습니다.';
end;




-- 시설, 시설책임자 삭제 프로시저
create or replace procedure delete_facility(
    vfac_manager facility.fac_manager%TYPE,
    result OUT VARCHAR2
)
is

begin
    delete from facility
    where fac_manager = vfac_manager;
    commit;
    result := '시설, 시설책임자가 삭제되었습니다.';
end;



--시설 정보확인
create or replace procedure fac_infor(v_sid IN char, result OUT VARCHAR2)
is 
    sname soldat.s_name%type;
    fname soldat.s_name%type;
begin
    select s.s_name, f.fac_name
    into sname, fname
    from facility f, soldat s
    where v_sid = s.s_id and s.s_id = f.fac_manager;

    result := sname||'님의 담당구역은 '||fname||'입니다.';
end;



--유류 거래 더하기
create or replace procedure oil_add(v_oildate varchar2, v_oiltrader char, v_oilrefer varchar2,
                                    v_oilvolume number, v_oilkind varchar2)
is
    oilbalance oil.oil_balance%type;

begin
    select oil_balance
    into oilbalance
    from oil
    where rownum = 1 and oil_kind = v_oilkind
    order by oil_date desc;

    oilbalance := oilbalance + v_oilvolume;

    insert into oil
    values(v_oildate, v_oiltrader, v_oilrefer, v_oilvolume, oilbalance, v_oilkind);
    commit;

end;




--유류 거래 빼기
create or replace procedure oil_minus(v_oildate varchar2, v_oiltrader char, v_oilrefer varchar2,
                                    v_oilvolume number, v_oilkind varchar2)
is
    oilbalance oil.oil_balance%type;

begin
    select oil_balance
    into oilbalance
    from oil
    where rownum = 1 and oil_kind = v_oilkind
    order by oil_date desc;

    oilbalance := oilbalance - v_oilvolume;

    insert into oil
    values(v_oildate, v_oiltrader, v_oilrefer, v_oilvolume, oilbalance, v_oilkind);
    commit;

end;