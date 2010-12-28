Public Class 经文对比显示

    Private Sub Button3_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)

    End Sub

    Private Sub Button6_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()
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

        choosedBook = book

        Dim nrChapter As Integer = Key.GetChapterCount(book.book)
        Dim bckChoosedChapter As Integer = choosedChapter
        nudChapter.Maximum = nrChapter
        nudChapter.Minimum = 1
        choosedChapter = bckChoosedChapter

        reload(ComboBox1, RichTextBox1, choosedVersion1)
        reload(ComboBox2, RichTextBox2, choosedVersion2)
    End Sub
    Private Sub 经文对比显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ComboBox1.Items.Clear()
        ComboBox1.Items.AddRange(Version.GetAllVersions().ToArray())
        ComboBox2.Items.Clear()
        ComboBox2.Items.AddRange(Version.GetAllVersions().ToArray())

        Combo1.Items.Clear()
        Combo1.Items.AddRange(Book.GetAllBooks().ToArray())


        If Not choosedVersion1 Is Nothing Then
            For i As Integer = 0 To ComboBox1.Items.Count - 1
                If choosedVersion1.initial.Equals(DirectCast(ComboBox1.Items.Item(i), Version).initial) Then
                    ComboBox1.SelectedIndex = i
                End If
            Next
        End If

        If Not choosedVersion2 Is Nothing Then
            For i As Integer = 0 To ComboBox2.Items.Count - 1
                If choosedVersion2.initial.Equals(DirectCast(ComboBox2.Items.Item(i), Version).initial) Then
                    ComboBox2.SelectedIndex = i
                End If
            Next
        End If

        If Not choosedBook Is Nothing Then
            For i As Integer = 0 To Combo1.Items.Count - 1
                If choosedBook.book.Equals(DirectCast(Combo1.Items.Item(i), Book).book) Then
                    Combo1.SelectedIndex = i
                End If
            Next
        End If



        If choosedChapter <= nudChapter.Maximum And choosedChapter >= nudChapter.Minimum Then
            nudChapter.Value = choosedChapter
        End If

        If choosedVerse <= nudVerse.Maximum And choosedVerse >= nudVerse.Minimum Then
            nudVerse.Value = choosedVerse
        End If

        reload(ComboBox1, RichTextBox1, choosedVersion1)
        reload(ComboBox2, RichTextBox2, choosedVersion2)

        Timer1.Enabled = True
        Timer1.Interval = 1
    End Sub

    Private Sub nudChapter_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudChapter.ValueChanged
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = nudChapter.Value

        If book Is Nothing Or chapter = 0 Then
            Return
        End If

        choosedChapter = chapter

        Dim nrVerse As Integer = Key.GetVerseCount(book.book, chapter)
        Dim bckChoosedVerse As Integer = choosedVerse
        nudVerse.Maximum = nrVerse
        nudVerse.Minimum = 1
        choosedVerse = bckChoosedVerse

        reload(ComboBox1, RichTextBox1, choosedVersion1)
        reload(ComboBox2, RichTextBox2, choosedVersion2)
    End Sub

    Private Sub reload(ByVal comboVersion As ComboBox, ByVal rtf As RichTextBox, ByRef choosedVersion As Version)
        Dim ver As Version = DirectCast(comboVersion.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = nudChapter.Value
        Dim verse As Integer = nudVerse.Value

        If ver Is Nothing Or book Is Nothing Or chapter = 0 Or verse = 0 Then
            Return
        End If

        choosedVersion = ver

        Dim osisId As String = Key.GetOsisId(book.book, chapter, verse)

        Dim textContent As String = Module1.Text.GetText(ver.initial, osisId)

        If textContent Is Nothing Then
            With rtf
                .Text = "所选择的版本中没有这个章节！"
                .SelectAll()
                .SelectionFont = New System.Drawing.Font("幼圆", 30.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
                .SelectionColor = Color.Red
            End With
        Else
            With rtf
                .Text = textContent
                .SelectAll()
                .SelectionFont = New System.Drawing.Font("幼圆", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
                .SelectionColor = Color.Black
            End With

            If ver.language.Equals("Hebrew") Then
                rtf.SelectionFont = New System.Drawing.Font("Hebrew", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))

            End If
        End If

        Dim note As Note = note.GetNote(loginedUser.Username, osisId)
        If note Is Nothing Then Return

        rtf.Text += vbCrLf + "您在" + note.timestamp + "留的笔记:" + vbCrLf + note.content
        rtf.Select(Len(textContent), Len(RichTextBox1.Text) - Len(textContent))
        rtf.SelectionColor = Color.Blue

    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        reload(ComboBox1, RichTextBox1,choosedVersion1)

    End Sub

    Private Sub ComboBox2_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox2.SelectedIndexChanged
        reload(ComboBox2, RichTextBox2, choosedVersion2)
    End Sub

    Private Sub nudVerse_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudVerse.ValueChanged
        reload(ComboBox1, RichTextBox1, choosedVersion1)
        reload(ComboBox2, RichTextBox2, choosedVersion2)

        choosedVerse = CInt(nudVerse.Value)
    End Sub

    Private Sub B_Backbrowse_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_Backbrowse.Click
        fbrowse = New 圣经章节概览
        fbrowse.Show()
        Me.Close()
    End Sub

    Private Sub Button1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Button1.Click
        freader = New 经文查询显示
        freader.Show()
        Me.Close()

    End Sub

    Private Sub B_Mark_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_Mark.Click

        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)
        Dim verse As Integer = CInt(nudVerse.Value)

        If book Is Nothing Or chapter = 0 Or verse = 0 Then
            Return
        End If

        Dim osisId As String = Key.GetOsisId(book.book, chapter, verse)

        Dim note As Note = note.GetNote(loginedUser.Username, osisId)

        Dim content As String
        If note Is Nothing Then
            content = ""
        Else
            content = note.content
        End If

        content = InputBox("输入您对这一章节的笔记：", "圣经笔记", content)

        If content Is Nothing Then Return

        note = New Note
        note.username = loginedUser.Username
        note.osisId = osisId
        note.content = content
        note.timestamp = Date.Now

        note.Put()

        reload(ComboBox1, RichTextBox1, choosedVersion1)
        reload(ComboBox2, RichTextBox2, choosedVersion2)
    End Sub
End Class
