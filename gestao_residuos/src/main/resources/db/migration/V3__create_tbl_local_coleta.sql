CREATE SEQUENCE seq_local_coleta
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_local_coleta (
                                  id NUMBER PRIMARY KEY,
                                  nome VARCHAR2(100) NOT NULL,
                                  endereco VARCHAR2(200) NOT NULL,
                                  capacidade_maxima NUMBER(10,2)
);

