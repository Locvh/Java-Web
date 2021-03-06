USE [master]
GO
/****** Object:  Database [LunarNewYear]    Script Date: 2/9/2022 11:35:16 AM ******/
CREATE DATABASE [LunarNewYear]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LunarNewYear', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LunarNewYear.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LunarNewYear_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LunarNewYear_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LunarNewYear] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LunarNewYear].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LunarNewYear] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LunarNewYear] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LunarNewYear] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LunarNewYear] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LunarNewYear] SET ARITHABORT OFF 
GO
ALTER DATABASE [LunarNewYear] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LunarNewYear] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LunarNewYear] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LunarNewYear] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LunarNewYear] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LunarNewYear] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LunarNewYear] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LunarNewYear] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LunarNewYear] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LunarNewYear] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LunarNewYear] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LunarNewYear] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LunarNewYear] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LunarNewYear] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LunarNewYear] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LunarNewYear] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LunarNewYear] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LunarNewYear] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LunarNewYear] SET  MULTI_USER 
GO
ALTER DATABASE [LunarNewYear] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LunarNewYear] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LunarNewYear] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LunarNewYear] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LunarNewYear] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LunarNewYear] SET QUERY_STORE = OFF
GO
USE [LunarNewYear]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_detail]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_detail](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ProductId] [varchar](30) NULL,
	[OrderId] [varchar](30) NULL,
	[AmountOfMoney] [float] NULL,
	[TotalQuantity] [int] NULL,
	[ProductName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Order_detail] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderId] [varchar](30) NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[PhoneNumber] [varchar](15) NULL,
	[UserId] [int] NULL,
	[Address] [nvarchar](100) NULL,
	[TotalPayment] [float] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductId] [varchar](30) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[Description] [nvarchar](250) NULL,
	[Image] [nvarchar](250) NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[Status] [bit] NULL,
	[CategoryID] [int] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ProductId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profile]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profile](
	[ProfileID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[Sex] [nvarchar](5) NULL,
	[DOB] [date] NULL,
	[Email] [nvarchar](50) NULL,
	[PhoneNumber] [nvarchar](10) NULL,
	[UserId] [int] NULL,
	[Status] [bit] NULL,
	[Address] [nvarchar](100) NULL,
 CONSTRAINT [PK_Profile] PRIMARY KEY CLUSTERED 
(
	[ProfileID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 2/9/2022 11:35:16 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Role] [nvarchar](50) NOT NULL,
	[Status] [bit] NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (1, N'Hàng bán chạy')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (2, N'Hàng giá rẻ')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (3, N'Hàng giới hạn')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (4, N'Trở lại bình thường')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Order_detail] ON 

INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (8, N'NT-AVB-234', N'8520E', 12.256321907043457, 1, N'Chu khai xuan')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (9, N'NT-TLU-956', N'8520E', 611.5615234375, 5, N'Hop tra')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (10, N'NT-CKD-672', N'29D5D', 1.3322000503540039, 1, N'long den')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (11, N'NT-AVB-234', N'29D5D', 12.256321907043457, 1, N'Chu khai xuan')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (12, N'NT-LVO-634', N'29D5D', 140, 7, N'Muc')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (13, N'NT-OLP-065', N'29D5D', 70, 7, N'Ao moi')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (14, N'NT-CKD-672', N'5DA31', 1.3322000503540039, 1, N'long den')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (15, N'NT-LVO-634', N'5DA31', 20, 1, N'Muc')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (16, N'NT-TLU-956', N'5DA31', 122.31230926513672, 1, N'Hop tra')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (17, N'NT-OLP-065', N'5DA31', 10, 1, N'Ao moi')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (18, N'NT-OLD-132', N'066A6', 250, 5, N'Chau dao')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (19, N'NT-OLP-065', N'066A6', 10, 1, N'Ao moi')
INSERT [dbo].[Order_detail] ([Id], [ProductId], [OrderId], [AmountOfMoney], [TotalQuantity], [ProductName]) VALUES (20, N'NT-OLP-065', N'BBB5C', 10, 1, N'Ao moi')
SET IDENTITY_INSERT [dbo].[Order_detail] OFF
INSERT [dbo].[Orders] ([OrderId], [CustomerName], [PhoneNumber], [UserId], [Address], [TotalPayment], [Status]) VALUES (N'066A6', N'Anh Nam', N'12345', 4, N'sfdfsdfs', 260, 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerName], [PhoneNumber], [UserId], [Address], [TotalPayment], [Status]) VALUES (N'29D5D', N'Nguyen minh khanh', N'1234567891', 13, N'fdf', 223.58851623535156, 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerName], [PhoneNumber], [UserId], [Address], [TotalPayment], [Status]) VALUES (N'5DA31', N'Nguyen minh khanh', N'1234567891', 13, N'fdf', 153.64450073242188, 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerName], [PhoneNumber], [UserId], [Address], [TotalPayment], [Status]) VALUES (N'8520E', N'Tran Thanh Tung', N'1234566547', 14, N'xvnt', 623.81787109375, 1)
INSERT [dbo].[Orders] ([OrderId], [CustomerName], [PhoneNumber], [UserId], [Address], [TotalPayment], [Status]) VALUES (N'BBB5C', N'Minh ', N'0388455446', 2, N'fsdfsd', 10, 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-ABC-012', N'TestProduct', N'assd', N'lan.jpg', 123.23320770263672, 15, 1, 4)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-AVB-234', N'Chu khai xuan', N'Dep', N'tet2.jpg', 12.256321907043457, 20, 1, 4)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-CKD-001', N'Chau mai', N'Trung bay ngay tet', N'chaumai.jpg', 20, 20, 1, 4)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-CKD-672', N'long den', N'Sang bong', N'longdentet.jpg', 1.3322000503540039, 20, 1, 3)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-CUK-650', N'Quyt', N'Ngon', N'quyt.jpg', 20, 20, 1, 3)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-LVO-634', N'Muc', N'An ngay tet', N'keo.jpg', 20, 20, 1, 4)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-OLD-132', N'Chau dao', N'Trung bay ngay tet', N'chaudao.jpg', 50, 20, 1, 4)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-OLD-596', N'Banh quy', N'An rat ngon', N'cookies.jpg', 50, 20, 1, 2)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-OLD-957', N'Ao dai', N'Ao rat dep', N'ao dai.jpg', 20, 20, 1, 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-OLP-065', N'Ao moi', N'Mac ao don tet', N'aoNamMoi.jpg', 10, 19, 1, 3)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-PLO-854', N'Hop qua', N'Tang ban be ngay tet', N'hop qua.jpg', 53.1144, 20, 1, 2)
INSERT [dbo].[Product] ([ProductId], [ProductName], [Description], [Image], [Price], [Quantity], [Status], [CategoryID]) VALUES (N'NT-TLU-956', N'Hop tra', N'Dep', N'tra.jpg', 122.31230926513672, 20, 1, 2)
SET IDENTITY_INSERT [dbo].[Profile] ON 

INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (1, N'Vo Huu Loc', N'Nam', CAST(N'1993-11-11' AS Date), N'No@gmail.com', N'038845544', 1, 1, N'fsdfs')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (2, N'Minh ', N'Nam', CAST(N'1993-11-15' AS Date), N'dsda@fdfs.com', N'0388455446', 2, 1, N'fsdfsd')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (3, N'Sao', N'Nam', CAST(N'1993-11-11' AS Date), N'No@gmail.com', N'038845544', 3, 1, N'dsfdsfsd')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (4, N'Thanh Thong', N'Nam', CAST(N'2021-02-02' AS Date), N'dc1@gm.com', N'1010101010', 9, 1, N'dsfsdfs')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (5, N'Anh Nam', N'Nu', CAST(N'2021-02-03' AS Date), N'dk21@gm.com', N'12345', 4, 1, N'sfdfsdfs')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (6, N'Duc', N'Nam', CAST(N'2021-02-01' AS Date), N'do12@gm.com', N'1010101011', 11, 1, N'fsdf')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (7, N'Cong Minh', N'Nam', CAST(N'2021-02-03' AS Date), N'd@gm.com', N'1010101022', 12, 1, N'fsdfsd')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (8, N'Nguyen Minh khanh', N'Nam', CAST(N'2020-11-17' AS Date), N'Nosa@gmail.com', N'1234567891', 13, 1, N'fdf')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (9, N'Tran Thanh Tung', N'Nam', CAST(N'2010-06-17' AS Date), N'dxa@gm.com', N'1234566444', 14, 1, N'xvnt')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (10, N'p', N'Nam', CAST(N'2020-11-17' AS Date), N'ddsa@gm.com', N'1010101077', 15, 1, N'xvnt')
INSERT [dbo].[Profile] ([ProfileID], [CustomerName], [Sex], [DOB], [Email], [PhoneNumber], [UserId], [Status], [Address]) VALUES (11, N'a', N'Nam', CAST(N'2021-03-15' AS Date), N'ds@gm.com', N'1010101015', 16, 1, N's')
SET IDENTITY_INSERT [dbo].[Profile] OFF
SET IDENTITY_INSERT [dbo].[Registration] ON 

INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (1, N'LocVH', N'1234', N'admin', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (2, N'DucVH', N'duc@12', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (3, N'ThongVH', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (4, N'NamAI', N'chuotmikey', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (5, N'ChauKN', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (6, N'LoanTN', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (7, N'PhucVT', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (9, N'ThongNT', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (10, N'AnTH', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (11, N'MinhCT', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (12, N'KietTT', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (13, N'KhanhNM', N'thoden', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (14, N'TungTT', N'chugau', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (15, N'test', N'1234', N'user', 1)
INSERT [dbo].[Registration] ([UserId], [Username], [Password], [Role], [Status]) VALUES (16, N'addd', N'1', N'user', 1)
SET IDENTITY_INSERT [dbo].[Registration] OFF
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Orders] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Orders] ([OrderId])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Orders]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Product] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([ProductId])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Product]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Registration] FOREIGN KEY([UserId])
REFERENCES [dbo].[Registration] ([UserId])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Registration]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[Profile]  WITH CHECK ADD  CONSTRAINT [FK_Profile_Registration] FOREIGN KEY([UserId])
REFERENCES [dbo].[Registration] ([UserId])
GO
ALTER TABLE [dbo].[Profile] CHECK CONSTRAINT [FK_Profile_Registration]
GO
USE [master]
GO
ALTER DATABASE [LunarNewYear] SET  READ_WRITE 
GO
