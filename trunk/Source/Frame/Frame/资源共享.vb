Imports System.Data.SqlClient
Imports System.IO


Public Class 资源共享
    Inherits System.Windows.Forms.Form
    Dim myconn As New SqlClient.SqlConnection("initial catalog=UBPA;data source=.;integrated security=SSPI;")
    Dim myadapter As SqlClient.SqlDataAdapter = New SqlClient.SqlDataAdapter("select * from casepavilion", myconn)
    Dim mydataset As New Data.DataSet
    Private Sub 圣经概览_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class