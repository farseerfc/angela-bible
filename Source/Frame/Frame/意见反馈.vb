Public Class 意见反馈

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Dim suggestion As Char = SqlFilter(TextBox1.Text)
        Dim timestamp As Date = SqlFilter(DateTimePicker1.Value)

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "insert into [Suggestion]([username],[timestamp],[suggestion]) " + _
                     "select " & groupID & ",'" + username + "','" + timestamp + "','" + suggestion + "' "  + _
                    "where not exists(" + _
                        "select * from [dbo].[Suggestion] where suggestion = '" + suggestion + "');"
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
End Class