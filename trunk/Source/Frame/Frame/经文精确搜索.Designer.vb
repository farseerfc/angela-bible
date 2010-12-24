<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 经文精确搜索
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(经文精确搜索))
        Me.GroupBox1 = New System.Windows.Forms.GroupBox
        Me.ComboBox1 = New System.Windows.Forms.ComboBox
        Me.Label3 = New System.Windows.Forms.Label
        Me.Combo2 = New System.Windows.Forms.ComboBox
        Me.Combo3 = New System.Windows.Forms.ComboBox
        Me.Button1 = New System.Windows.Forms.Button
        Me.Label4 = New System.Windows.Forms.Label
        Me.Combo1 = New System.Windows.Forms.ComboBox
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label1 = New System.Windows.Forms.Label
        Me.Button2 = New System.Windows.Forms.Button
        Me.Button3 = New System.Windows.Forms.Button
        Me.GroupBox1.SuspendLayout()
        Me.SuspendLayout()
        '
        'GroupBox1
        '
        Me.GroupBox1.BackgroundImage = CType(resources.GetObject("GroupBox1.BackgroundImage"), System.Drawing.Image)
        Me.GroupBox1.Controls.Add(Me.ComboBox1)
        Me.GroupBox1.Controls.Add(Me.Label3)
        Me.GroupBox1.Controls.Add(Me.Combo2)
        Me.GroupBox1.Controls.Add(Me.Combo3)
        Me.GroupBox1.Controls.Add(Me.Button1)
        Me.GroupBox1.Controls.Add(Me.Label4)
        Me.GroupBox1.Controls.Add(Me.Combo1)
        Me.GroupBox1.Controls.Add(Me.Label2)
        Me.GroupBox1.Controls.Add(Me.Label1)
        Me.GroupBox1.Font = New System.Drawing.Font("隶书", 24.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.GroupBox1.ForeColor = System.Drawing.Color.Orange
        Me.GroupBox1.Location = New System.Drawing.Point(8, 25)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(442, 288)
        Me.GroupBox1.TabIndex = 1
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "经文精确定位查询"
        '
        'ComboBox1
        '
        Me.ComboBox1.BackColor = System.Drawing.Color.FromArgb(CType(CType(255, Byte), Integer), CType(CType(255, Byte), Integer), CType(CType(128, Byte), Integer))
        Me.ComboBox1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox1.Font = New System.Drawing.Font("隶书", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.ComboBox1.Location = New System.Drawing.Point(219, 194)
        Me.ComboBox1.Name = "ComboBox1"
        Me.ComboBox1.Size = New System.Drawing.Size(51, 24)
        Me.ComboBox1.TabIndex = 11
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.BackColor = System.Drawing.Color.Black
        Me.Label3.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label3.ForeColor = System.Drawing.Color.Yellow
        Me.Label3.Location = New System.Drawing.Point(95, 199)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(85, 19)
        Me.Label3.TabIndex = 10
        Me.Label3.Text = "所在节号"
        '
        'Combo2
        '
        Me.Combo2.BackColor = System.Drawing.Color.FromArgb(CType(CType(255, Byte), Integer), CType(CType(255, Byte), Integer), CType(CType(128, Byte), Integer))
        Me.Combo2.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo2.Font = New System.Drawing.Font("隶书", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo2.Location = New System.Drawing.Point(147, 149)
        Me.Combo2.Name = "Combo2"
        Me.Combo2.Size = New System.Drawing.Size(51, 24)
        Me.Combo2.TabIndex = 8
        '
        'Combo3
        '
        Me.Combo3.BackColor = System.Drawing.Color.Yellow
        Me.Combo3.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo3.Font = New System.Drawing.Font("隶书", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo3.FormattingEnabled = True
        Me.Combo3.Location = New System.Drawing.Point(239, 50)
        Me.Combo3.Name = "Combo3"
        Me.Combo3.Size = New System.Drawing.Size(178, 24)
        Me.Combo3.TabIndex = 7
        '
        'Button1
        '
        Me.Button1.BackColor = System.Drawing.Color.DarkOrange
        Me.Button1.Font = New System.Drawing.Font("隶书", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Button1.ForeColor = System.Drawing.Color.OldLace
        Me.Button1.Image = CType(resources.GetObject("Button1.Image"), System.Drawing.Image)
        Me.Button1.Location = New System.Drawing.Point(348, 241)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(88, 41)
        Me.Button1.TabIndex = 9
        Me.Button1.Text = "查询"
        Me.Button1.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button1.UseVisualStyleBackColor = False
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.BackColor = System.Drawing.Color.Black
        Me.Label4.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label4.ForeColor = System.Drawing.Color.Yellow
        Me.Label4.Location = New System.Drawing.Point(6, 50)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(218, 19)
        Me.Label4.TabIndex = 6
        Me.Label4.Text = "您想查询的圣经语言版本"
        '
        'Combo1
        '
        Me.Combo1.BackColor = System.Drawing.Color.Gold
        Me.Combo1.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo1.Font = New System.Drawing.Font("楷体", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(154, 100)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(218, 24)
        Me.Combo1.TabIndex = 2
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.BackColor = System.Drawing.Color.Black
        Me.Label2.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label2.ForeColor = System.Drawing.Color.Yellow
        Me.Label2.Location = New System.Drawing.Point(23, 154)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 19)
        Me.Label2.TabIndex = 1
        Me.Label2.Text = "所在章号"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.BackColor = System.Drawing.Color.Black
        Me.Label1.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label1.ForeColor = System.Drawing.Color.Yellow
        Me.Label1.Location = New System.Drawing.Point(6, 100)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(142, 19)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "您想查阅的书卷"
        '
        'Button2
        '
        Me.Button2.BackColor = System.Drawing.Color.DarkOrange
        Me.Button2.Font = New System.Drawing.Font("隶书", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Button2.ForeColor = System.Drawing.Color.OldLace
        Me.Button2.Image = CType(resources.GetObject("Button2.Image"), System.Drawing.Image)
        Me.Button2.Location = New System.Drawing.Point(224, 319)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(140, 41)
        Me.Button2.TabIndex = 10
        Me.Button2.Text = "返回主页"
        Me.Button2.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button2.UseVisualStyleBackColor = False
        '
        'Button3
        '
        Me.Button3.BackColor = System.Drawing.Color.DarkOrange
        Me.Button3.Font = New System.Drawing.Font("隶书", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Button3.ForeColor = System.Drawing.Color.OldLace
        Me.Button3.Image = CType(resources.GetObject("Button3.Image"), System.Drawing.Image)
        Me.Button3.Location = New System.Drawing.Point(370, 319)
        Me.Button3.Name = "Button3"
        Me.Button3.Size = New System.Drawing.Size(89, 41)
        Me.Button3.TabIndex = 11
        Me.Button3.Text = "帮助"
        Me.Button3.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button3.UseVisualStyleBackColor = False
        '
        '经文精确搜索
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackColor = System.Drawing.Color.Black
        Me.ClientSize = New System.Drawing.Size(462, 364)
        Me.Controls.Add(Me.Button3)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.GroupBox1)
        Me.Name = "经文精确搜索"
        Me.Text = "经文精确搜索"
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents Combo2 As System.Windows.Forms.ComboBox
    Friend WithEvents Combo3 As System.Windows.Forms.ComboBox
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Combo1 As System.Windows.Forms.ComboBox
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents Button2 As System.Windows.Forms.Button
    Friend WithEvents Button3 As System.Windows.Forms.Button
    Friend WithEvents ComboBox1 As System.Windows.Forms.ComboBox
    Friend WithEvents Label3 As System.Windows.Forms.Label
End Class
