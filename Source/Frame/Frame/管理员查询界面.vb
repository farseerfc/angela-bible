Imports System.Data.SqlClient

Public Class 管理员查询界面
    Inherits System.Windows.Forms.Form
    Dim username As String
    Dim groupID As Integer
    Dim password As String
    Dim email As String
    Dim odb As New db.db



    Private Sub 管理学查询界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: 这行代码将数据加载到表“AngelabibleDataSet.UserInfo”中。您可以根据需要移动或移除它。
        Me.UserInfoTableAdapter.Fill(Me.AngelabibleDataSet.UserInfo)

    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button4.Click
        Application.Exit()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        Me.Hide()
        fstart.Show()
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Dim sqlsel As String
        Dim strdel As String
        If MsgBox("删除不能恢复，确认删除吗？", MsgBoxStyle.Question + MsgBoxStyle.OkCancel) = MsgBoxResult.Ok Then
            Try
                Dim username As String = "delete from UserInfo where Username = '" & Trim(txtname.Text) & "'"
                Dim idel As Integer = odb.updatedatabase(strdel)
                If idel = 1 Then
                    DataGridView1.DataSource = odb.createdatatable(sqlsel)
                    MsgBox("删除成功！", MsgBoxStyle.Information)
                End If
            Catch ex As Exception
                MsgBox(ex.Message)
                Exit Sub
            End Try
        End If

    End Sub

    Private Sub DataGridView1_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView1.CellContentClick

    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button5.Click
        If txtname.Text = "' then " Then
            MsgBox("请先输入需要查询的用户名()", MsgBoxStyle.Information)
            txtname.Focus()
        Else

        End If

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click
        If OpenFileDialog1.ShowDialog = DialogResult.OK Then
        End If
    End Sub

    Private Sub OpenFileDialog1_FileOk(ByVal sender As System.Object, ByVal e As System.ComponentModel.CancelEventArgs) Handles OpenFileDialog1.FileOk

    End Sub
End Class