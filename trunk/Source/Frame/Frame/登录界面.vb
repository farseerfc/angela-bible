﻿Imports System
Imports System.Data
Imports System.Data.SqlClient
Public Class 登录界面
    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Label1.Left = Label1.Left + 1
        If Label1.Left > Me.Width Then Label1.Left = -Label1.Width
        Label2.Left = Label2.Left + 1
        If Label2.Left > Me.Width Then Label2.Left = -Label2.Width
    End Sub

    Private Sub 起始界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Timer1.Enabled = True
        Timer1.Interval = 1
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub log_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles log.Click
        Dim username As String = SqlFilter(TextBox1.Text)
        Dim password As String = SqlFilter(TextBox2.Text)

        '打开数据库连接
        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "select [username],[password],[email],[groupname] from " + _
                    "[dbo].[UserInfo] join [UserGroup] on [UserInfo].[groupId]=[UserGroup].[groupId] " + _
                    "where [username]='" + username + "' and [password]='" + password + "';"

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()

            If reader.HasRows Then
                While reader.Read()
                    loginedUser = New UserInfo
                    loginedUser.Username = reader.GetString(0).Trim
                    loginedUser.Password = reader.GetString(1).Trim
                    loginedUser.Email = reader.GetString(2).Trim
                    loginedUser.GroupName = reader.GetString(3).Trim
                End While

                '登陆成功
                'MsgBox("登陆成功！", MsgBoxStyle.OkOnly)
                fhome = New 主页
                fhome.Show()
                Me.Close()
            Else
                MsgBox("登陆失败！", MsgBoxStyle.OkOnly)
            End If
        End Using
    End Sub

    Private Sub browse_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles browse.Click
        loginedUser = New UserInfo
        loginedUser.Username = "guest"
        loginedUser.Password = ""
        loginedUser.Email = ""
        loginedUser.GroupName = ""

        fhome = New 主页
        fhome.Show()
        Me.Close()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Dim psd As String
        psd = InputBox("请输入管理员查询口令", "管理员登录审核")
        If psd = "13482777307" Then
            MsgBox("果然是最聪明的主人哦!欢迎回来哇！")
            fadmin = New 管理员查询界面
            fadmin.Show()
            Me.Close()
            End
        Else
            MsgBox("口令错误,切勿私自闯入！", MsgBoxStyle.OkOnly, "严重警告你啊！！")
        End If
    End Sub

    Private Sub register_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles register.Click
        fregister = New 用户注册界面
        fregister.Show()
        Me.Close()
    End Sub
End Class
