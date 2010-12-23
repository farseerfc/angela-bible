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
        Me.Label2 = New System.Windows.Forms.Label
        Me.B_ChapterDown = New System.Windows.Forms.Button
        Me.B_ChapterUp = New System.Windows.Forms.Button
        Me.Label3 = New System.Windows.Forms.Label
        Me.B_VerseDown = New System.Windows.Forms.Button
        Me.B_VerseUp = New System.Windows.Forms.Button
        Me.B_Backbrowse = New System.Windows.Forms.Button
        Me.Cancel = New System.Windows.Forms.Button
        Me.Button6 = New System.Windows.Forms.Button
        Me.B_Mark = New System.Windows.Forms.Button
        Me.ComboBox1 = New System.Windows.Forms.ComboBox
        Me.SuspendLayout()
        '
        'RichTextBox1
        '
        Me.RichTextBox1.Location = New System.Drawing.Point(99, 49)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.Size = New System.Drawing.Size(579, 249)
        Me.RichTextBox1.TabIndex = 1
        Me.RichTextBox1.Text = ""
        '
        'Combo1
        '
        Me.Combo1.BackColor = System.Drawing.Color.SkyBlue
        Me.Combo1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo1.Font = New System.Drawing.Font("华文行楷", 15.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo1.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(389, 14)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(180, 29)
        Me.Combo1.TabIndex = 20
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.BackColor = System.Drawing.Color.SkyBlue
        Me.Label2.Font = New System.Drawing.Font("幼圆", 16.0!)
        Me.Label2.ForeColor = System.Drawing.Color.Black
        Me.Label2.Location = New System.Drawing.Point(308, 16)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(32, 22)
        Me.Label2.TabIndex = 25
        Me.Label2.Text = "章"
        '
        'B_ChapterDown
        '
        Me.B_ChapterDown.Image = CType(resources.GetObject("B_ChapterDown.Image"), System.Drawing.Image)
        Me.B_ChapterDown.Location = New System.Drawing.Point(341, 11)
        Me.B_ChapterDown.Name = "B_ChapterDown"
        Me.B_ChapterDown.Size = New System.Drawing.Size(33, 33)
        Me.B_ChapterDown.TabIndex = 24
        Me.B_ChapterDown.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_ChapterDown.UseVisualStyleBackColor = True
        '
        'B_ChapterUp
        '
        Me.B_ChapterUp.Image = CType(resources.GetObject("B_ChapterUp.Image"), System.Drawing.Image)
        Me.B_ChapterUp.Location = New System.Drawing.Point(274, 12)
        Me.B_ChapterUp.Name = "B_ChapterUp"
        Me.B_ChapterUp.Size = New System.Drawing.Size(33, 33)
        Me.B_ChapterUp.TabIndex = 23
        Me.B_ChapterUp.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_ChapterUp.UseVisualStyleBackColor = True
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.SkyBlue
        Me.Label3.Font = New System.Drawing.Font("幼圆", 16.0!)
        Me.Label3.ForeColor = System.Drawing.Color.Black
        Me.Label3.Location = New System.Drawing.Point(610, 14)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 22)
        Me.Label3.TabIndex = 28
        Me.Label3.Text = "节"
        '
        'B_VerseDown
        '
        Me.B_VerseDown.Image = CType(resources.GetObject("B_VerseDown.Image"), System.Drawing.Image)
        Me.B_VerseDown.Location = New System.Drawing.Point(645, 12)
        Me.B_VerseDown.Name = "B_VerseDown"
        Me.B_VerseDown.Size = New System.Drawing.Size(33, 33)
        Me.B_VerseDown.TabIndex = 27
        Me.B_VerseDown.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_VerseDown.UseVisualStyleBackColor = True
        '
        'B_VerseUp
        '
        Me.B_VerseUp.Image = CType(resources.GetObject("B_VerseUp.Image"), System.Drawing.Image)
        Me.B_VerseUp.Location = New System.Drawing.Point(575, 11)
        Me.B_VerseUp.Name = "B_VerseUp"
        Me.B_VerseUp.Size = New System.Drawing.Size(33, 33)
        Me.B_VerseUp.TabIndex = 26
        Me.B_VerseUp.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_VerseUp.UseVisualStyleBackColor = True
        '
        'B_Backbrowse
        '
        Me.B_Backbrowse.Image = CType(resources.GetObject("B_Backbrowse.Image"), System.Drawing.Image)
        Me.B_Backbrowse.Location = New System.Drawing.Point(60, 88)
        Me.B_Backbrowse.Name = "B_Backbrowse"
        Me.B_Backbrowse.Size = New System.Drawing.Size(33, 33)
        Me.B_Backbrowse.TabIndex = 32
        Me.B_Backbrowse.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Backbrowse.UseVisualStyleBackColor = True
        '
        'Cancel
        '
        Me.Cancel.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.Cancel.Font = New System.Drawing.Font("幼圆", 10.5!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Cancel.ForeColor = System.Drawing.Color.LightSkyBlue
        Me.Cancel.Image = CType(resources.GetObject("Cancel.Image"), System.Drawing.Image)
        Me.Cancel.Location = New System.Drawing.Point(60, 166)
        Me.Cancel.Name = "Cancel"
        Me.Cancel.Size = New System.Drawing.Size(33, 35)
        Me.Cancel.TabIndex = 31
        Me.Cancel.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Cancel.UseVisualStyleBackColor = False
        '
        'Button6
        '
        Me.Button6.Image = CType(resources.GetObject("Button6.Image"), System.Drawing.Image)
        Me.Button6.Location = New System.Drawing.Point(59, 127)
        Me.Button6.Name = "Button6"
        Me.Button6.Size = New System.Drawing.Size(33, 33)
        Me.Button6.TabIndex = 30
        Me.Button6.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button6.UseVisualStyleBackColor = True
        '
        'B_Mark
        '
        Me.B_Mark.Image = CType(resources.GetObject("B_Mark.Image"), System.Drawing.Image)
        Me.B_Mark.Location = New System.Drawing.Point(60, 49)
        Me.B_Mark.Name = "B_Mark"
        Me.B_Mark.Size = New System.Drawing.Size(33, 33)
        Me.B_Mark.TabIndex = 29
        Me.B_Mark.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.B_Mark.UseVisualStyleBackColor = True
        '
        'ComboBox1
        '
        Me.ComboBox1.BackColor = System.Drawing.Color.MidnightBlue
        Me.ComboBox1.Font = New System.Drawing.Font("华文楷体", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.ComboBox1.ForeColor = System.Drawing.Color.White
        Me.ComboBox1.FormattingEnabled = True
        Me.ComboBox1.Items.AddRange(New Object() {"中文和合本", "德语版", "希伯来原文", "ESV", "KJV", "ISV", "BWE", "WEB", "JST"})
        Me.ComboBox1.Location = New System.Drawing.Point(21, 14)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(212, 29)
        Me.ComboBox1.TabIndex = 33
        Me.ComboBox1.Text = "查看其他圣经版本"
        '
        '经文查询显示
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(702, 525)
        Me.Controls.Add(Me.ComboBox1)
        Me.Controls.Add(Me.B_Backbrowse)
        Me.Controls.Add(Me.Cancel)
        Me.Controls.Add(Me.Button6)
        Me.Controls.Add(Me.B_Mark)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.B_VerseDown)
        Me.Controls.Add(Me.B_VerseUp)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.B_ChapterDown)
        Me.Controls.Add(Me.B_ChapterUp)
        Me.Controls.Add(Me.Combo1)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Name = "经文查询显示"
        Me.Text = "经文查询显示"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
    Friend WithEvents Combo1 As System.Windows.Forms.ComboBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents B_ChapterDown As System.Windows.Forms.Button
    Friend WithEvents B_ChapterUp As System.Windows.Forms.Button
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents B_VerseDown As System.Windows.Forms.Button
    Friend WithEvents B_VerseUp As System.Windows.Forms.Button
    Friend WithEvents B_Backbrowse As System.Windows.Forms.Button
    Friend WithEvents Cancel As System.Windows.Forms.Button
    Friend WithEvents Button6 As System.Windows.Forms.Button
    Friend WithEvents B_Mark As System.Windows.Forms.Button
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
End Class
