-- Généré par Oracle SQL Developer Data Modeler 18.1.0.082.1035
--   à :        2018-10-09 09:58:26 CEST
--   site :      SQL Server 2012
--   type :      SQL Server 2012



CREATE TABLE epreuve 
    ( idepreuve bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     dateDebutValidite DATETIME2 NOT NULL , 
     dateFinValidite DATETIME2 NOT NULL , 
     tempsEcoule INTEGER , 
     etat NVARCHAR (10) NOT NULL DEFAULT 'EA' , 
     note_obtenu FLOAT , 
     niveau_obtenu NVARCHAR (50) , 
     idTest BIGINT NOT NULL , 
     idUtilisateur BIGINT NOT NULL  
    )
    ON "default"
 


ALTER TABLE EPREUVE 
    ADD 
    CHECK ( etat IN ('EA', 'EC', 'T') ) 



ALTER TABLE epreuve add check(niveau_obtenu IN(
    'A','ECA','NA'
) ) 

ALTER TABLE EPREUVE ADD constraint epreuve_pk PRIMARY KEY CLUSTERED (idEpreuve)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE profil 
    ( codeprofil bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     libelle NVARCHAR (50) NOT NULL 
    )
    ON "default" 

ALTER TABLE PROFIL ADD constraint profil_pk PRIMARY KEY CLUSTERED (codeProfil)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE promotion 
    ( codepromo bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     libelle NVARCHAR (50) NOT NULL 
    )
    ON "default" 

ALTER TABLE PROMOTION ADD constraint promotion_pk PRIMARY KEY CLUSTERED (codePromo)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE proposition 
    ( idproposition bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     enonce NTEXT NOT NULL , 
     estBonne BIT NOT NULL , 
     idQuestion BIGINT NOT NULL 
    )
    ON "default" 

ALTER TABLE PROPOSITION ADD constraint proposition_pk PRIMARY KEY CLUSTERED (idProposition)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE question 
    ( idquestion bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     enonce NTEXT NOT NULL , 
     media IMAGE , 
     points INTEGER NOT NULL DEFAULT 1 , 
     idTheme BIGINT NOT NULL 
    )
    ON "default" 

ALTER TABLE QUESTION ADD constraint question_pk PRIMARY KEY CLUSTERED (idQuestion)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE question_tirage (
    estmarquee   bit NOT NULL,
    numordre     INTEGER NOT NULL,
    idepreuve    bigint NOT NULL,
    idquestion   bigint NOT NULL
)
    ON "default" 

CREATE TABLE section_test (
    nbquestionsatirer   INTEGER NOT NULL,
    idtest              bigint NOT NULL,
    idtheme             bigint NOT NULL
)
    ON "default" 

CREATE TABLE test 
    ( idtest bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     libelle NVARCHAR (50) NOT NULL , 
     description NTEXT NOT NULL , 
     duree INTEGER NOT NULL , 
     seuil_haut INTEGER NOT NULL , 
     seuil_bas INTEGER NOT NULL 
    )
    ON "default" 

ALTER TABLE TEST ADD constraint test_pk PRIMARY KEY CLUSTERED (idTest)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE theme 
    ( idtheme bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     libelle NVARCHAR (50) NOT NULL 
    )
    ON "default" 

ALTER TABLE THEME ADD constraint theme_pk PRIMARY KEY CLUSTERED (idTheme)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 

CREATE TABLE utilisateur 
    ( idutilisateur bigint NOT NULL IDENTITY NOT FOR REPLICATION , 
     nom NVARCHAR (50) NOT NULL , 
     prenom NVARCHAR (50) NOT NULL , 
     email NVARCHAR (50) NOT NULL , 
     password NVARCHAR (50) NOT NULL , 
     isCandidat BIT NOT NULL , 
     isCollaborateur BIT NOT NULL , 
     codeProfil BIGINT NOT NULL,
codepromo bigint 
    )
    ON "default" 

ALTER TABLE UTILISATEUR ADD constraint utilisateur_pk PRIMARY KEY CLUSTERED (idUtilisateur)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
     ON "default" 
    
    ALTER TABLE utilisateur add constraint utilisateur_un unique nonclustered(email)
     ON "default" 

ALTER TABLE EPREUVE
    ADD CONSTRAINT epreuve_test_fk FOREIGN KEY ( idtest )
        REFERENCES test ( idtest )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE EPREUVE
    ADD CONSTRAINT epreuve_utilisateur_fk FOREIGN KEY ( idutilisateur )
        REFERENCES utilisateur ( idutilisateur )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE PROPOSITION
    ADD CONSTRAINT proposition_question_fk FOREIGN KEY ( idquestion )
        REFERENCES question ( idquestion )
ON DELETE CASCADE 
    ON UPDATE no action 

ALTER TABLE QUESTION
    ADD CONSTRAINT question_theme_fk FOREIGN KEY ( idtheme )
        REFERENCES theme ( idtheme )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE QUESTION_TIRAGE
    ADD CONSTRAINT question_tirage_epreuve_fk FOREIGN KEY ( idepreuve )
        REFERENCES epreuve ( idepreuve )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE QUESTION_TIRAGE
    ADD CONSTRAINT question_tirage_question_fk FOREIGN KEY ( idquestion )
        REFERENCES question ( idquestion )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE SECTION_TEST
    ADD CONSTRAINT section_test_test_fk FOREIGN KEY ( idtest )
        REFERENCES test ( idtest )
ON DELETE CASCADE
    ON UPDATE no action 

ALTER TABLE SECTION_TEST
    ADD CONSTRAINT section_test_theme_fk FOREIGN KEY ( idtheme )
        REFERENCES theme ( idtheme )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE UTILISATEUR
    ADD CONSTRAINT utilisateur_profil_fk FOREIGN KEY ( codeprofil )
        REFERENCES profil ( codeprofil )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE UTILISATEUR
    ADD CONSTRAINT utilisateur_promotion_fk FOREIGN KEY ( codepromo )
        REFERENCES promotion ( codepromo )
ON DELETE CASCADE 
    ON UPDATE no action 



-- Rapport récapitulatif d'Oracle SQL Developer Data Modeler : 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             21
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE DATABASE                          0
-- CREATE DEFAULT                           0
-- CREATE INDEX ON VIEW                     0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE ROLE                              0
-- CREATE RULE                              0
-- CREATE SCHEMA                            0
-- CREATE SEQUENCE                          0
-- CREATE PARTITION FUNCTION                0
-- CREATE PARTITION SCHEME                  0
-- 
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
