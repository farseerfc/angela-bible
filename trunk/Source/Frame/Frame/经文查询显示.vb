Public Class 经文查询显示

    Private Sub 经文查询显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ComboBox1.Items.Clear()
        ComboBox1.Items.AddRange(Version.GetAllVersions().ToArray())

        Combo1.Items.Clear()
        Combo1.Items.AddRange(Book.GetAllBooks().ToArray())

        If Not choosedVersion1 Is Nothing Then
            For i As Integer = 0 To ComboBox1.Items.Count - 1
                If choosedVersion1.initial.Equals(DirectCast(ComboBox1.Items.Item(i), Version).initial) Then
                    ComboBox1.SelectedIndex = i
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

        reloadText()
    End Sub


    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        choosedVersion1 = ver

        Dim lastSelect As String
        If Combo1.SelectedItem Is Nothing Then
            lastSelect = ""
        Else
            lastSelect = DirectCast(Combo1.SelectedItem, Book).book
        End If

        Combo1.Items.Clear()
        Combo1.Items.AddRange(BookVersion.GetBooksByVersion(ver.initial).ToArray())

        For i As Integer = 0 To Combo1.Items.Count - 1
            If DirectCast(Combo1.Items.Item(i), Book).book.Equals(lastSelect) Then
                Combo1.SelectedIndex = i
            End If
        Next

        reloadText()

    End Sub

    Private Sub Combo1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo1.SelectedIndexChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)

        If ver Is Nothing Or book Is Nothing Then
            Return
        End If

        choosedBook = book

        Dim bckChoosedChapter As Integer = choosedChapter
        Dim nrChapter As Integer = CInt(Key.GetChapterCount(book.book, ver.initial))
        nudChapter.Maximum = nrChapter
        nudChapter.Minimum = 1

        choosedChapter = bckChoosedChapter
        If choosedChapter <= nrChapter And choosedChapter >= 1 Then
            nudChapter.Value = choosedChapter
        End If

        reloadText()
    End Sub

    Private Sub nudChapter_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudChapter.ValueChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)

        If ver Is Nothing Or book Is Nothing Or chapter = 0 Then
            Return
        End If

        choosedChapter = chapter

        Dim bckChoosedVerse As Integer = choosedVerse
        Dim nrVerse As Integer = Key.GetVerseCount(book.book, chapter, ver.initial)
        nudVerse.Maximum = nrVerse
        nudVerse.Minimum = 1
        choosedVerse = bckChoosedVerse

        If choosedVerse <= nrVerse And choosedVerse >= 1 Then
            nudVerse.Value = choosedVerse
        End If

        reloadText()
    End Sub

    Private Sub nudVerse_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudVerse.ValueChanged
        reloadText()
        choosedVerse = CInt(nudVerse.Value)
    End Sub

    Private Sub reloadText()
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)
        Dim verse As Integer = CInt(nudVerse.Value)

        If ver Is Nothing Or book Is Nothing Or chapter = 0 Or verse = 0 Then
            Return
        End If



        Dim osisId As String = Key.GetOsisId(book.book, chapter, verse)
        Dim textContent As String = Module1.Text.GetText(ver.initial, osisId)
        RichTextBox1.Text = textContent

        Dim note As Note = note.GetNote(loginedUser.Username, osisId)
        If note Is Nothing Then Return

        RichTextBox1.Text += vbCrLf + "您在" + note.timestamp+"留的笔记:" + vbCrLf + note.content
        RichTextBox1.Select(Len(textContent), Len(RichTextBox1.Text) - Len(textContent))
        RichTextBox1.SelectionColor = Color.Blue


    End Sub

    Private Sub bhome_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()
    End Sub

    Private Sub B_Backbrowse_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_Backbrowse.Click
        fcompare = New 经文对比显示
        fcompare.Show()
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

        note.put()

        reloadText()
    End Sub

    Private Sub bexit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        fsearch = New 经文精确搜索
        fsearch.Show()
        Me.Close()
    End Sub
End Class