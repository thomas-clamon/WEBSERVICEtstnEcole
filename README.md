CREATE TABLE [dbo].[Etudiants](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NOT NULL,
	[prenom] [varchar](50) NOT NULL,
	[classe] [varchar](50) NOT NULL,
	[datenaissance] [date] NOT NULL,
 CONSTRAINT [PK_Etudiants] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



---------


CREATE TABLE [dbo].[Matieres](
	[code] [varchar](10) NOT NULL,
	[description] [varchar](50) NOT NULL,
	[type] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Matieres] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

-------------

GO

CREATE TABLE [dbo].[Evaluations](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[id_etudiant] [int] NOT NULL,
	[id_matiere] [varchar](10) NOT NULL,
	[note] [int] NOT NULL,
	[date] [date] NOT NULL,
	[type] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Evaluations] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Evaluations]  WITH CHECK ADD  CONSTRAINT [FK_EvalEtudiant] FOREIGN KEY([id_etudiant])
REFERENCES [dbo].[Etudiants] ([ID])
GO

ALTER TABLE [dbo].[Evaluations] CHECK CONSTRAINT [FK_EvalEtudiant]
GO

ALTER TABLE [dbo].[Evaluations]  WITH CHECK ADD  CONSTRAINT [FK_EvalMatiere] FOREIGN KEY([id_matiere])
REFERENCES [dbo].[Matieres] ([code])
GO

ALTER TABLE [dbo].[Evaluations] CHECK CONSTRAINT [FK_EvalMatiere]
GO


