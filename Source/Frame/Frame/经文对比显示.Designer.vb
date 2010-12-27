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
        Me.B_Mark = New System.Windows.Forms.Button
        Me.bhome = New System.Windows.Forms.Button
        Me.bexit = New System.Windows.Forms.Button
        Me.B_Backbrowse = New System.Windows.Forms.Button
        Me.Label1 = New System.Windows.Forms.Label
        Me.Timer1 = New System.Windows.Forms.Timer(Me.components)
        Me.RichTextBox2 = New System.Windows.Forms.RichTextBox
        Me.ComboBox1 = New System.Windows.Forms.ComboBox
        Me.Combo1 = New System.Windows.Forms.ComboBox
        Me.Button1 = New System.Windows.Forms.Button
        Me.ComboBox2 = New System.Windows.Forms.ComboBox
        Me.nudChapter = New System.Windows.Forms.NumericUpDown
        Me.nudVerse = New System.Windows.Forms.NumericUpDown
        Me.Label4 = New System.Windows.Forms.Label
        Me.Label5 = New System.Windows.Forms.Label
        Me.Label6 = New System.Windows.Forms.Label
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label3 = New System.Windows.Forms.Label
        CType(Me.nudChapter, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.nudVerse, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'RichTextBox1
        '
        Me.RichTextBox1.Font = New System.Drawing.Font("Comic Sans MS", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.RichTextBox1.Location = New System.Drawing.Point(3, 171)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.ReadOnly = True
        Me.RichTextBox1.Size = New System.Drawing.Size(319, 334)
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
        'bhome
        '
        Me.bhome.Image = CType(resources.GetObject("bhome.Image"), System.Drawing.Image)
        Me.bhome.Location = New System.Drawing.Point(333, 304)
        Me.bhome.Name = "bhome"
        Me.bhome.Size = New System.Drawing.Size(33, 33)
        Me.bhome.TabIndex = 12
        Me.bhome.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bhome.UseVisualStyleBackColor = True
        '
        'bexit
        '
        Me.bexit.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.bexit.Font = New System.Drawing.Font("幼圆", 10.5!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.bexit.ForeColor = System.Drawing.Color.LightSkyBlue
        Me.bexit.Image = CType(resources.GetObject("bexit.Image"), System.Drawing.Image)
        Me.bexit.Location = New System.Drawing.Point(334, 464)
        Me.bexit.Name = "bexit"
        Me.bexit.Size = New System.Drawing.Size(39, 40)
        Me.bexit.TabIndex = 13
        Me.bexit.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bexit.UseVisualStyleBackColor = False
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
        Me.Label1.ForeColor = System.Drawing.Color.Gold
        Me.Label1.Location = New System.Drawing.Point(8, 16)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(493, 44)
        Me.Label1.TabIndex = 15
        Me.Label1.Text = "你必将生命的道路指示我。在你面前有满足的喜乐。" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "        在你右手中有永远的福乐。——诗篇16：11" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10)
        '
        'Timer1
        '
        '
        'RichTextBox2
        '
        Me.RichTextBox2.Font = New System.Drawing.Font("Comic Sans MS", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.RichTextBox2.Location = New System.Drawing.Point(379, 171)
        Me.RichTextBox2.Name = "RichTextBox2"
        Me.RichTextBox2.ReadOnly = True
        Me.RichTextBox2.Size = New System.Drawing.Size(319, 335)
        Me.RichTextBox2.TabIndex = 16
        Me.RichTextBox2.Text = ""
        '
        'ComboBox1
        '
        Me.ComboBox1.BackColor = System.Drawing.Color.LightSkyBlue
        Me.ComboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox1.Font = New System.Drawing.Font("Comic Sans MS", 13.0!, System.Drawing.FontStyle.Bold)
        Me.ComboBox1.ForeColor = System.Drawing.Color.Black
        Me.ComboBox1.FormattingEnabled = True
        Me.ComboBox1.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox1.Location = New System.Drawing.Point(122, 132)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(200, 33)
        Me.ComboBox1.TabIndex = 17
        '
        'Combo1
        '
        Me.Combo1.BackColor = System.Drawing.Color.SkyBlue
        Me.Combo1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo1.Font = New System.Drawing.Font("华文楷体", 13.0!)
        Me.Combo1.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(104, 71)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(180, 27)
        Me.Combo1.TabIndex = 19
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
        Me.ComboBox2.BackColor = System.Drawing.Color.MediumPurple
        Me.ComboBox2.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox2.Font = New System.Drawing.Font("Comic Sans MS", 13.0!, System.Drawing.FontStyle.Bold)
        Me.ComboBox2.ForeColor = System.Drawing.Color.Black
        Me.ComboBox2.FormattingEnabled = True
        Me.ComboBox2.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox2.Location = New System.Drawing.Point(491, 132)
        Me.ComboBox2.Name = "ComboBox2"
        Me.ComboBox2.Size = New System.Drawing.Size(200, 33)
        Me.ComboBox2.TabIndex = 21
        '
        'nudChapter
        '
        Me.nudChapter.Font = New System.Drawing.Font("Comic Sans MS", 13.0!)
        Me.nudChapter.Location = New System.Drawing.Point(379, 71)
        Me.nudChapter.Name = "nudChapter"
        Me.nudChapter.Size = New System.Drawing.Size(71, 32)
        Me.nudChapter.TabIndex = 24
        '
        'nudVerse
        '
        Me.nudVerse.Font = New System.Drawing.Font("Comic Sans MS", 13.0!)
        Me.nudVerse.Location = New System.Drawing.Point(507, 75)
        Me.nudVerse.Name = "nudVerse"
        Me.nudVerse.Size = New System.Drawing.Size(71, 32)
        Me.nudVerse.TabIndex = 25
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.BackColor = System.Drawing.Color.Black
        Me.Label4.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label4.ForeColor = System.Drawing.Color.Aqua
        Me.Label4.Location = New System.Drawing.Point(8, 140)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(90, 19)
        Me.Label4.TabIndex = 38
        Me.Label4.Text = "圣经版本1"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.BackColor = System.Drawing.Color.Black
        Me.Label5.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label5.ForeColor = System.Drawing.Color.Aqua
        Me.Label5.Location = New System.Drawing.Point(375, 140)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(93, 19)
        Me.Label5.TabIndex = 39
        Me.Label5.Text = "圣经版本2"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.BackColor = System.Drawing.Color.Black
        Me.Label6.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label6.ForeColor = System.Drawing.Color.Aqua
        Me.Label6.Location = New System.Drawing.Point(13, 75)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(85, 19)
        Me.Label6.TabIndex = 40
        Me.Label6.Text = "书卷选择"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.BackColor = System.Drawing.Color.Black
        Me.Label2.Font = New System.Drawing.Font("华文行楷", 16.25!)
        Me.Label2.ForeColor = System.Drawing.Color.Aqua
        Me.Label2.Location = New System.Drawing.Point(341, 71)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(32, 23)
        Me.Label2.TabIndex = 41
        Me.Label2.Text = "章"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.Black
        Me.Label3.Font = New System.Drawing.Font("华文行楷", 16.25!)
        Me.Label3.ForeColor = System.Drawing.Color.Aqua
        Me.Label3.Location = New System.Drawing.Point(469, 71)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 23)
        Me.Label3.TabIndex = 42
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
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.nudVerse)
        Me.Controls.Add(Me.nudChapter)
        Me.Controls.Add(Me.ComboBox2)
        Me.Controls.Add(Me.Button1)
        Me.Controls.Add(Me.Combo1)
        Me.Controls.Add(Me.ComboBox1)
        Me.Controls.Add(Me.RichTextBox2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.B_Backbrowse)
        Me.Controls.Add(Me.bexit)
        Me.Controls.Add(Me.bhome)
        Me.Controls.Add(Me.B_Mark)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Name = "经文对比显示"
        Me.Text = "经文对比显示"
        CType(Me.nudChapter, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.nudVerse, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
    Friend WithEvents ImageList1 As System.Windows.Forms.ImageList
    Friend WithEvents B_Mark As System.Windows.Forms.Button
    Friend WithEvents bhome As System.Windows.Forms.Button
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents B_Backbrowse As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Timer1 As System.Windows.Forms.Timer
    Friend WithEvents RichTextBox2 As System.Windows.Forms.RichTextBox
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
    Friend WithEvents Combo1 As System.Windows.Forms.ComboBox
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents ComboBox2 As System.Windows.Forms.ComboBox
    Friend WithEvents nudChapter As System.Windows.Forms.NumericUpDown
    Friend WithEvents nudVerse As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
End Class
