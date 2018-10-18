USE [ProjetQCM_DB]
GO

ALTER TABLE [dbo].[epreuve] DROP CONSTRAINT [epreuve_utilisateur_fk]
GO


ALTER TABLE EPREUVE
    ADD CONSTRAINT epreuve_utilisateur_fk FOREIGN KEY ( idutilisateur )
        REFERENCES utilisateur ( idutilisateur )
ON DELETE CASCADE
    ON UPDATE no action 


USE [ProjetQCM_DB]
GO

ALTER TABLE [dbo].[question_tirage] DROP CONSTRAINT [question_tirage_epreuve_fk]
GO

ALTER TABLE QUESTION_TIRAGE
    ADD CONSTRAINT question_tirage_epreuve_fk FOREIGN KEY ( idepreuve )
        REFERENCES epreuve ( idepreuve )
ON DELETE CASCADE
    ON UPDATE no action 
	
	
USE [ProjetQCM_DB]
GO

ALTER TABLE [dbo].[utilisateur] DROP CONSTRAINT [utilisateur_promotion_fk]
GO

ALTER TABLE UTILISATEUR
    ADD CONSTRAINT utilisateur_promotion_fk FOREIGN KEY ( codepromo )
        REFERENCES promotion ( codepromo )
ON DELETE CASCADE 
    ON UPDATE no action
	

USE [ProjetQCM_DB]
GO

ALTER TABLE [dbo].[section_test] DROP CONSTRAINT [section_test_test_fk]
GO

ALTER TABLE SECTION_TEST
    ADD CONSTRAINT section_test_test_fk FOREIGN KEY ( idtest )
        REFERENCES test ( idtest )
ON DELETE CASCADE
    ON UPDATE no action