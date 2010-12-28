Public Class 圣经版本简介

    Private Sub TreeView1_AfterSelect(ByVal sender As System.Object, ByVal e As System.Windows.Forms.TreeViewEventArgs) Handles Tree_version.AfterSelect
        Me.RichTextBox1.Text = "请在左侧选择您所需要查询的圣经信息或者圣经版本!     欢迎！祝愿您能再这里与主相遇！！"
        RichTextBox1.ForeColor = Color.Orange
        RichTextBox1.Font = New Font("隶书", 25)

        If Me.Tree_version.SelectedNode.Tag = "b_concp" Then
            Me.RichTextBox1.Text = "圣经都是神所默示的(提后三：16)；圣经都是神的默示，不同于人的好教训；是因为其来源的分别。圣经不仅一部分是神的话，而都是，全部是神的话。出于人的话，不能永久存在，因为人不能永久存在；惟有神的话必永久存在，因为神是永恒的神。神能够保守他的话，使它成就。"
            RichTextBox1.ForeColor = Color.Magenta
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "b_orig" Then
            Me.RichTextBox1.Text = "圣经的价值，在于其从哪里来的。圣经是从神的默示来的。[默示]在原文是[神吹气]的意思。气和灵是相同的。圣经是由神的圣灵感动而来，藉著人的手写出他的话。所以能称为神的话。以赛亚书说：凡有血气的尽都如草；他的美容，都像野地的花。草必枯乾，花必凋残，因为耶和华的气吹在其上。百姓诚然是草。草必枯乾，花必凋残；惟有我们神的话，必永远立定(赛四0：7,8)。可见人的存在，是何等的脆弱。神的气吹在其上，就没有了。但神的气吹在属他的人，圣灵感动人写出神的话，却是永存的。这是说，永生的神，藉著不能永存的人，写出了他永存的话，使信的人，得著永远的生命。这是何等的奇妙！"
            RichTextBox1.ForeColor = Color.Magenta
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "b_use" Then
            Me.RichTextBox1.Text = "圣经有其超自然的来源，才会有超自然的功能。有人对基督教这名词避知若浼，其实圣经正是一本属灵的教材，其功能首先是教训，使圣徒知道所当遵行的。因此[从前所写的圣经，都是为教训我们写的](罗一五：4)。 有的人力求避免基督教的[教]字，既无必要，也不可能。                                但人往往有错失，不能达到所要求的标准。因此，不能怕提到罪，也不能拒绝责备罪。我们既不能绝对免除错误，也就需要责备。虽然责备不是人所欢喜的，但是必要的。所以应当求主指教我们，得著智慧的心，因主藉圣经的责备而回转，行他的道路。                       圣经也是我们行天路的指针，使我们不至于如羊走迷，偏行己路。从圣经的标准，我们可以校查到自己的偏差，及时归返正路。所以圣经也是给我们操练行义的典范，使我们好学习行主的道。"
            RichTextBox1.ForeColor = Color.Magenta
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "b_aim" Then
            Me.RichTextBox1.Text = "属神的人为主而活，是主选召我们的目的。为了达成这目的：[要叫我们行善，就是神预备叫我们行的](弗二：10)，必须先得装备完全。    神的话不是叫人来欣赏，也不是叫人建立一个敬虔的行业而已；而是有其更高的旨意，叫我们知所遵行，完成他所命定我们去作的。这就是神赐圣经给我们的目标：充分装备属神的人，以能适合达成这使命。"
            RichTextBox1.ForeColor = Color.Magenta
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "b_app" Then
            Me.RichTextBox1.Text = "今天，我们所以能明白神的事，明白他的心意和计划，都是因为神自己主动地，透过人类所能明白的文字，在圣经里清楚地记载出来。圣经是基督教的经典，是基督徒生活与信仰的最高权威，整本圣经都是神的话，那么我们应该如何来应用呢？                     我们已清楚，圣经是神用来向人类传达他的作为、言语与计划的一本书。但是神为什么要将圣经赐给我们？圣经与耶稣又有什么关系呢？提后三15：「你是从小明白圣经，这圣经能使你因信基督耶稣，有得救的智慧。」由保罗这句话中可见，圣经使提摩太有智慧，对耶稣有信心，并因此而获得救恩。                        所以我们可以肯定的说，神赐圣经给我们，就是要将救恩显明，使我们对耶稣有信心，接受他作救主。约翰则说：「你们查考圣经，因你们以为内中有永主；给我作见证的就是这经。然而，你们不肯到我这里来得生命」（约五39～40），故知犹太人查考圣经，是希望从圣经里找到永远的生命。但耶稣却指责他们说：「你们寻找不到永生，因为你们不到我这里来寻找。」犹太人不知道主耶稣才是生命的源头，更不明白他们查考的圣经，其实就是为耶稣作见证的。因此，总括这两段经文，我们就可以清楚地知道，从圣经所记载的信息，再配合上主耶稣自己的言语行为，都证明耶稣即是人类的救主，是人永远生命的源头。"
            RichTextBox1.ForeColor = Color.Magenta
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If


        If Me.Tree_version.SelectedNode.Tag = "Chi" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "。这本圣经的译本是中国大陆地区最广为使用的基督教圣经版本。1919年出版的中文和合译本圣经， 无疑的， 是神给华人教会的特别恩赐，藉西方宣教士和华人基督徒合作的成果。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "KJV" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "即King James Version，1611年完成，又称詹姆斯国王钦定版圣经，是英语《圣经》第一个通行版本，具有里程碑式的意义，已经成为英国文学的一部分。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "ESV" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "即English Standard Version,ESV是建立在过去500年英语圣经翻译的最优秀的主流的基础上翻译而成。ESV基本上是直译，它尽可能寻求得到原始文本的精确词义和每一圣经作者的个人风格。这样，它着重于词与词的对应，同时考虑到现代英语和原文语言之间在文法、句法和成语方面的差异。寻求原始文本的透明度，让读者尽可能地看到原始文本的结构和意义。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "ISV" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "即 International Standard Version，又称国际标准版圣经。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "BWE" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "即Bible in WorldWide English，"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "WEB" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "即World English Bible，The World English Bible is a Modern English update of the American Standard Version of 1901. "
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "JST" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + ",即Joseph Smith Translation，又称为斯密约瑟译本也称作《圣经》灵感本（英文：Inspired Version），这个版本是以《圣经》英王詹姆士钦定本为脚本，在其上直接以英文直接添增修订部份经文而成。它是摩门信仰中的神圣的经文, 也是基督社区（先前称作重组后的耶稣基督后期圣徒教会）的教会正典。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "German" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "，即德国最通用的基督教圣经版本，由Martin Luther翻译。１５４３年，路德翻译的德文圣经面世了，海涅认为路德对圣经的翻译是【创造了德语】，路德所译的圣经是依照着未经后世篡改的希伯莱文和希腊文原本。他的翻译为人民提供了对抗天主教会的思想武器。从另一种意义上说，他译的圣经使用的是德国语言，这种统一的语言成为联系德意志各邦的重要纽带。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If

        If Me.Tree_version.SelectedNode.Tag = "Herb" Then
            Me.RichTextBox1.Text = Me.Tree_version.SelectedNode.Text + "，希伯来古语圣经。是圣经原本的语言写成。"
            RichTextBox1.ForeColor = Color.Indigo
            RichTextBox1.Font = New Font("华文楷体", 14)
        End If
    End Sub

    Private Sub Button2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bhome.Click
        fhome = New 主页
        fhome.Show()
        Me.Close()
    End Sub

    Private Sub Button4_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles bexit.Click
        Application.Exit()
    End Sub

    Private Sub 圣经版本简介_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load

    End Sub
End Class