USE [master]
GO
/****** Object:  Database [ProjectPRJ321]    Script Date: 7/13/2020 7:52:14 PM ******/
CREATE DATABASE [ProjectPRJ321] 
GO
USE [ProjectPRJ321]
GO
/****** Object:  Table [dbo].[tblBooks]    Script Date: 7/13/2020 7:52:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBooks](
	[bookID] [nvarchar](50) NOT NULL,
	[title] [nvarchar](50) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[author] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblBooks] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 7/13/2020 7:52:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[getDate] [date] NULL,
	[returnDate] [date] NULL,
	[total] [int] NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 7/13/2020 7:52:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[bookID] [nvarchar](50) NOT NULL,
	[orderID] [nvarchar](50) NOT NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC,
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 7/13/2020 7:52:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [nvarchar](10) NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 7/13/2020 7:52:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[UserID] [nvarchar](50) NOT NULL,
	[FullName] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
	[RoleID] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'aaaaaaa', N'aaaaaaaaaa', 22222, 22222222, N'aaaaaa', 0)
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'BBN', N'banana2', 2000, 12, N'notFPT    ', 1)
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'ddd', N'banana2', 2000, 12, N'notFPT', 0)
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'ff', N'banana2', 2000, 12, N'fff', 1)
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'PRJ311', N'java destop', 5000, 5, N'FPT', 0)
INSERT [dbo].[tblBooks] ([bookID], [title], [price], [quantity], [author], [status]) VALUES (N'PRJ321', N'java web', 1000, 6, N'FPT', 1)
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'ad', N'administrator')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (N'user', N'user')
INSERT [dbo].[tblUsers] ([UserID], [FullName], [Password], [RoleID]) VALUES (N'admin', N'administrator', N'1', N'ad')
INSERT [dbo].[tblUsers] ([UserID], [FullName], [Password], [RoleID]) VALUES (N'user', N'user', N'2', N'user')
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([UserID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUsers]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblBooks] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBooks] ([bookID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblBooks]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles] FOREIGN KEY([RoleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles]
GO
USE [master]
GO
ALTER DATABASE [ProjectPRJ321] SET  READ_WRITE 
GO
