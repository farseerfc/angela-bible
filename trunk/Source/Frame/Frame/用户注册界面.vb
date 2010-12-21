Public Class 用户注册界面

    Private Function Load_UserGroup() As List(Of String)

    End Function

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
            Label8.Text = "恭喜您，您输入的密码合理有效！"
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
        If TextBox4.Text.Trim = TextBox3.Text.Trim And TextBox4.Text <> "" Then
            Label9.Text = "确认密码成功！"
            Label9.ForeColor = Color.Orange
        Else
            Label9.Text = "您输入的确认密码有错误，请重新输入确认密码或重新输入密码！"
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
End Class