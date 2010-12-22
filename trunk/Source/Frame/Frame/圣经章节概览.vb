Imports System.Data.SqlClient
Imports System.IO


Public Class 圣经章节概览
    Inherits System.Windows.Forms.Form
    Dim myconn As New SqlClient.SqlConnection("initial catalog=UBPA;data source=.;integrated security=SSPI;")
    Dim myadapter As SqlClient.SqlDataAdapter = New SqlClient.SqlDataAdapter("select * from casepavilion", myconn)
    Dim mydataset As New Data.DataSet

    Private Function getAllBooks() As List(Of Book)
        Dim result As New List(Of Book)

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "select [book],[name],[index],[treatment],[group],[describe] from [book] order by [index];"

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
            While reader.Read()
                Dim b As New Book
                b.book = reader.GetString(0).Trim()
                b.name = reader.GetString(1).Trim()
                b.index = reader.GetInt32(2)
                b.treatment = reader.GetString(3).Trim()
                b.group = reader.GetString(4).Trim()
                b.describe = reader.GetString(5).Trim()

                result.Add(b)

            End While

        End Using

        Return result
    End Function

    ''' <summary>
    ''' 从BookGroup视图中按顺序选出book组
    ''' </summary>
    ''' <param name="treatment">"OT" or "NT"</param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function getBookGroups(ByVal treatment As String) As List(Of String)
        Dim result As New List(Of String)

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                        " select [group]  from [BookGroup] where [treatment] = '" + treatment + "' " + _
                        "         order by mindex ; "

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
            While reader.Read()
                result.Add(reader.GetString(0).Trim())
            End While

        End Using

        Return result
    End Function


    Private Function getBooksOfGroup(ByVal group As String) As List(Of Book)
        Dim result As New List(Of Book)

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                    "select [book],[name],[index],[treatment],[group],[describe] from [book] " + _
                    "where [group]='" + group + "' order by [index];"

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
            While reader.Read()
                Dim b As New Book
                b.book = reader.GetString(0).Trim()
                b.name = reader.GetString(1).Trim()
                b.index = reader.GetInt32(2)
                b.treatment = reader.GetString(3).Trim()
                b.group = reader.GetString(4).Trim()
                b.describe = reader.GetString(5).Trim()

                result.Add(b)

            End While

        End Using

        Return result
    End Function

    Private Sub createBooksInTab(ByVal tab As TabControl, ByVal treatment As String, ByVal imageIndexOffset As Integer)


        tab.TabPages.Clear()

        Dim imageIndex As Integer

        imageIndex = 0

        For Each group As String In getBookGroups(treatment)
            Dim page As New TabPage
            page.Text = group
            page.ToolTipText = group
            page.Tag = group

            Dim listview As New ListView

            'copy 自designer的代码，设置格式字体等属性
            listview.Activation = System.Windows.Forms.ItemActivation.OneClick
            listview.Alignment = System.Windows.Forms.ListViewAlignment.Left
            listview.BackColor = System.Drawing.Color.Lavender
            listview.Font = New System.Drawing.Font("幼圆", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
            listview.ForeColor = System.Drawing.Color.DarkBlue
            listview.ImeMode = System.Windows.Forms.ImeMode.NoControl
            listview.LargeImageList = Me.ImageList1
            listview.Location = New System.Drawing.Point(3, 3)
            listview.Name = "ListView1"
            listview.RightToLeft = System.Windows.Forms.RightToLeft.Yes
            listview.ShowItemToolTips = True
            listview.StateImageList = ImageList1
            listview.UseCompatibleStateImageBehavior = False
            listview.View = System.Windows.Forms.View.SmallIcon

            listview.Dock = DockStyle.Fill


            For Each b As Book In getBooksOfGroup(group)
                Dim item As New ListViewItem
                item.Text = b.name
                item.Tag = b.book
                item.ToolTipText = b.name
                item.StateImageIndex = (imageIndex Mod 8) + imageIndexOffset

                listview.Items.Add(item)

                imageIndex += 1
            Next

            page.Controls.Add(listview)

            tab.TabPages.Add(page)

            '            listview.Size = page.Size
        Next
    End Sub

    Private Sub getVersions(combo As ComboBox)
        Dim result As New List(Of String)
        combo.Items.Clear()

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                        "select title from [Version]; "

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
            While reader.Read()
                result.Add(reader.GetString(0).Trim())


            End While

        End Using


        combo.Items.AddRange(result.ToArray())
    End Sub

    Private Sub 圣经概览_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        createBooksInTab(TabControl1, "OT", 0)
        createBooksInTab(TabControl2, "NT", 7)

        getVersions(ComboBox1)
        getVersions(ComboBox2)

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

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click

    End Sub
End Class