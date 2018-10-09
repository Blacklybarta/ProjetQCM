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


