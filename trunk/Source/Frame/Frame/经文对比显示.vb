Public Class 经文对比显示

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        Me.Hide()
        fhome.Show()
    End Sub

    Private Sub Cancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub Timer1_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Timer1.Tick
        Label1.Left = Label1.Left + 1
        If Label1.Left > Me.Width Then Label1.Left = -Label1.Width
    End Sub

   
    Private Sub B_ChapterDown_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub B_ChapterUp_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub Label3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub B_VerseUp_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub
    Private Sub Combo1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo1.SelectedIndexChanged
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)

        If book Is Nothing Then
            Return
        End If

        Dim nrChapter As Integer = Key.GetChapterCount(book.book)

        nudChapter.Maximum = nrChapter
        nudChapter.Minimum = 1

        reload(ComboBox1, RichTextBox1)
        reload(ComboBox2, RichTextBox2)
    End Sub
    Private Sub 经文对比显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ComboBox1.Items.Clear()
        ComboBox1.Items.AddRange(Version.GetAllVersions().ToArray())
        ComboBox2.Items.Clear()
        ComboBox2.Items.AddRange(Version.GetAllVersions().ToArray())

        Combo1.Items.Clear()
        Combo1.Items.AddRange(Book.GetAllBooks().ToArray())

        reload(ComboBox1, RichTextBox1)
        reload(ComboBox2, RichTextBox2)
        Timer1.Enabled = True
        Timer1.Interval = 1
    End Sub

    Private Sub nudChapter_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudChapter.ValueChanged
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = nudChapter.Value

        If book Is Nothing Or chapter = 0 Then
            Return
        End If

        Dim nrVerse As Integer = Key.GetVerseCount(book.book, chapter)
        nudVerse.Maximum = nrVerse
        nudVerse.Minimum = 1

        reload(ComboBox1, RichTextBox1)
        reload(ComboBox2, RichTextBox2)
    End Sub

    Private Sub reload(ByVal comboVersion As ComboBox, ByVal rtf As RichTextBox)
        Dim ver As Version = DirectCast(comboVersion.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = nudChapter.Value
        Dim verse As Integer = nudVerse.Value

        If ver Is Nothing Or book Is Nothing Or chapter = 0 Or verse = 0 Then
            Return
        End If

        Dim osisId As String = Key.GetOsisId(book.book, chapter, verse)

        Dim textContext As String = Module1.Text.GetText(ver.initial, osisId)

        If textContext Is Nothing Then
            With rtf
                .Text = "所选择的版本中没有这个章节！"
                .SelectAll()
                .SelectionFont = New System.Drawing.Font("幼圆", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
                .SelectionColor = Color.Red
            End With
        Else
            With rtf
                .Text = textContext
                .SelectAll()
                .SelectionFont = New System.Drawing.Font("幼圆", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
                .SelectionColor = Color.Black
            End With

            If ver.language.Equals("Hebrew") Then
                rtf.SelectionFont = New System.Drawing.Font("Hebrew", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))

            End If
        End If
    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        reload(ComboBox1, RichTextBox1)

    End Sub

    Private Sub ComboBox2_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox2.SelectedIndexChanged
        reload(ComboBox2, RichTextBox2)
    End Sub

    Private Sub nudVerse_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudVerse.ValueChanged
        reload(ComboBox1, RichTextBox1)
        reload(ComboBox2, RichTextBox2)
    End Sub

    Private Sub B_Backbrowse_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_Backbrowse.Click
        Me.Hide()
        fbrowse.Show()
    End Sub
End Class
