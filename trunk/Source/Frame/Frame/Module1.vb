﻿Imports System.Data.SqlClient

Module Module1
    Public fstart As New 登录界面
    Public fregister As New 用户注册界面
    Public fbrowse As New 圣经章节概览
    Public fsearch As New 经文精确搜索
    Public fcompare As New 经文对比显示
    Public freader As New 经文查询显示
    Public fcarols As New 赞美诗
    Public fhome As New 主页
    Public fwelcome As New 欢迎界面
    Public fadmin As New 管理员查询界面
    Public fversion As New 圣经版本简介
    Public fhelp As New 帮助
    Public fmessage As New 意见反馈

    Public strConnect As String = "server=localhost;user id=angela;password=angela;initial catalog=angelabible;"

    ''' <summary>
    ''' 主程序入口点
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub Main()
        '开启WinXP之后就有的视觉特效
        Application.EnableVisualStyles()
        '选择一个窗口作为主窗口
        ' TODO: 修改这句代码，完善窗口生命期管理
        Application.Run(freader)
    End Sub

    Public Class Book
        Public book As String
        Public name As String
        Public index As Integer
        Public treatment As String
        Public group As String
        Public describe As String

        Public Overrides Function ToString() As String
            Return Me.name
        End Function

        Public Shared Function GetAllBooks() As List(Of Book)
            Dim result As New List(Of Book)

            Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
                sqlConnection.Open()
                sqlConnection.CreateCommand()
                Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
                sqlCommand.CommandText = "use angelabible; " + _
                        "select [book],[name],[index],[treatment],[group],[describe] from [book] order by [index];"

                Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
                While reader.Read()
                    Dim b As New Book
                    b.book = reader.GetString(0).Trim()
                    b.name = reader.GetString(1).Trim()
                    b.index = reader.GetInt32(2)
                    b.treatment = reader.GetString(3).Trim()
                    b.group = reader.GetString(4).Trim()
                    b.describe = reader.GetString(5).Trim()

                    result.Add(b)

                End While

            End Using

            Return result
        End Function

    End Class


    Public Class Key
        Public osisId As String
        Public book As String
        Public chapter As Integer
        Public verse As Integer

        Public Shared Function GetVerseCount(ByVal book As String, ByVal chapter As Integer, ByVal initial As String) As Integer
            Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
                sqlConnection.Open()
                sqlConnection.CreateCommand()
                Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
                sqlCommand.CommandText = "use angelabible; " + _
                        "select Max(verse) from (" + _
                            "[Text] join [Key] on [Text].osisID=[Key].osisID)" + _
                                "join [Version] on [Version].initial=[Text].initial " + _
                            "where book='" + book + _
                        "' and chapter=" & chapter & _
                        " and [Text].initial='" + initial + "';"
                Return CInt(sqlCommand.ExecuteScalar())
            End Using
        End Function

        Public Shared Function GetChapterCount(ByVal book As String, ByVal initial As String) As String
            Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
                sqlConnection.Open()
                sqlConnection.CreateCommand()
                Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
                sqlCommand.CommandText = "use angelabible; " + _
                        "select Max(chapter) from (" + _
                            "[Text] join [Key] on [Text].osisID=[Key].osisID)" + _
                                "join [Version] on [Version].initial=[Text].initial " + _
                            "where book='" + book + _
                        "' and [Text].initial='" + initial + "';"
                Return CInt(sqlCommand.ExecuteScalar())
            End Using
        End Function
    End Class


    Public Class Version
        Public initial As String
        Public language As String
        Public osisRef As String
        Public title As String
        Public describe As String

        Public Overrides Function ToString() As String
            Return Me.title
        End Function

        Public Shared Function GetAllVersions() As List(Of Version)
            Dim result As New List(Of Version)

            Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
                sqlConnection.Open()
                sqlConnection.CreateCommand()
                Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
                sqlCommand.CommandText = "use angelabible; " + _
                        "select [initial],[language],[osisRef],[title],[describe]  from [Version];"

                Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
                While reader.Read()
                    Dim ver As New Version
                    ver.initial = reader.GetString(0).Trim()
                    ver.language = reader.GetString(1).Trim()
                    ver.osisRef = reader.GetString(2).Trim()
                    ver.title = reader.GetString(3).Trim()
                    ver.describe = reader.GetString(4)
                    result.Add(ver)
                End While

            End Using

            Return result
        End Function
    End Class


    Public Class BookVersion
        Public book As String
        Public initial As String

        Public Shared Function GetBooksByVersion(ByVal initial As String) As List(Of Book)
            Dim result As New List(Of Book)

            Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
                sqlConnection.Open()
                sqlConnection.CreateCommand()
                Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
                sqlCommand.CommandText = "use angelabible; " + _
                        "select [Book].[book],[name],[index],[treatment],[group],[describe] " + _
                        "    from Book join BookVersion on Book.book = bookVersion.book " + _
                        "    where initial ='" + initial + "' order by [index];"

                Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
                While reader.Read()
                    Dim b As New Book
                    b.book = reader.GetString(0).Trim()
                    b.name = reader.GetString(1).Trim()
                    b.index = reader.GetInt32(2)
                    b.treatment = reader.GetString(3).Trim()
                    b.group = reader.GetString(4).Trim()
                    b.describe = reader.GetString(5).Trim()

                    result.Add(b)

                End While

            End Using

            Return result

        End Function

    End Class

    ''' <summary>
    ''' 返回即将嵌入sql的字符串，转义掉“'”，简单地防止sql注入
    ''' </summary>
    ''' <param name="input"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function SqlFilter(ByVal input As String) As String
        Return input.Replace("'", "''").Trim()
    End Function

    '测试用
    '
    Public Function GetDataSet() As SqlClient.SqlDataReader

        Dim strConnect As String = "server=localhost;user id=angela;password=angela;initial catalog=angelabible;"
        Dim sqlCommand As SqlClient.SqlCommand

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            sqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible;select * from [dbo].[Version];"

            Dim sqlReader As SqlClient.SqlDataReader = sqlCommand.ExecuteReader()
            While sqlReader.Read()

                Dim initial As String = sqlReader.GetString(0)
                Dim title As String = sqlReader.GetString(1)

                MsgBox(title, MsgBoxStyle.OkOnly, initial)
            End While




            Return sqlReader
        End Using

    End Function
End Module
