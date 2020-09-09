CREATE TABLE company
(
    co_id	    CHAR(20),
    co_name  VARCHAR2(50),
    co_addr    VARCHAR2(50),
    co_area     VARCHAR2(50),
    co_manager    CHAR(20),
    co_scale     VARCHAR2(50),
    co_capacity  VARCHAR2(50),
    CONSTRAINT co_pk PRIMARY KEY (co_id)
);


CREATE TABLE soldat
(
    s_id	   CHAR(20),
    s_name   VARCHAR2(50),
    s_class    VARCHAR2(50),
    s_sex    VARCHAR2(20),
    s_birth  DATE,
    s_pnum  CHAR(50),
    s_addr  VARCHAR2(50),
    s_co    CHAR(20),
    s_po    CHAR(20),
    s_duty  VARCHAR2(50),
    s_transfer DATE,
    s_discharge DATE,
    s_physic_docunum  NUMBER(4),
    s_medical_docunum  NUMBER(4),
    CONSTRAINT s_pk PRIMARY KEY (s_id),
    CONSTRAINT s_fk1 FOREIGN KEY (s_class) REFERENCES class(c_name),
    CONSTRAINT s_fk2 FOREIGN KEY (s_co) REFERENCES company(co_id),
    CONSTRAINT s_fk3 FOREIGN KEY (s_po) REFERENCES position(p_id),
    CONSTRAINT s_fk4 FOREIGN KEY (s_physic_docunum) REFERENCES physical(ps_docunum),
    CONSTRAINT s_fk5 FOREIGN KEY (s_medical_docunum) REFERENCES medical(m_docunum)
);


CREATE TABLE vacation
(
    v_id	   CHAR(20),
    v_sol      CHAR(20),
    v_kind     VARCHAR2(20),
    v_start    DATE,
    v_end      DATE,
    CONSTRAINT v_pk PRIMARY KEY (v_id),
    CONSTRAINT v_fk FOREIGN KEY (v_sol) REFERENCES soldat(s_id)
);


CREATE TABLE family
(
    f_sol	     CHAR(20),
    f_name	     VARCHAR2(20),
    f_pnum       CHAR(20),
    f_rel        VARCHAR2(20), 
    CONSTRAINT f_pk PRIMARY KEY (f_sol, f_name),
    CONSTRAINT f_fk FOREIGN KEY (f_sol) REFERENCES  soldat (s_id)
);


CREATE TABLE class
(
    c_name	   VARCHAR2(50),
    c_sal	   NUMBER(20),
    CONSTRAINT c_pk PRIMARY KEY (c_name)
 );


CREATE TABLE equipment
(
    eq_id      CHAR(20),
    eq_co_id    CHAR(20),
    eq_name      VARCHAR2(50),
    eq_stock    NUMBER(10),
    eq_kind     VARCHAR(50),
    CONSTRAINT eq_pk PRIMARY KEY (eq_id),
    CONSTRAINT eq_fk FOREIGN KEY (eq_co_id) REFERENCES company(co_id)
 );


CREATE TABLE position
(
    p_id	   CHAR(20),
    p_name	   VARCHAR2(50),
    CONSTRAINT p_pk PRIMARY KEY (p_id)
 );


CREATE TABLE facility
(
    fac_name	   VARCHAR2(50),
    fac_manager	   CHAR(20),
    CONSTRAINT fac_pk PRIMARY KEY (fac_name),
    CONSTRAINT fac_fk FOREIGN KEY (fac_manager) REFERENCES  soldat (s_id)
    
 );


CREATE TABLE benefit
(
    b_name	   VARCHAR2(50),
    b_class	   VARCHAR2(50),
    b_con      VARCHAR2(100),
    CONSTRAINT b_pk PRIMARY KEY (b_name),
    CONSTRAINT b_fk FOREIGN KEY (b_class) REFERENCES  class (c_name)
    
 );


CREATE TABLE medical
(
    m_docunum        NUMBER(4),
    m_name	   VARCHAR2(50),
    m_date     DATE,
    m_con      VARCHAR2(100),
    CONSTRAINT m_pk PRIMARY KEY (m_docunum)
);


CREATE TABLE train
(
    t_name	   VARCHAR2(50),
    t_co	   CHAR(20),
    t_start     DATE,
    t_end       DATE,
    t_addr      VARCHAR2(100),
    CONSTRAINT t_pk PRIMARY KEY (t_name, t_co),
    CONSTRAINT t_fk FOREIGN KEY (t_co) REFERENCES  company (co_id)
    
 );


CREATE TABLE duty
(
    d_name	   VARCHAR2(50),
    d_sol	   CHAR(20),
    d_date     DATE,
    d_worktime    DATE,
    d_addr      VARCHAR2(100),
    CONSTRAINT d_pk PRIMARY KEY (d_sol, d_date,d_worktime),
    CONSTRAINT d_fk FOREIGN KEY (d_sol) REFERENCES  soldat (s_id)
    
 );


CREATE TABLE physical
(
    ps_docunum NUMBER(4),
    ps_class   VARCHAR2(50),
    ps_pu	   NUMBER(5),
    ps_su      NUMBER(5),
    ps_run     NUMBER(5),
    ps_fire    NUMBER(5),
    CONSTRAINT ps_pk PRIMARY KEY (ps_docunum)
 );


CREATE TABLE oil
(
    oil_date   DATE,
    oil_trader  CHAR(20),
    oil_refer   VARCHAR2(50),
    oil_volume  NUMBER(10),
    oil_balance  NUMBER(10),
    oil_kind    VARCHAR2(50),
    CONSTRAINT oil_pk PRIMARY KEY (oil_date, oil_trader),
    CONSTRAINT oil_fk FOREIGN KEY (oil_trader) REFERENCES  soldat(s_id)
);

commit;
set linesize 100
Set Serveroutput on
