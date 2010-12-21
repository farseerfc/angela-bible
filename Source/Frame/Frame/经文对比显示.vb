Public Class 经文对比显示

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_VerseDown.Click

    End Sub

    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button6.Click
        Me.Hide()
        fhome.Show()
    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel.Click
        Application.Exit()
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Label1.Left = Label1.Left + 1
        If Label1.Left > Me.Width Then Label1.Left = -Label1.Width
    End Sub

    Private Sub B_ChapterDown_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_ChapterDown.Click

    End Sub
End Class