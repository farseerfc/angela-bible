Imports System.Data.SqlClient
Public Class 经文搜索
    Inherits System.Windows.Forms.Form
    Private Sub 经文搜索_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Dim bible() As String = {"创世纪 (Genesis) ", "出埃及记 (Exodus) ", "利未记 (Leviticus) ", "民数记 (Numbers) ", "申命记 (Deuteronomy) ", "约书亚记 (Joshua) ", "士师记 (Judges) ", "路得记 (Ruth) ", "撒母耳记上(1 Samuel) ", "撒母耳记下(2 Samuel) ", "列王纪上(1 Kings) ", "列王纪下(2 Kings) ", "历代志上(1 Chronicles) ", "历代志下(2 Chronicles) ", "以斯拉记(Ezra) ", "尼希米记(Nehemiah) ", "以斯帖记 (Esther) ", "约伯记 (Job) ", "诗篇 (Psalms) ", "箴言 (Proverbs) ", "传道书 (Ecclesiastes) ", "雅歌 (Song of Songs) ", "以赛亚书 (Isaiah) ", "耶利米书 (Jeremiahr) ", "耶利米哀歌(Lamentations) ", "以西结书 (Ezekielk) ", "何西阿书 (Hosea) ", "约珥书 (Joel) ", "阿摩司书 (Amoss) ", "俄巴底亚书 (Obadiah) ", "约拿书 (Jonah) ", "弥迦书 (Micah) ", "那鸿书 (Nahum) ", "哈巴谷书 (Habakkuk) ", "西番雅书 (Zephaniah) ", "哈该书 (Haggai) ", "撒迦利亚书 (Zechariah) ", "玛拉基书 (Malachi) ", "马太福音 (Matthew) ", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians) ", "歌罗西书 (Colossians) ", "帖撒罗尼迦前书 (1 Thessalonians) ", "帖撒罗尼迦后书 (2 Thessalonians) ", "提摩太前书 (1 Timothy) ", "提摩太后书 (2 Timothy) ", "提多书 (Titus) ", "腓利门书 (Philemon) ", "希伯来书 (Hebrews) ", "雅各书 (James) ", "彼得前书(1 Peter) ", "彼得后书(2 Peter) ", "约翰一书(1 John) ", "约翰二书(2 John) ", "约翰三书(3 John) ", "犹大书 (Jude) ", "启示录 (Revelation)"}
        Dim i As Integer
        For i = 0 To bible.GetLength(0) - 1
            Combo1.Items.Add(bible(i))
        Next

    End Sub

    Private Sub Combo1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles Combo1.SelectedIndexChanged
        '1 "创世纪 (Genesis)" 
        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '2"出埃及记 (Exodus) "
        If Combo1.Text = "出埃及记 (Exodus) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '3"利未记 (Leviticus) "
        If Combo1.Text = "利未记 (Leviticus) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '4"民数记 (Numbers) "
        If Combo1.Text = "民数记 (Numbers) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '5"申命记 (Deuteronomy) "
        If Combo1.Text = "申命记 (Deuteronomy) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '6 "约书亚记 (Joshua) "
        If Combo1.Text = "约书亚记 (Joshua) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '7"士师记 (Judges) "
        If Combo1.Text = "士师记 (Judges) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '8"路得记 (Ruth) "
        If Combo1.Text = "路得记 (Ruth) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '9"撒母耳记上(1 Samuel) "
        If Combo1.Text = "撒母耳记上(1 Samuel) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        '10"撒母耳记下(2 Samuel) "
        If Combo1.Text = "撒母耳记下(2 Samuel) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '11 "列王纪上(1 Kings) ",
        If Combo1.Text = " " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '12"列王纪下(2 Kings) "
        If Combo1.Text = "列王纪下(2 Kings) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '13历代志上(1 Chronicles)
        If Combo1.Text = "历代志上(1 Chronicles) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '14历代志下(2 Chronicles)
        If Combo1.Text = "历代志下(2 Chronicles)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '15 "以斯拉记(Ezra) "
        If Combo1.Text = "以斯拉记(Ezra) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '16尼希米记(Nehemiah) "
        If Combo1.Text = "尼希米记(Nehemiah)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '17 "以斯帖记 (Esther) "
        If Combo1.Text = "以斯帖记 (Esther) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '18 "约伯记 (Job) "
        If Combo1.Text = "约伯记 (Job) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '19"诗篇 (Psalms) "
        If Combo1.Text = "诗篇 (Psalms) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '20"箴言 (Proverbs) "
        If Combo1.Text = "箴言 (Proverbs) " Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '"传道书 (Ecclesiastes) "
        If Combo1.Text = "传道书 (Ecclesiastes)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        ' "雅歌 (Song of Songs) "
        If Combo1.Text = "雅歌 (Song of Songs)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '"以赛亚书 (Isaiah) ",
        If Combo1.Text = "以赛亚书 (Isaiah)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If
        '"耶利米书 (Jeremiahr) "
        If Combo1.Text = "耶利米书 (Jeremiahr)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

        If Combo1.Text = "创世纪 (Genesis)" Then
            Combo2.Items.Clear()
            Combo2.Text = ""
            Dim bible() As String = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"}
            Dim i As Integer
            For i = 0 To bible.GetLength(0) - 1
                Combo2.Items.Add(bible(i))
            Next
        End If

    End Sub
End Class