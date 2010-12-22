Public Class 用户注册界面

    'Private Function Load_UserGroup() As List(Of String)

    'End Function

    Private Sub 用户注册界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        TextBox1.Focus()
        TextBox1.Text = ""
        TextBox2.Text = ""
        TextBox3.Text = ""
        TextBox4.Text = ""
    End Sub

    Private Sub TextBox3_LostFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox3.LostFocus
        If TextBox3.Text.Trim.Length > 5 Then
            Label8.Text = "您输入的密码合理有效！"
        Else
            TextBox3.Text = ""
            TextBox3.Focus()
        End If

    End Sub

    Private Sub TextBox1_TextChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox1.TextChanged
        Dim StrName As String
        StrName = TextBox1.Text.Trim.ToString
        If TextBox1.Text = "" Then
            Exit Sub
        End If

    End Sub


    Private Sub TextBox4_LostFocus(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TextBox4.LostFocus
        If TextBox4.Text.Trim().Equals(TextBox3.Text.Trim()) And Not TextBox3.Text.Equals("") Then
            Label9.Text = "您的密码已经确认成功！"
            Label9.ForeColor = Color.Red
        Else
            Label9.Text = "两次输入的密码不一致，请重新输入！"
            Label9.ForeColor = Color.Red
        End If
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Me.Hide()
        fstart.Show()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Application.Exit()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Dim username As String = SqlFilter(TextBox1.Text)
        Dim password1 As String = SqlFilter(TextBox3.Text)
        Dim password2 As String = SqlFilter(TextBox4.Text)
        Dim email As String = SqlFilter(TextBox2.Text)
        Dim groupID As Integer = ComboBox1.SelectedIndex + 1 ' 管理员 groupId 为0，普通用户从1开始算

        '检查用户输入是否合法
        If groupID = 0 Then
            MsgBox("请选择您的信仰状况！", MsgBoxStyle.OkOnly)
            Return
        End If

        If Not password1.Equals(password2) Then
            MsgBox("两次输入的密码不一致，请重新输入！",MsgBoxStyle.OkOnly)
            Return
        End If

        If username.Trim().Length < 6 Then
            MsgBox("用户名太短，请重新输入！", MsgBoxStyle.OkOnly)
            Return
        End If

        '打开数据库连接
        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "insert into [UserInfo]([groupId],[username],[password],[email]) " + _
                    "select " & groupID & ",'" + username + "','" + password1 + "','" + email + "' " + _
                    "where not exists(" + _
                        "select * from [dbo].[UserInfo] where username = '" + username + "');"

            Dim rowcount As Integer = sqlCommand.ExecuteNonQuery()

            If rowcount <> 1 Then
                '用户已存在
                MsgBox("用户已存在！", MsgBoxStyle.OkOnly)
                Return
            Else
                '用户不存在，插入成功
                MsgBox("用户成功注册", MsgBoxStyle.OkOnly)
                Me.Hide()
                fstart.Show()

            End If

        End Using
    End Sub

End Class