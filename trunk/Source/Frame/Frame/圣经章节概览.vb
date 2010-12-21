Imports System.Data.SqlClient
Imports System.IO


Public Class 圣经章节概览
    Inherits System.Windows.Forms.Form
    Dim myconn As New SqlClient.SqlConnection("initial catalog=UBPA;data source=.;integrated security=SSPI;")
    Dim myadapter As SqlClient.SqlDataAdapter = New SqlClient.SqlDataAdapter("select * from casepavilion", myconn)
    Dim mydataset As New Data.DataSet
    Private Sub 圣经概览_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub

    Private Sub ListView1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListView1.SelectedIndexChanged

    End Sub

    Private Sub ListView5_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListView5.SelectedIndexChanged

    End Sub

    Private Sub ListView7_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListView7.SelectedIndexChanged

    End Sub

    Private Sub TabControl2_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles TabControl2.SelectedIndexChanged

    End Sub

    Private Sub ListView4_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ListView4.SelectedIndexChanged

    End Sub

    Private Sub PictureBox2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles PictureBox2.Click

    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button2.Click
        Me.Hide()
        fhome.Show()
    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Cancel.Click
         Application.Exit()
    End Sub
End Class