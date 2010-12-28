Public Class 主页

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_browse.Click
        fbrowse = New 圣经章节概览
        fbrowse.Show()
        Me.Close()
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_search.Click
        fsearch = New 经文精确搜索
        fsearch.Show()
        Me.Close()
    End Sub

    Private Sub Button5_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_version.Click
        fversion = New 圣经版本简介
        fversion.Show()
        Me.Close()
    End Sub
    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Label1.Left = Label1.Left + 1
        If Label1.Left < Me.Width Then Label1.Left = -Label1.Width
    End Sub

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_carol.Click
        fcarols = New 赞美诗
        fcarols.Show()
        Me.Close()
    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub
    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_suggestion.Click
        fmessage = New 意见反馈
        fmessage.Show()
        Me.Close()
    End Sub
End Class