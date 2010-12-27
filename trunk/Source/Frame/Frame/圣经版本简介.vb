Public Class 圣经版本简介

    Private Sub TreeView1_AfterSelect(ByVal sender As System.Object, ByVal e As System.Windows.Forms.TreeViewEventArgs) Handles TreeView1.AfterSelect
        Me.RichTextBox1.Text = "请在左侧选择您所需要查询的圣经版本!"
        RichTextBox1.ForeColor = Color.Orange
        RichTextBox1.Font = New Font("隶书", 25)
        If Me.TreeView1.SelectedNode.Tag = "Chi" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "。这本圣经的译本是中国大陆地区最广为使用的基督教圣经版本。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "KJV" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "即King James Version，1611年完成，又称詹姆斯国王钦定版圣经，是英语《圣经》第一个通行版本，具有里程碑式的意义，已经成为英国文学的一部分。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "ESV" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "即English Standard Version,ESV是建立在过去500年英语圣经翻译的最优秀的主流的基础上翻译而成。ESV基本上是直译，它尽可能寻求得到原始文本的精确词义和每一圣经作者的个人风格。这样，它着重于词与词的对应，同时考虑到现代英语和原文语言之间在文法、句法和成语方面的差异。寻求原始文本的透明度，让读者尽可能地看到原始文本的结构和意义。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "ISV" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "即 International Standard Version，又称国际标准版圣经。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "BWE" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "即Bible in WorldWide English，"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "WEB" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "即World English Bible，The World English Bible is a Modern English update of the American Standard Version of 1901. "
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "JST" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + ",即Joseph Smith Translation，又称为斯密约瑟译本也称作《圣经》灵感本（英文：Inspired Version），这个版本是以《圣经》英王詹姆士钦定本为脚本，在其上直接以英文直接添增修订部份经文而成。它是摩门信仰中的神圣的经文, 也是基督社区（先前称作重组后的耶稣基督后期圣徒教会）的教会正典。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "German" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "，即德国最通用的基督教圣经版本，由Martin Luther翻译。１５４３年，路德翻译的德文圣经面世了，海涅认为路德对圣经的翻译是【创造了德语】，路德所译的圣经是依照着未经后世篡改的希伯莱文和希腊文原本。他的翻译为人民提供了对抗天主教会的思想武器。从另一种意义上说，他译的圣经使用的是德国语言，这种统一的语言成为联系德意志各邦的重要纽带。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.TreeView1.SelectedNode.Tag = "Herb" Then
            Me.RichTextBox1.Text = Me.TreeView1.SelectedNode.Text + "，希伯来古语圣经。是圣经原本的语言写成。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        Me.Hide()
        fhome.Show()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub 圣经版本简介_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class