USE [ProjetQCM_DB]
GO

INSERT INTO [dbo].[profil]
           ([libelle])
     VALUES
           ('Profil1'),
		   ('Profil2')
GO

INSERT INTO [dbo].[promotion]
           ([libelle])
     VALUES
           ('2018/2019'),
		   ('2019/2020'),
		   ('2021/2022')
GO

INSERT INTO [dbo].[utilisateur]
           ([nom]
           ,[prenom]
           ,[email]
           ,[password]
           ,[isCandidat]
           ,[isCollaborateur]
           ,[codeProfil]
           ,[codepromo])
     VALUES
		   ('MICHEL'
           ,'Arthur'
           ,'contact@arthurmichel.com'
           ,'password'
           ,0
           ,1
           ,2
           ,NULL),
		   ('GOODENOUGH'
           ,'David'
           ,'contact@davidgoodenough.com'
           ,'password'
           ,1
           ,0
           ,2
           ,1),
		   ('ZIDANE'
           ,'Zinedine'
           ,'contact@zinedinezidane.com'
           ,'password'
           ,0
           ,1
           ,1
           ,NULL),
           ('GRIEZMANN'
           ,'Antoine'
           ,'contact@antoinegriezmann.com'
           ,'password'
           ,1
           ,0
           ,1
           ,3),
		   ('DESCHAMPS'
           ,'Didier'
           ,'contact@didierdeschamps.com'
           ,'password'
           ,1
           ,0
           ,1
           ,2),
		   ('SPIELBERG'
           ,'Steven'
           ,'contact@stevenspielberg.com'
           ,'password'
           ,0
           ,1
           ,1
           ,2),
		   ('CERDERA'
           ,'Thomas'
           ,'contact@thomascerdera.com'
           ,'password'
           ,1
           ,0
           ,1
           ,3)
GO

INSERT INTO [dbo].[test]
           ([libelle]
           ,[description]
           ,[duree]
           ,[seuil_haut]
           ,[seuil_bas])
     VALUES
           ('Analyse et conception'
           ,'Test des compétences en analyse et conception.'
           ,120
           ,14
           ,10),
		   ('PHP et Symfony'
           ,'Test des compétences de développement en PHP et Symfony.'
           ,90
           ,14
           ,10),
		   ('Procédures stockées en PL-SQL'
           ,'Test des compétences PL-SQL.'
           ,90
           ,14
           ,10)
GO

INSERT INTO [dbo].[epreuve]
           ([dateDebutValidite]
           ,[dateFinValidite]
           ,[tempsEcoule]
           ,[etat]
           ,[note_obtenu]
           ,[niveau_obtenu]
           ,[idTest]
           ,[idUtilisateur])
     VALUES
           ('02/06/2018'
           ,'05/12/2019'
           ,NULL
           ,'EA'
           ,NULL
           ,NULL
           ,1
           ,3),
		   ('04/10/2015'
           ,'26/12/2015'
           ,110
           ,'T'
           ,13
           ,'ECA'
           ,1
           ,1),
		   ('24/02/2018'
           ,'22/09/2019'
           ,56
           ,'T'
           ,8
           ,'A'
           ,2
           ,14)
GO

INSERT INTO [dbo].[theme]
           ([libelle])
     VALUES
           ('Comprehension'),
		   ('Logique'),
		   ('Connaissances')
GO