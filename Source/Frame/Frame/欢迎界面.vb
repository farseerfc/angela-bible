Imports System.Data.SqlClient


Public Class 欢迎界面
    Inherits System.Windows.Forms.Form
    Private Sub 欢迎界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.AxShockwaveFlash1.Movie = Application.StartupPath & "\Check.swf"
    End Sub

    Private Sub AxShockwaveFlash1_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AxShockwaveFlash1.Enter

    End Sub
End Class