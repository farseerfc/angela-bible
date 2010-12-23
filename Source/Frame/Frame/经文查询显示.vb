Public Class 经文查询显示

    Private Sub 经文查询显示_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        loadVersionList()
        loadBookList()
    End Sub

    Private Sub loadVersionList()

    End Sub

    Private Sub loadBookList()
        Dim books As List(Of Book) = Book.GetAllBooks()

        Combo1.Items.Clear()
        Combo1.Items.AddRange(books.ToArray())

    End Sub
End Class