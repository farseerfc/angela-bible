Public Class 意见反馈

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Dim suggestion As String = SqlFilter(TextBox1.Text)
        Dim timestamp As Date = Date.Now
        Dim username As String = loginedUser.Username


        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "insert into [Suggestion]([username],[timestamp],[suggestion]) " + _
                     "select '" + username + "','" + timestamp + "','" + suggestion + "' " + _
                    "where not exists(" + _
                        "select * from [dbo].[Suggestion] where [username] = '" + username + "' and [timestamp]='" + timestamp + "');"
            Dim rowcount As Integer = sqlCommand.ExecuteNonQuery()
            If rowcount > 0 Then
                '添加成功
                MsgBox("您的意见已经告知管理员，谢谢您的参与！")
            Else
                '添加失败
                MsgBox("数据库链接失败！")
            End If


        End Using
    End Sub

    Private Sub DateTimePicker1_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles DateTimePicker1.ValueChanged
        Dim dateTimePicker1 As New DateTimePicker()

        ' Set the MinDate and MaxDate.因为只能显示当天
        dateTimePicker1.MinDate = DateTime.Today
        dateTimePicker1.MaxDate = DateTime.Today
        dateTimePicker1.ShowCheckBox = True
        dateTimePicker1.ShowUpDown = True


    End Sub

    Private Sub bhome_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()

    End Sub

    Private Sub bexit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub
End Class