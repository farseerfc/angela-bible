<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 经文查询显示
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(经文查询显示))
        Me.RichTextBox1 = New System.Windows.Forms.RichTextBox
        Me.Combo1 = New System.Windows.Forms.ComboBox
        Me.B_Backbrowse = New System.Windows.Forms.Button
        Me.bexit = New System.Windows.Forms.Button
        Me.bhome = New System.Windows.Forms.Button
        Me.B_Mark = New System.Windows.Forms.Button
        Me.ComboBox1 = New System.Windows.Forms.ComboBox
        Me.nudChapter = New System.Windows.Forms.NumericUpDown
        Me.nudVerse = New System.Windows.Forms.NumericUpDown
        Me.Label1 = New System.Windows.Forms.Label
        Me.Label4 = New System.Windows.Forms.Label
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label3 = New System.Windows.Forms.Label
        Me.Label5 = New System.Windows.Forms.Label
        Me.Label6 = New System.Windows.Forms.Label
        CType(Me.nudChapter, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.nudVerse, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'RichTextBox1
        '
        Me.RichTextBox1.Font = New System.Drawing.Font("Comic Sans MS", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.RichTextBox1.Location = New System.Drawing.Point(81, 117)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.Size = New System.Drawing.Size(382, 249)
        Me.RichTextBox1.TabIndex = 1
        Me.RichTextBox1.Text = ""
        '
        'Combo1
        '
        Me.Combo1.BackColor = System.Drawing.Color.SkyBlue
        Me.Combo1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo1.Font = New System.Drawing.Font("华文楷体", 13.0!)
        Me.Combo1.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(112, 67)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(212, 27)
        Me.Combo1.TabIndex = 20
        '
        'B_Backbrowse
        '
        Me.B_Backbrowse.Image = CType(resources.GetObject("B_Backbrowse.Image"), System.Drawing.Image)
        Me.B_Backbrowse.Location = New System.Drawing.Point(21, 156)
        Me.B_Backbrowse.Name = "B_Backbrowse"
        Me.B_Backbrowse.Size = New System.Drawing.Size(33, 33)
        Me.B_Backbrowse.TabIndex = 32
        Me.B_Backbrowse.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Backbrowse.UseVisualStyleBackColor = True
        '
        'bexit
        '
        Me.bexit.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.bexit.Font = New System.Drawing.Font("幼圆", 10.5!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.bexit.ForeColor = System.Drawing.Color.LightSkyBlue
        Me.bexit.Image = CType(resources.GetObject("bexit.Image"), System.Drawing.Image)
        Me.bexit.Location = New System.Drawing.Point(21, 234)
        Me.bexit.Name = "bexit"
        Me.bexit.Size = New System.Drawing.Size(33, 35)
        Me.bexit.TabIndex = 31
        Me.bexit.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bexit.UseVisualStyleBackColor = False
        '
        'bhome
        '
        Me.bhome.Image = CType(resources.GetObject("bhome.Image"), System.Drawing.Image)
        Me.bhome.Location = New System.Drawing.Point(20, 195)
        Me.bhome.Name = "bhome"
        Me.bhome.Size = New System.Drawing.Size(33, 33)
        Me.bhome.TabIndex = 30
        Me.bhome.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bhome.UseVisualStyleBackColor = True
        '
        'B_Mark
        '
        Me.B_Mark.Image = CType(resources.GetObject("B_Mark.Image"), System.Drawing.Image)
        Me.B_Mark.Location = New System.Drawing.Point(21, 117)
        Me.B_Mark.Name = "B_Mark"
        Me.B_Mark.Size = New System.Drawing.Size(33, 33)
        Me.B_Mark.TabIndex = 29
        Me.B_Mark.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Mark.UseVisualStyleBackColor = True
        '
        'ComboBox1
        '
        Me.ComboBox1.BackColor = System.Drawing.Color.SkyBlue
        Me.ComboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox1.Font = New System.Drawing.Font("Bodoni MT Poster Compressed", 14.0!)
        Me.ComboBox1.ForeColor = System.Drawing.Color.DarkBlue
        Me.ComboBox1.FormattingEnabled = True
        Me.ComboBox1.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox1.Location = New System.Drawing.Point(112, 9)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(212, 31)
        Me.ComboBox1.TabIndex = 33
        '
        'nudChapter
        '
        Me.nudChapter.Font = New System.Drawing.Font("Britannic Bold", 13.25!)
        Me.nudChapter.Location = New System.Drawing.Point(401, 67)
        Me.nudChapter.Name = "nudChapter"
        Me.nudChapter.Size = New System.Drawing.Size(62, 27)
        Me.nudChapter.TabIndex = 34
        '
        'nudVerse
        '
        Me.nudVerse.Font = New System.Drawing.Font("Britannic Bold", 13.25!)
        Me.nudVerse.Location = New System.Drawing.Point(517, 67)
        Me.nudVerse.Name = "nudVerse"
        Me.nudVerse.Size = New System.Drawing.Size(62, 27)
        Me.nudVerse.TabIndex = 35
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.BackColor = System.Drawing.Color.Transparent
        Me.Label1.Font = New System.Drawing.Font("楷体", 14.25!, CType((System.Drawing.FontStyle.Bold Or System.Drawing.FontStyle.Italic), System.Drawing.FontStyle), System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label1.ForeColor = System.Drawing.Color.FromArgb(CType(CType(255, Byte), Integer), CType(CType(128, Byte), Integer), CType(CType(0, Byte), Integer))
        Me.Label1.Location = New System.Drawing.Point(12, 369)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(405, 95)
        Me.Label1.TabIndex = 36
        Me.Label1.Text = "My soul clings to you, " & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "        your right hand upholds me! " & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "我心紧紧地跟随你，" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "       " & _
            " 你的右手扶持我！" & Global.Microsoft.VisualBasic.ChrW(13) & Global.Microsoft.VisualBasic.ChrW(10) & "                     -诗篇63:8"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.BackColor = System.Drawing.Color.Black
        Me.Label4.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label4.ForeColor = System.Drawing.Color.Aqua
        Me.Label4.Location = New System.Drawing.Point(12, 17)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(85, 19)
        Me.Label4.TabIndex = 37
        Me.Label4.Text = "圣经版本"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.BackColor = System.Drawing.Color.Black
        Me.Label2.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label2.ForeColor = System.Drawing.Color.Aqua
        Me.Label2.Location = New System.Drawing.Point(12, 67)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 19)
        Me.Label2.TabIndex = 38
        Me.Label2.Text = "书卷选择"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.Black
        Me.Label3.Font = New System.Drawing.Font("华文行楷", 16.25!)
        Me.Label3.ForeColor = System.Drawing.Color.Aqua
        Me.Label3.Location = New System.Drawing.Point(479, 68)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 23)
        Me.Label3.TabIndex = 39
        Me.Label3.Text = "节"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.BackColor = System.Drawing.Color.Black
        Me.Label5.Font = New System.Drawing.Font("华文行楷", 16.25!)
        Me.Label5.ForeColor = System.Drawing.Color.Aqua
        Me.Label5.Location = New System.Drawing.Point(367, 70)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(32, 23)
        Me.Label5.TabIndex = 40
        Me.Label5.Text = "章"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.BackColor = System.Drawing.Color.Transparent
        Me.Label6.Font = New System.Drawing.Font("Freestyle Script", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label6.ForeColor = System.Drawing.Color.Gold
        Me.Label6.Location = New System.Drawing.Point(432, 0)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(258, 23)
        Me.Label6.TabIndex = 41
        Me.Label6.Text = "Designed by Farseer Yang and Angela Liu"
        '
        '经文查询显示
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(702, 474)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.nudVerse)
        Me.Controls.Add(Me.nudChapter)
        Me.Controls.Add(Me.ComboBox1)
        Me.Controls.Add(Me.B_Backbrowse)
        Me.Controls.Add(Me.bexit)
        Me.Controls.Add(Me.bhome)
        Me.Controls.Add(Me.B_Mark)
        Me.Controls.Add(Me.Combo1)
        Me.Controls.Add(Me.RichTextBox1)
        Me.ForeColor = System.Drawing.Color.Yellow
        Me.Name = "经文查询显示"
        Me.Text = "经文查询显示"
        CType(Me.nudChapter, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.nudVerse, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
    Friend WithEvents Combo1 As System.Windows.Forms.ComboBox
    Friend WithEvents B_Backbrowse As System.Windows.Forms.Button
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents bhome As System.Windows.Forms.Button
    Friend WithEvents B_Mark As System.Windows.Forms.Button
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
    Friend WithEvents nudChapter As System.Windows.Forms.NumericUpDown
    Friend WithEvents nudVerse As System.Windows.Forms.NumericUpDown
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
End Class
