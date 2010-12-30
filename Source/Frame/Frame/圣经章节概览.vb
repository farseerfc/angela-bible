Imports System.Data.SqlClient
Imports System.IO

Public Class 圣经章节概览
    Inherits System.Windows.Forms.Form
    Dim myconn As New SqlClient.SqlConnection("initial catalog=UBPA;data source=.;integrated security=SSPI;")
    Dim myadapter As SqlClient.SqlDataAdapter = New SqlClient.SqlDataAdapter("select * from casepavilion", myconn)
    Dim mydataset As New Data.DataSet


    Dim listviews As New Dictionary(Of ListView, TabPage)


    ''' <summary>
    ''' 从BookGroup视图中按顺序选出book组
    ''' </summary>
    ''' <param name="testament">"OT" or "NT"</param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Private Function getBookGroups(ByVal testament As String) As List(Of String)
        Dim result As New List(Of String)

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            Dim sqlCommand As SqlClient.SqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible; " + _
                        " select [group]  from [BookGroup] where [testament] = '" + testament + "' " + _
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
                    "select [book],[name],[index],[testament],[group],[describe] from [book] " + _
                    "where [group]='" + group + "' order by [index];"

            Dim reader As SqlDataReader = sqlCommand.ExecuteReader()
            While reader.Read()
                Dim b As New Book
                b.book = reader.GetString(0).Trim()
                b.name = reader.GetString(1).Trim()
                b.index = reader.GetInt32(2)
                b.testament = reader.GetString(3).Trim()
                b.group = reader.GetString(4).Trim()
                b.describe = reader.GetString(5).Trim()

                result.Add(b)

            End While

        End Using

        Return result
    End Function

    Private Sub createBooksInTab(ByVal tab As TabControl, ByVal testament As String, ByVal imageIndexOffset As Integer)


        tab.TabPages.Clear()

        Dim imageIndex As Integer

        imageIndex = 0

        For Each group As String In getBookGroups(testament)
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

            AddHandler listview.ItemSelectionChanged, AddressOf Book_Selected


            For Each b As Book In getBooksOfGroup(group)
                Dim item As New ListViewItem
                item.Text = b.name
                item.Tag = b
                item.ToolTipText = b.name
                item.StateImageIndex = (imageIndex Mod 8) + imageIndexOffset

                listview.Items.Add(item)

                imageIndex += 1
            Next

            page.Controls.Add(listview)

            tab.TabPages.Add(page)

            listviews(listview) = page
            '            listview.Size = page.Size
        Next
    End Sub

    Private Sub Book_Selected(ByVal sender As Object, ByVal e As ListViewItemSelectionChangedEventArgs)
        Dim obj As Object = e.Item.Tag
        If (obj Is Nothing) Then
            Return
        End If
        choosedBook = DirectCast(obj, Book)
    End Sub

    Private Sub getVersions(ByVal combo As ComboBox)
        combo.Items.Clear()
        combo.Items.AddRange(Version.GetAllVersions().ToArray())
    End Sub

    Private Sub 圣经概览_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        createBooksInTab(Tab_NT, "OT", 0)
        createBooksInTab(Tab_OT, "NT", 7)

        getVersions(Combo_Ver2)
        getVersions(Combo_Ver1)

        If Not (choosedVersion2 Is Nothing) Then
            For i As Integer = 0 To Combo_Ver2.Items.Count - 1
                If choosedVersion2.initial.Equals(DirectCast(Combo_Ver2.Items.Item(i), Version).initial) Then
                    Combo_Ver2.SelectedIndex = i
                End If
            Next
        End If

        If Not (choosedVersion1 Is Nothing) Then
            For i As Integer = 0 To Combo_Ver1.Items.Count - 1
                If choosedVersion1.initial.Equals(DirectCast(Combo_Ver1.Items.Item(i), Version).initial) Then
                    Combo_Ver1.SelectedIndex = i
                End If
            Next
        End If

        If Not (choosedBook Is Nothing) Then
            For Each pair As KeyValuePair(Of ListView, TabPage) In listviews
                For i As Integer = 0 To pair.Key.Items.Count - 1
                    If (DirectCast(pair.Key.Items(i).Tag, Book).book.Equals(choosedBook.book)) Then
                        pair.Key.SelectedIndices.Clear()
                        pair.Key.SelectedIndices.Add(i)

                        If Tab_NT.TabPages.Contains(pair.Value) Then
                            Tab_NT.SelectedTab = pair.Value
                        End If

                        If Tab_OT.TabPages.Contains(pair.Value) Then
                            Tab_OT.SelectedTab = pair.Value
                        End If
                    End If
                Next
            Next
        End If
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()
    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles b_search.Click
        fcompare = New 经文对比显示
        fcompare.Show()
        Me.Close()
    End Sub

    Private Sub ComboBox2_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo_Ver1.SelectedIndexChanged
        Dim obj As Version = DirectCast(Combo_Ver1.SelectedItem, Version)
        If obj Is Nothing Then
            Return
        End If

        choosedVersion1 = obj
    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo_Ver2.SelectedIndexChanged
        Dim obj As Version = DirectCast(Combo_Ver2.SelectedItem, Version)
        If obj Is Nothing Then
            Return
        End If

        choosedVersion2 = obj
    End Sub
End Class