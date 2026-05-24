INSERT INTO TBL_ALERTA (
    ID,
    MENSAGEM,
    NIVEL,
    DATA_CRIACAO,
    LOCAL_COLETA_ID
) VALUES (
             SEQ_ALERTA.NEXTVAL,
             'Limite de descarte de plástico próximo do máximo',
             3,
             SYSDATE,
             NULL
         );

INSERT INTO TBL_ALERTA (
    ID,
    MENSAGEM,
    NIVEL,
    DATA_CRIACAO,
    LOCAL_COLETA_ID
) VALUES (
             SEQ_ALERTA.NEXTVAL,
             'Coleta de vidro atrasada',
             2,
             SYSDATE,
             NULL
         );

INSERT INTO TBL_ALERTA (
    ID,
    MENSAGEM,
    NIVEL,
    DATA_CRIACAO,
    LOCAL_COLETA_ID
) VALUES (
             SEQ_ALERTA.NEXTVAL,
             'Volume de resíduos orgânicos acima do esperado',
             1,
             SYSDATE,
             NULL
         );