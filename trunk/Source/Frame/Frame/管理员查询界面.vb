Imports System.Data.SqlClient

Public Class 管理员查询界面
    Inherits System.Windows.Forms.Form
    Dim username As String
    Dim groupID As Integer
    Dim password As String
    Dim email As String
  


    Private Sub 管理学查询界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'TODO: 这行代码将数据加载到表“AngelabibleDataSet.UserInfo”中。您可以根据需要移动或移除它。
        'Me.UserInfoTableAdapter.Fill(Me.AngelabibleDataSet.UserInfo)
        FillUserDataView(UserInfo.GetAllUser())
        FillSuggestionView()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        fstart = New 登录界面
        fstart.Show()
        Me.Hide()
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        'Dim sqlsel As String
        'Dim strdel As String
        If MsgBox("删除不能恢复，确认删除吗？", MsgBoxStyle.Question + MsgBoxStyle.OkCancel) = MsgBoxResult.Ok Then
            Try
                'Dim username As String = "delete from UserInfo where Username = '" & Trim(txtname.Text) & "'"
                'If idel = 1 Then
                'DataGridView1.DataSource = odb.createdatatable(sqlsel)
                'MsgBox("删除成功！", MsgBoxStyle.Information)
                ' End If
                If UserInfo.DeleteUserByName(SqlFilter(txtname.Text)) Then
                    MsgBox("删除成功")
                Else
                    MsgBox("删除失败")
                End If
            Catch ex As Exception
                MsgBox(ex.Message)
                Exit Sub
            End Try
        End If


        FillUserDataView(UserInfo.GetAllUser())
    End Sub

    Private Sub DataGridView1_CellContentClick(ByVal sender As System.Object, ByVal e As System.Windows.Forms.DataGridViewCellEventArgs) Handles DataGridView1.CellContentClick
     
    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button5.Click
        If txtname.Text = "" Then
            MsgBox("请先输入需要查询的用户名()", MsgBoxStyle.Information)
            txtname.Focus()
        Else
            FillUserDataView(UserInfo.GetUserByName(SqlFilter(txtname.Text)))
        End If

    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button3.Click

        FillUserDataView(UserInfo.GetAllUser())
        
    End Sub

    Private Sub OpenFileDialog1_FileOk(ByVal sender As System.Object, ByVal e As System.ComponentModel.CancelEventArgs) Handles OpenFileDialog1.FileOk

    End Sub

    Private Sub FillUserDataView(ByVal users As List(Of UserInfo))
        DataGridView1.DataBindings.Clear()
        DataGridView1.DataSource = Nothing
        DataGridView1.Columns.Clear()

        DataGridView1.Columns.Add("Username", "用户名")
        DataGridView1.Columns.Add("Password", "密码")
        DataGridView1.Columns.Add("GroupName", "用户分组")
        DataGridView1.Columns.Add("Email", "电子邮箱")

        For Each user As UserInfo In users
            DataGridView1.Rows.Add(user.Username, user.Password, user.GroupName, user.Email)
        Next
    End Sub

    Private Sub btnAllSuggestion_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnAllSuggestion.Click
        FillSuggestionView()
    End Sub

    Private Sub FillSuggestionView()
        dgvSuggestion.DataBindings.Clear()
        dgvSuggestion.DataSource = Nothing
        dgvSuggestion.Columns.Clear()

        dgvSuggestion.Columns.Add("Username", "用户名")
        dgvSuggestion.Columns.Add("Timestamp", "留言时间")
        dgvSuggestion.Columns.Add("Suggestion", "建议")

        For Each sug As Suggestion In Suggestion.GetAll()
            dgvSuggestion.Rows.Add(sug.username, sug.timestamp, sug.suggestion)
        Next
    End Sub

    Private Sub btnDeleteSuggestion_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDeleteSuggestion.Click
        For Each row As DataGridViewRow In dgvSuggestion.SelectedRows
            Dim username As String = CStr(row.Cells(0).Value)
            Dim timestamp As Date = CDate(row.Cells(1).Value)

            Dim sug As New Suggestion
            sug.username = username
            sug.timestamp = timestamp
            If sug.Delete() Then
                MsgBox("用户" + username + "在" + timestamp + "时的留言已删除。")
            Else
                MsgBox("留言删除不成功！")
            End If
        Next

        FillSuggestionView()
    End Sub


    Private Sub DataGridView1_SelectionChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DataGridView1.SelectionChanged
        For Each row As DataGridViewRow In DataGridView1.SelectedRows
            txtname.Text = CStr(row.Cells(0).Value)
        Next
    End Sub
End Class