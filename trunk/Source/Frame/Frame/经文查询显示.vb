Public Class 经文查询显示

    Private Sub 经文查询显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ComboBox1.Items.Clear()
        ComboBox1.Items.AddRange(Version.GetAllVersions().ToArray())


        Dim books As List(Of Book) = Book.GetAllBooks()

        Combo1.Items.Clear()
        Combo1.Items.AddRange(books.ToArray())

        reloadText()
    End Sub


    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)

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

        Dim nrChapter As Integer = CInt(Key.GetChapterCount(book.book, ver.initial))
        nudChapter.Maximum = nrChapter
        nudChapter.Minimum = 1

        reloadText()
    End Sub

    Private Sub nudChapter_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudChapter.ValueChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)

        If ver Is Nothing Or book Is Nothing Or chapter = 0 Then
            Return
        End If

        Dim nrVerse As Integer = Key.GetVerseCount(book.book, chapter, ver.initial)
        nudVerse.Maximum = nrVerse
        nudVerse.Minimum = 1


        reloadText()
    End Sub

    Private Sub nudVerse_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudVerse.ValueChanged
        reloadText()

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
    End Sub

    Private Sub bhome_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()
    End Sub

    Private Sub B_Backbrowse_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles B_Backbrowse.Click
        fsearch = New 经文精确搜索
        fsearch.Show()
        Me.Close()
    End Sub
End Class