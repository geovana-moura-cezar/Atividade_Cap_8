CREATE SEQUENCE seq_descarte
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_descarte (
                              id NUMBER PRIMARY KEY,

                              data_descarte DATE NOT NULL,
                              quantidade NUMBER NOT NULL,
                              status VARCHAR2(50) NOT NULL,

                              residuo_id NUMBER NOT NULL,
                              local_coleta_id NUMBER NOT NULL,

                              CONSTRAINT fk_descarte_residuo
                                  FOREIGN KEY (residuo_id)
                                      REFERENCES tbl_residuo(id),

                              CONSTRAINT fk_descarte_local
                                  FOREIGN KEY (local_coleta_id)
                                      REFERENCES tbl_local_coleta(id)
);


