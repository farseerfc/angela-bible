﻿Imports System.Data.SqlClient


Public Class 欢迎界面
    Inherits System.Windows.Forms.Form
    Private Sub 欢迎界面_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Me.AxShockwaveFlash1.Movie = Application.StartupPath & "\开机动画.swf"
    End Sub

    Private Sub AxShockwaveFlash1_Enter(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles AxShockwaveFlash1.Enter

    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Me.Hide()
        fstart.Show()
        Timer1.Enabled = False
    End Sub
End Class