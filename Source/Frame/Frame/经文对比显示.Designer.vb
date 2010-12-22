<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 经文对比显示
    Inherits System.Windows.Forms.Form

    'Form 重写 Dispose，以清理组件列表。
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Windows 窗体设计器所必需的
    Private components As System.ComponentModel.IContainer

    '注意: 以下过程是 Windows 窗体设计器所必需的
    '可以使用 Windows 窗体设计器修改它。
    '不要使用代码编辑器修改它。
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(经文对比显示))
        Me.RichTextBox1 = New System.Windows.Forms.RichTextBox
        Me.ImageList1 = New System.Windows.Forms.ImageList(Me.components)
        Me.B_ChapterUp = New System.Windows.Forms.Button
        Me.B_ChapterDown = New System.Windows.Forms.Button
        Me.B_VerseUp = New System.Windows.Forms.Button
        Me.B_VerseDown = New System.Windows.Forms.Button
        Me.B_Mark = New System.Windows.Forms.Button
        Me.Button6 = New System.Windows.Forms.Button
        Me.Cancel = New System.Windows.Forms.Button
        Me.B_Backbrowse = New System.Windows.Forms.Button
        Me.Label1 = New System.Windows.Forms.Label
        Me.Timer1 = New System.Windows.Forms.Timer(Me.components)
        Me.RichTextBox2 = New System.Windows.Forms.RichTextBox
        Me.ComboBox1 = New System.Windows.Forms.ComboBox
        Me.Combo1 = New System.Windows.Forms.ComboBox
        Me.Button1 = New System.Windows.Forms.Button
        Me.ComboBox2 = New System.Windows.Forms.ComboBox
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label3 = New System.Windows.Forms.Label
        Me.SuspendLayout()
        '
        'RichTextBox1
        '
        Me.RichTextBox1.Location = New System.Drawing.Point(3, 114)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.Size = New System.Drawing.Size(319, 391)
        Me.RichTextBox1.TabIndex = 0
        Me.RichTextBox1.Text = ""
        '
        'ImageList1
        '
        Me.ImageList1.ImageStream = CType(resources.GetObject("ImageList1.ImageStream"), System.Windows.Forms.ImageListStreamer)
        Me.ImageList1.TransparentColor = System.Drawing.Color.Transparent
        Me.ImageList1.Images.SetKeyName(0, "arrow-left48.png")
        Me.ImageList1.Images.SetKeyName(1, "arrow-right48.png")
        Me.ImageList1.Images.SetKeyName(2, "arrow-down48.png")
        Me.ImageList1.Images.SetKeyName(3, "arrow-up48.png")
        Me.ImageList1.Images.SetKeyName(4, "button-rotate-ccw48.png")
        Me.ImageList1.Images.SetKeyName(5, "Help-File48.png")
        '
        'B_ChapterUp
        '
        Me.B_ChapterUp.Image = CType(resources.GetObject("B_ChapterUp.Image"), System.Drawing.Image)
        Me.B_ChapterUp.Location = New System.Drawing.Point(391, 42)
        Me.B_ChapterUp.Name = "B_ChapterUp"
        Me.B_ChapterUp.Size = New System.Drawing.Size(33, 33)
        Me.B_ChapterUp.TabIndex = 1
        Me.B_ChapterUp.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_ChapterUp.UseVisualStyleBackColor = True
        '
        'B_ChapterDown
        '
        Me.B_ChapterDown.Image = CType(resources.GetObject("B_ChapterDown.Image"), System.Drawing.Image)
        Me.B_ChapterDown.Location = New System.Drawing.Point(458, 41)
        Me.B_ChapterDown.Name = "B_ChapterDown"
        Me.B_ChapterDown.Size = New System.Drawing.Size(33, 33)
        Me.B_ChapterDown.TabIndex = 8
        Me.B_ChapterDown.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_ChapterDown.UseVisualStyleBackColor = True
        '
        'B_VerseUp
        '
        Me.B_VerseUp.Image = CType(resources.GetObject("B_VerseUp.Image"), System.Drawing.Image)
        Me.B_VerseUp.Location = New System.Drawing.Point(391, 78)
        Me.B_VerseUp.Name = "B_VerseUp"
        Me.B_VerseUp.Size = New System.Drawing.Size(33, 33)
        Me.B_VerseUp.TabIndex = 9
        Me.B_VerseUp.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_VerseUp.UseVisualStyleBackColor = True
        '
        'B_VerseDown
        '
        Me.B_VerseDown.Image = CType(resources.GetObject("B_VerseDown.Image"), System.Drawing.Image)
        Me.B_VerseDown.Location = New System.Drawing.Point(458, 80)
        Me.B_VerseDown.Name = "B_VerseDown"
        Me.B_VerseDown.Size = New System.Drawing.Size(33, 33)
        Me.B_VerseDown.TabIndex = 10
        Me.B_VerseDown.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_VerseDown.UseVisualStyleBackColor = True
        '
        'B_Mark
        '
        Me.B_Mark.Image = CType(resources.GetObject("B_Mark.Image"), System.Drawing.Image)
        Me.B_Mark.Location = New System.Drawing.Point(334, 226)
        Me.B_Mark.Name = "B_Mark"
        Me.B_Mark.Size = New System.Drawing.Size(33, 33)
        Me.B_Mark.TabIndex = 11
        Me.B_Mark.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Mark.UseVisualStyleBackColor = True
        '
        'Button6
        '
        Me.Button6.Image = CType(resources.GetObject("Button6.Image"), System.Drawing.Image)
        Me.Button6.Location = New System.Drawing.Point(333, 304)
        Me.Button6.Name = "Button6"
        Me.Button6.Size = New System.Drawing.Size(33, 33)
        Me.Button6.TabIndex = 12
        Me.Button6.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button6.UseVisualStyleBackColor = True
        '
        'Cancel
        '
        Me.Cancel.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.Cancel.Font = New System.Drawing.Font("幼圆", 10.5!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Cancel.ForeColor = System.Drawing.Color.LightSkyBlue
        Me.Cancel.Image = CType(resources.GetObject("Cancel.Image"), System.Drawing.Image)
        Me.Cancel.Location = New System.Drawing.Point(334, 464)
        Me.Cancel.Name = "Cancel"
        Me.Cancel.Size = New System.Drawing.Size(39, 40)
        Me.Cancel.TabIndex = 13
        Me.Cancel.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Cancel.UseVisualStyleBackColor = False
        '
        'B_Backbrowse
        '
        Me.B_Backbrowse.Image = CType(resources.GetObject("B_Backbrowse.Image"), System.Drawing.Image)
        Me.B_Backbrowse.Location = New System.Drawing.Point(334, 265)
        Me.B_Backbrowse.Name = "B_Backbrowse"
        Me.B_Backbrowse.Size = New System.Drawing.Size(33, 33)
        Me.B_Backbrowse.TabIndex = 14
        Me.B_Backbrowse.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Backbrowse.UseVisualStyleBackColor = True
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.BackColor = System.Drawing.Color.Transparent
        Me.Label1.Font = New System.Drawing.Font("华文行楷", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label1.ForeColor = System.Drawing.Color.Yellow
        Me.Label1.Location = New System.Drawing.Point(8, 16)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(877, 22)
        Me.Label1.TabIndex = 15
        Me.Label1.Text = "你必将生命的道路指示我。在你面前有满足的喜乐。在你右手中有永远的福乐。——诗篇16：11" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10)
        '
        'Timer1
        '
        '
        'RichTextBox2
        '
        Me.RichTextBox2.Location = New System.Drawing.Point(379, 115)
        Me.RichTextBox2.Name = "RichTextBox2"
        Me.RichTextBox2.Size = New System.Drawing.Size(319, 391)
        Me.RichTextBox2.TabIndex = 16
        Me.RichTextBox2.Text = ""
        '
        'ComboBox1
        '
        Me.ComboBox1.BackColor = System.Drawing.Color.MidnightBlue
        Me.ComboBox1.Font = New System.Drawing.Font("华文楷体", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.ComboBox1.ForeColor = System.Drawing.Color.White
        Me.ComboBox1.FormattingEnabled = True
        Me.ComboBox1.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox1.Location = New System.Drawing.Point(4, 79)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(172, 29)
        Me.ComboBox1.TabIndex = 17
        Me.ComboBox1.Text = "请选择圣经版本"
        '
        'Combo1
        '
        Me.Combo1.BackColor = System.Drawing.Color.SkyBlue
        Me.Combo1.Font = New System.Drawing.Font("华文行楷", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo1.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(207, 63)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(180, 29)
        Me.Combo1.TabIndex = 19
        Me.Combo1.Text = "请选择书卷"
        '
        'Button1
        '
        Me.Button1.Image = CType(resources.GetObject("Button1.Image"), System.Drawing.Image)
        Me.Button1.Location = New System.Drawing.Point(334, 187)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(33, 33)
        Me.Button1.TabIndex = 20
        Me.Button1.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button1.UseVisualStyleBackColor = True
        '
        'ComboBox2
        '
        Me.ComboBox2.BackColor = System.Drawing.Color.Indigo
        Me.ComboBox2.Font = New System.Drawing.Font("华文楷体", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.ComboBox2.ForeColor = System.Drawing.Color.White
        Me.ComboBox2.FormattingEnabled = True
        Me.ComboBox2.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox2.Location = New System.Drawing.Point(519, 79)
        Me.ComboBox2.Name = "ComboBox2"
        Me.ComboBox2.Size = New System.Drawing.Size(172, 29)
        Me.ComboBox2.TabIndex = 21
        Me.ComboBox2.Text = "请选择圣经版本"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.BackColor = System.Drawing.Color.SkyBlue
        Me.Label2.Font = New System.Drawing.Font("幼圆", 16.0!)
        Me.Label2.ForeColor = System.Drawing.Color.Black
        Me.Label2.Location = New System.Drawing.Point(425, 46)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(32, 22)
        Me.Label2.TabIndex = 22
        Me.Label2.Text = "章"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.SkyBlue
        Me.Label3.Font = New System.Drawing.Font("幼圆", 16.0!)
        Me.Label3.ForeColor = System.Drawing.Color.Black
        Me.Label3.Location = New System.Drawing.Point(425, 80)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 22)
        Me.Label3.TabIndex = 23
        Me.Label3.Text = "节"
        '
        '经文对比显示
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(703, 514)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.ComboBox2)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.Combo1)
        Me.Controls.Add(Me.ComboBox1)
        Me.Controls.Add(Me.RichTextBox2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.B_Backbrowse)
        Me.Controls.Add(Me.Cancel)
        Me.Controls.Add(Me.Button6)
        Me.Controls.Add(Me.B_Mark)
        Me.Controls.Add(Me.B_VerseDown)
        Me.Controls.Add(Me.B_VerseUp)
        Me.Controls.Add(Me.B_ChapterDown)
        Me.Controls.Add(Me.B_ChapterUp)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Name = "经文对比显示"
        Me.Text = "经文对比显示"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
    Friend WithEvents ImageList1 As System.Windows.Forms.ImageList
    Friend WithEvents B_ChapterUp As System.Windows.Forms.Button
    Friend WithEvents B_ChapterDown As System.Windows.Forms.Button
    Friend WithEvents B_VerseUp As System.Windows.Forms.Button
    Friend WithEvents B_VerseDown As System.Windows.Forms.Button
    Friend WithEvents B_Mark As System.Windows.Forms.Button
    Friend WithEvents Button6 As System.Windows.Forms.Button
    Friend WithEvents Cancel As System.Windows.Forms.Button
    Friend WithEvents B_Backbrowse As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Timer1 As System.Windows.Forms.Timer
    Friend WithEvents RichTextBox2 As System.Windows.Forms.RichTextBox
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
    Friend WithEvents Combo1 As System.Windows.Forms.ComboBox
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents ComboBox2 As System.Windows.Forms.ComboBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
End Class
