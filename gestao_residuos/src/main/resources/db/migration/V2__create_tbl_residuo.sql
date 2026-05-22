CREATE SEQUENCE seq_residuo
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_residuo (
                             id NUMBER PRIMARY KEY,
                             nome VARCHAR2(100) NOT NULL,
                             tipo VARCHAR2(100) NOT NULL,
                             peso NUMBER(10,2) NOT NULL
);

