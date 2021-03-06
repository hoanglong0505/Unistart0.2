USE [master]
GO
/****** Object:  Database [University]    Script Date: 09-07-2018 5:30:27 PM ******/
CREATE DATABASE [University]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'University', FILENAME = N'D:\Quy\SQLDATA\University.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'University_log', FILENAME = N'D:\Quy\SQLDATA\University_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [University] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [University].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [University] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [University] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [University] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [University] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [University] SET ARITHABORT OFF 
GO
ALTER DATABASE [University] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [University] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [University] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [University] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [University] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [University] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [University] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [University] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [University] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [University] SET  DISABLE_BROKER 
GO
ALTER DATABASE [University] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [University] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [University] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [University] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [University] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [University] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [University] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [University] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [University] SET  MULTI_USER 
GO
ALTER DATABASE [University] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [University] SET DB_CHAINING OFF 
GO
ALTER DATABASE [University] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [University] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [University] SET DELAYED_DURABILITY = DISABLED 
GO
USE [University]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](255) NOT NULL,
	[Contents] [varchar](255) NOT NULL,
	[CreateDate] [datetime] NULL,
	[Description] [varchar](255) NULL,
	[Image] [varchar](255) NULL,
	[IsActive] [bit] NULL,
	[Title] [varchar](255) NOT NULL,
	[UniversityId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ArticleTag]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ArticleTag](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ArticleId] [int] NULL,
	[MajorUniId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Block]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Block](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[BlockName] [varchar](255) NULL,
	[Description] [varchar](255) NULL,
	[IsActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BlockMajorUniversity]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BlockMajorUniversity](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NULL,
	[BLockId] [int] NOT NULL,
	[MajorUniversityID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BlockOfMajor]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BlockOfMajor](
	[Id] [int] NOT NULL,
	[isActive] [bit] NULL,
	[BlockId] [int] NULL,
	[MajorId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Favorite]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorite](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MajorUniversityId] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GroupMajor]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[GroupMajor](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[Code] [varchar](10) NOT NULL,
	[Name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HighSchool]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HighSchool](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [int] NOT NULL,
	[Name] [varchar](255) NOT NULL,
	[LocationId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Location]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Location](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NULL,
	[LocationCode] [varchar](255) NULL,
	[LocationName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Major]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Major](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Description] [varchar](255) NULL,
	[IsActive] [bit] NOT NULL,
	[MajorName] [varchar](255) NOT NULL,
	[GroupMajorId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MajorMBTI]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MajorMBTI](
	[Id] [int] NOT NULL,
	[isActive] [bit] NULL,
	[MajorId] [int] NULL,
	[MBTITypeID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MajorUniversity]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MajorUniversity](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Description] [varchar](255) NULL,
	[isActive] [bit] NULL,
	[NumberOfYear] [float] NULL,
	[Prospects] [varchar](255) NULL,
	[Requirement] [varchar](255) NULL,
	[MajorId] [int] NULL,
	[UniversityId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MBTIGroup]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MBTIGroup](
	[Id] [int] NOT NULL,
	[isActive] [bit] NULL,
	[MBTIGroupName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MBTIQuestion]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MBTIQuestion](
	[Id] [int] NOT NULL,
	[Code] [varchar](255) NULL,
	[isActive] [bit] NULL,
	[Option1Name] [varchar](255) NULL,
	[Option2Name] [varchar](255) NULL,
	[QuestionContent] [varchar](255) NULL,
	[MBTIGroupId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MBTIResult]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MBTIResult](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[TestDate] [datetime] NULL,
	[MBTITypeId] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MBTIType]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MBTIType](
	[Id] [int] NOT NULL,
	[contentType] [varchar](255) NULL,
	[Description] [varchar](255) NULL,
	[isActive] [bit] NOT NULL,
	[MBTITypeName] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Provider]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Provider](
	[ProviderID] [varchar](255) NOT NULL,
	[ProviderName] [varchar](255) NOT NULL,
	[UserID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ProviderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuestionAnswer]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[QuestionAnswer](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Contents] [varchar](255) NOT NULL,
	[Count] [int] NOT NULL,
	[CreatedDateTime] [datetime] NOT NULL,
	[IsActive] [bit] NOT NULL,
	[LastUpdatedTime] [datetime] NULL,
	[NumberOfReport] [int] NULL,
	[ParentId] [int] NOT NULL,
	[Status] [bit] NULL,
	[Title] [varchar](255) NULL,
	[Type] [int] NOT NULL,
	[Vote] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuestionTag]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuestionTag](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[QuestionId] [int] NULL,
	[TagId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Report]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Report](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[QuestionAnswerId] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Review]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Review](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Description] [varchar](255) NULL,
	[IsActive] [bit] NULL,
	[IsRecomment] [bit] NULL,
	[StarCare] [int] NULL,
	[StarCareer] [int] NULL,
	[StarFacilities] [int] NULL,
	[StarSocieties] [int] NULL,
	[StarTeaching] [int] NULL,
	[Status] [bit] NULL,
	[Title] [varchar](255) NULL,
	[UniversityId] [int] NOT NULL,
	[UserId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ReviewAverage]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewAverage](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[AveRecomment] [float] NOT NULL,
	[AveStarCare] [float] NOT NULL,
	[AveStarCareer] [float] NOT NULL,
	[AveStarFacilities] [float] NOT NULL,
	[AveStarSocieties] [float] NOT NULL,
	[AveStarTeaching] [float] NOT NULL,
	[TotalReview] [int] NOT NULL,
	[UniversityId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReviewLike]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewLike](
	[Id] [int] NOT NULL,
	[isActive] [bit] NULL,
	[IsLike] [bit] NULL,
	[ReviewId] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReviewMajor]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewMajor](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NULL,
	[IsRecomment] [bit] NULL,
	[StarCareer] [int] NULL,
	[StarTeaching] [int] NULL,
	[MajorUniId] [int] NOT NULL,
	[UserId] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReviewMajorAverage]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewMajorAverage](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[AveRecomment] [float] NOT NULL,
	[AveStarCareer] [float] NOT NULL,
	[AveStarTeaching] [float] NOT NULL,
	[TotalReview] [int] NOT NULL,
	[MajorUniversityId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[Name] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ScoreHistory]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ScoreHistory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Barem] [int] NULL,
	[Description] [varchar](255) NULL,
	[Score] [float] NULL,
	[Year] [int] NULL,
	[BlockMajorUniId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tag](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[TagName] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TrainSystem]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TrainSystem](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IsActive] [bit] NOT NULL,
	[Name] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[University]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[University](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](255) NOT NULL,
	[Description] [varchar](255) NULL,
	[Email] [varchar](255) NULL,
	[Image] [varchar](255) NULL,
	[IsActive] [bit] NULL,
	[Logo] [varchar](255) NULL,
	[Name] [varchar](255) NOT NULL,
	[Phone] [varchar](255) NULL,
	[Priority] [int] NULL,
	[LocationId] [int] NULL,
	[TrainSystemId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](255) NULL,
	[Image] [varchar](255) NULL,
	[IsActive] [bit] NOT NULL,
	[Name] [varchar](255) NULL,
	[Password] [varchar](255) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[HighSchoolId] [int] NULL,
	[RoleID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Vote]    Script Date: 09-07-2018 5:30:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vote](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[QuestionAnswerId] [int] NULL,
	[UserId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FKmo1dhnc1v579su2ots5c9cost] FOREIGN KEY([UniversityId])
REFERENCES [dbo].[University] ([Id])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FKmo1dhnc1v579su2ots5c9cost]
GO
ALTER TABLE [dbo].[ArticleTag]  WITH CHECK ADD  CONSTRAINT [FK9ka23sfw16dlc91doym7teo5a] FOREIGN KEY([MajorUniId])
REFERENCES [dbo].[MajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[ArticleTag] CHECK CONSTRAINT [FK9ka23sfw16dlc91doym7teo5a]
GO
ALTER TABLE [dbo].[ArticleTag]  WITH CHECK ADD  CONSTRAINT [FKdq8n6fx6k0le144monv9fgbmy] FOREIGN KEY([ArticleId])
REFERENCES [dbo].[Article] ([Id])
GO
ALTER TABLE [dbo].[ArticleTag] CHECK CONSTRAINT [FKdq8n6fx6k0le144monv9fgbmy]
GO
ALTER TABLE [dbo].[BlockMajorUniversity]  WITH CHECK ADD  CONSTRAINT [FKhbhqrjhc0l8m7frq4bxr5n251] FOREIGN KEY([BLockId])
REFERENCES [dbo].[Block] ([Id])
GO
ALTER TABLE [dbo].[BlockMajorUniversity] CHECK CONSTRAINT [FKhbhqrjhc0l8m7frq4bxr5n251]
GO
ALTER TABLE [dbo].[BlockMajorUniversity]  WITH CHECK ADD  CONSTRAINT [FKtojafyp8ffurwafroqvpayfs1] FOREIGN KEY([MajorUniversityID])
REFERENCES [dbo].[MajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[BlockMajorUniversity] CHECK CONSTRAINT [FKtojafyp8ffurwafroqvpayfs1]
GO
ALTER TABLE [dbo].[BlockOfMajor]  WITH CHECK ADD  CONSTRAINT [FK1br58v5jstrt3eud31a8sgyv8] FOREIGN KEY([MajorId])
REFERENCES [dbo].[Major] ([Id])
GO
ALTER TABLE [dbo].[BlockOfMajor] CHECK CONSTRAINT [FK1br58v5jstrt3eud31a8sgyv8]
GO
ALTER TABLE [dbo].[BlockOfMajor]  WITH CHECK ADD  CONSTRAINT [FK6299ybajyhmtj4yp4jhirv5vp] FOREIGN KEY([BlockId])
REFERENCES [dbo].[Block] ([Id])
GO
ALTER TABLE [dbo].[BlockOfMajor] CHECK CONSTRAINT [FK6299ybajyhmtj4yp4jhirv5vp]
GO
ALTER TABLE [dbo].[Favorite]  WITH CHECK ADD  CONSTRAINT [FKn090x5xhb8gjl7s1eui9kvm3e] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Favorite] CHECK CONSTRAINT [FKn090x5xhb8gjl7s1eui9kvm3e]
GO
ALTER TABLE [dbo].[Favorite]  WITH CHECK ADD  CONSTRAINT [FKrk3rqr7t04vdm17ju5otuaqkm] FOREIGN KEY([MajorUniversityId])
REFERENCES [dbo].[MajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[Favorite] CHECK CONSTRAINT [FKrk3rqr7t04vdm17ju5otuaqkm]
GO
ALTER TABLE [dbo].[HighSchool]  WITH CHECK ADD  CONSTRAINT [FKq9c7st28dl5vc2glkws50ftnd] FOREIGN KEY([LocationId])
REFERENCES [dbo].[Location] ([Id])
GO
ALTER TABLE [dbo].[HighSchool] CHECK CONSTRAINT [FKq9c7st28dl5vc2glkws50ftnd]
GO
ALTER TABLE [dbo].[Major]  WITH CHECK ADD  CONSTRAINT [FKd1k67uuvuf846cy3j3h21x8j6] FOREIGN KEY([GroupMajorId])
REFERENCES [dbo].[GroupMajor] ([Id])
GO
ALTER TABLE [dbo].[Major] CHECK CONSTRAINT [FKd1k67uuvuf846cy3j3h21x8j6]
GO
ALTER TABLE [dbo].[MajorMBTI]  WITH CHECK ADD  CONSTRAINT [FK4nmrhriaxyb2tgew75sxks0hd] FOREIGN KEY([MBTITypeID])
REFERENCES [dbo].[MBTIType] ([Id])
GO
ALTER TABLE [dbo].[MajorMBTI] CHECK CONSTRAINT [FK4nmrhriaxyb2tgew75sxks0hd]
GO
ALTER TABLE [dbo].[MajorMBTI]  WITH CHECK ADD  CONSTRAINT [FKgr0avqnjr6p63sybqdmdk0wtj] FOREIGN KEY([MajorId])
REFERENCES [dbo].[Major] ([Id])
GO
ALTER TABLE [dbo].[MajorMBTI] CHECK CONSTRAINT [FKgr0avqnjr6p63sybqdmdk0wtj]
GO
ALTER TABLE [dbo].[MajorUniversity]  WITH CHECK ADD  CONSTRAINT [FK8m1odgweofkd7qwuarrxebbw1] FOREIGN KEY([MajorId])
REFERENCES [dbo].[Major] ([Id])
GO
ALTER TABLE [dbo].[MajorUniversity] CHECK CONSTRAINT [FK8m1odgweofkd7qwuarrxebbw1]
GO
ALTER TABLE [dbo].[MajorUniversity]  WITH CHECK ADD  CONSTRAINT [FKbkd2o6cpws7ic9c1jh0rswmau] FOREIGN KEY([UniversityId])
REFERENCES [dbo].[University] ([Id])
GO
ALTER TABLE [dbo].[MajorUniversity] CHECK CONSTRAINT [FKbkd2o6cpws7ic9c1jh0rswmau]
GO
ALTER TABLE [dbo].[MBTIQuestion]  WITH CHECK ADD  CONSTRAINT [FKbhjyj7pvdleatj5obvgcrieej] FOREIGN KEY([MBTIGroupId])
REFERENCES [dbo].[MBTIGroup] ([Id])
GO
ALTER TABLE [dbo].[MBTIQuestion] CHECK CONSTRAINT [FKbhjyj7pvdleatj5obvgcrieej]
GO
ALTER TABLE [dbo].[MBTIResult]  WITH CHECK ADD  CONSTRAINT [FKf5ct0yodcuw19w5fq28f0wlt2] FOREIGN KEY([MBTITypeId])
REFERENCES [dbo].[MBTIType] ([Id])
GO
ALTER TABLE [dbo].[MBTIResult] CHECK CONSTRAINT [FKf5ct0yodcuw19w5fq28f0wlt2]
GO
ALTER TABLE [dbo].[MBTIResult]  WITH CHECK ADD  CONSTRAINT [FKt1apxjg9kdox0a5f46xeyx825] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[MBTIResult] CHECK CONSTRAINT [FKt1apxjg9kdox0a5f46xeyx825]
GO
ALTER TABLE [dbo].[Provider]  WITH CHECK ADD  CONSTRAINT [FK3fhul5218f9fp2f1i6g6idl8h] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Provider] CHECK CONSTRAINT [FK3fhul5218f9fp2f1i6g6idl8h]
GO
ALTER TABLE [dbo].[QuestionAnswer]  WITH CHECK ADD  CONSTRAINT [FKjfkby03ajnm4toegfys3b9qh5] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[QuestionAnswer] CHECK CONSTRAINT [FKjfkby03ajnm4toegfys3b9qh5]
GO
ALTER TABLE [dbo].[QuestionTag]  WITH CHECK ADD  CONSTRAINT [FKepwb69i433v9ha83p0a1qegq6] FOREIGN KEY([QuestionId])
REFERENCES [dbo].[QuestionAnswer] ([Id])
GO
ALTER TABLE [dbo].[QuestionTag] CHECK CONSTRAINT [FKepwb69i433v9ha83p0a1qegq6]
GO
ALTER TABLE [dbo].[QuestionTag]  WITH CHECK ADD  CONSTRAINT [FKgjcx4rtx7ntjf6qaw9yeo0gu1] FOREIGN KEY([TagId])
REFERENCES [dbo].[Tag] ([Id])
GO
ALTER TABLE [dbo].[QuestionTag] CHECK CONSTRAINT [FKgjcx4rtx7ntjf6qaw9yeo0gu1]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FK6stiy9qma5dhj7yyp29j3dl92] FOREIGN KEY([QuestionAnswerId])
REFERENCES [dbo].[QuestionAnswer] ([Id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FK6stiy9qma5dhj7yyp29j3dl92]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FKafq3uyf4n6qvyrlfkps203sph] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FKafq3uyf4n6qvyrlfkps203sph]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK8pn6ya44x8djuydyd0rneqegj] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK8pn6ya44x8djuydyd0rneqegj]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FKtl3frq5v4i0xhnif76ocdhr7n] FOREIGN KEY([UniversityId])
REFERENCES [dbo].[University] ([Id])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FKtl3frq5v4i0xhnif76ocdhr7n]
GO
ALTER TABLE [dbo].[ReviewAverage]  WITH CHECK ADD  CONSTRAINT [FKpq3fl1jnw5ceyshe8no8qqh2m] FOREIGN KEY([UniversityId])
REFERENCES [dbo].[University] ([Id])
GO
ALTER TABLE [dbo].[ReviewAverage] CHECK CONSTRAINT [FKpq3fl1jnw5ceyshe8no8qqh2m]
GO
ALTER TABLE [dbo].[ReviewLike]  WITH CHECK ADD  CONSTRAINT [FKd8sc25trge36yo9ik5ie59jq3] FOREIGN KEY([ReviewId])
REFERENCES [dbo].[Review] ([Id])
GO
ALTER TABLE [dbo].[ReviewLike] CHECK CONSTRAINT [FKd8sc25trge36yo9ik5ie59jq3]
GO
ALTER TABLE [dbo].[ReviewLike]  WITH CHECK ADD  CONSTRAINT [FKeubxp6h29d4320thm5qyx4n5s] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[ReviewLike] CHECK CONSTRAINT [FKeubxp6h29d4320thm5qyx4n5s]
GO
ALTER TABLE [dbo].[ReviewMajor]  WITH CHECK ADD  CONSTRAINT [FK6baw6a2vct3nk5n44ei4hqc7y] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[ReviewMajor] CHECK CONSTRAINT [FK6baw6a2vct3nk5n44ei4hqc7y]
GO
ALTER TABLE [dbo].[ReviewMajor]  WITH CHECK ADD  CONSTRAINT [FKqlb1pm06xd6qxk44yat07najk] FOREIGN KEY([MajorUniId])
REFERENCES [dbo].[MajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[ReviewMajor] CHECK CONSTRAINT [FKqlb1pm06xd6qxk44yat07najk]
GO
ALTER TABLE [dbo].[ReviewMajorAverage]  WITH CHECK ADD  CONSTRAINT [FKd7safbn153e348e7ejgpnxw1u] FOREIGN KEY([MajorUniversityId])
REFERENCES [dbo].[MajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[ReviewMajorAverage] CHECK CONSTRAINT [FKd7safbn153e348e7ejgpnxw1u]
GO
ALTER TABLE [dbo].[ScoreHistory]  WITH CHECK ADD  CONSTRAINT [FK853e6si7uyli7aremfcdx0c1k] FOREIGN KEY([BlockMajorUniId])
REFERENCES [dbo].[BlockMajorUniversity] ([Id])
GO
ALTER TABLE [dbo].[ScoreHistory] CHECK CONSTRAINT [FK853e6si7uyli7aremfcdx0c1k]
GO
ALTER TABLE [dbo].[University]  WITH CHECK ADD  CONSTRAINT [FK8yht6iofa42oox0qod4x8eocr] FOREIGN KEY([TrainSystemId])
REFERENCES [dbo].[TrainSystem] ([Id])
GO
ALTER TABLE [dbo].[University] CHECK CONSTRAINT [FK8yht6iofa42oox0qod4x8eocr]
GO
ALTER TABLE [dbo].[University]  WITH CHECK ADD  CONSTRAINT [FKscy503mjibxor51f669y23vai] FOREIGN KEY([LocationId])
REFERENCES [dbo].[Location] ([Id])
GO
ALTER TABLE [dbo].[University] CHECK CONSTRAINT [FKscy503mjibxor51f669y23vai]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK98ltbnnq69up18kfeilxjkhck] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK98ltbnnq69up18kfeilxjkhck]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FKgqe15n3e71m26d2s97cfayxf6] FOREIGN KEY([HighSchoolId])
REFERENCES [dbo].[HighSchool] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FKgqe15n3e71m26d2s97cfayxf6]
GO
ALTER TABLE [dbo].[Vote]  WITH CHECK ADD  CONSTRAINT [FKodekqw2k07fh1e516bgu9s9s4] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
GO
ALTER TABLE [dbo].[Vote] CHECK CONSTRAINT [FKodekqw2k07fh1e516bgu9s9s4]
GO
ALTER TABLE [dbo].[Vote]  WITH CHECK ADD  CONSTRAINT [FKp31ymwpob4v1c2prdu5281elp] FOREIGN KEY([QuestionAnswerId])
REFERENCES [dbo].[QuestionAnswer] ([Id])
GO
ALTER TABLE [dbo].[Vote] CHECK CONSTRAINT [FKp31ymwpob4v1c2prdu5281elp]
GO
USE [master]
GO
ALTER DATABASE [University] SET  READ_WRITE 
GO
