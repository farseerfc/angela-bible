Public Class 经文查询显示

    Private Sub 经文查询显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        loadVersionList()
        loadBookList()
    End Sub

    Private Sub loadVersionList()
        ComboBox1.Items.Clear()
        ComboBox1.Items.AddRange(Version.GetAllVersions().ToArray())
    End Sub

    Private Sub loadBookList()
        Dim books As List(Of Book) = Book.GetAllBooks()

        Combo1.Items.Clear()
        Combo1.Items.AddRange(books.ToArray())

    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBox1.SelectedIndexChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)

        Combo1.Items.Clear()
        Combo1.Items.AddRange(BookVersion.GetBooksByVersion(ver.initial).ToArray())

    End Sub

    Private Sub Combo1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo1.SelectedIndexChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)

        Dim nrChapter As Integer = CInt(Key.GetChapterCount(book.book, ver.initial))
        nudChapter.Maximum = nrChapter
        nudChapter.Minimum = 1
    End Sub

    Private Sub nudChapter_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudChapter.ValueChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)

        Dim nrVerse As Integer = Key.GetVerseCount(book.book, chapter, ver.initial)
        nudVerse.Maximum = nrVerse
        nudVerse.Minimum = 1

    End Sub

    Private Sub nudVerse_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles nudVerse.ValueChanged
        Dim ver As Version = DirectCast(ComboBox1.SelectedItem, Version)
        Dim book As Book = DirectCast(Combo1.SelectedItem, Book)
        Dim chapter As Integer = CInt(nudChapter.Value)
        Dim verse As Integer = CInt(nudVerse.Value)

        Dim osisId As String = Key.GetOsisId(book.book, chapter, verse)
        Dim textContent As String = Module1.Text.GetText(ver.initial, osisId)
        RichTextBox1.Text = textContent

    End Sub
End Class