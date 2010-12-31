Imports System.Data.SqlClient
Public Class 欢迎界面
    Inherits System.Windows.Forms.Form


    Private Sub 欢迎界面_Deactivate(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Deactivate
        fstart = New 登录界面
        fstart.Show()
        fstart.BringToFront()
    End Sub


    Private Sub 欢迎界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.AxShockwaveFlash1.Movie = Application.StartupPath & "\开机动画.swf"
    End Sub
    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        If (Me.Visible) Then

            Me.Close()
        End If
        Timer1.Enabled = False
    End Sub

    Private Sub 欢迎界面_Shown(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Shown
        Timer1.Enabled = True
    End Sub
End Class