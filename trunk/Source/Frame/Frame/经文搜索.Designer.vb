﻿<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 经文搜索
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
        Me.GroupBox1 = New System.Windows.Forms.GroupBox
        Me.Combo2 = New System.Windows.Forms.ComboBox
        Me.Combo3 = New System.Windows.Forms.ComboBox
        Me.Label4 = New System.Windows.Forms.Label
        Me.Combo1 = New System.Windows.Forms.ComboBox
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label1 = New System.Windows.Forms.Label
        Me.GroupBox1.SuspendLayout()
        Me.SuspendLayout()
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.Combo2)
        Me.GroupBox1.Controls.Add(Me.Combo3)
        Me.GroupBox1.Controls.Add(Me.Label4)
        Me.GroupBox1.Controls.Add(Me.Combo1)
        Me.GroupBox1.Controls.Add(Me.Label2)
        Me.GroupBox1.Controls.Add(Me.Label1)
        Me.GroupBox1.Font = New System.Drawing.Font("华文行楷", 15.75!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.GroupBox1.Location = New System.Drawing.Point(17, 47)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(442, 288)
        Me.GroupBox1.TabIndex = 1
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "经文精确定位查询"
        '
        'Combo2
        '
        Me.Combo2.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.Combo2.Location = New System.Drawing.Point(287, 65)
        Me.Combo2.Name = "Combo2"
        Me.Combo2.Size = New System.Drawing.Size(51, 30)
        Me.Combo2.TabIndex = 8
        '
        'Combo3
        '
        Me.Combo3.FormattingEnabled = True
        Me.Combo3.Location = New System.Drawing.Point(275, 158)
        Me.Combo3.Name = "Combo3"
        Me.Combo3.Size = New System.Drawing.Size(98, 30)
        Me.Combo3.TabIndex = 7
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label4.Location = New System.Drawing.Point(6, 163)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(218, 19)
        Me.Label4.TabIndex = 6
        Me.Label4.Text = "您想查询的圣经语言版本"
        '
        'Combo1
        '
        Me.Combo1.Font = New System.Drawing.Font("楷体", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Combo1.FormattingEnabled = True
        Me.Combo1.Items.AddRange(New Object() {"创世纪 (Genesis)", "出埃及记 (Exodus)", "利未记 (Leviticus)", "民数记 (Numbers)", "申命记 (Deuteronomy)", "约书亚记 (Joshua)", "士师记 (Judges)", "路得记 (Ruth)", "撒母耳记上(1 Samuel)", "撒母耳记下(2 Samuel)", "列王纪上(1 Kings)", "列王纪下(2 Kings)", "历代志上(1 Chronicles)", "历代志下(2 Chronicles)", "以斯拉记(Ezra)", "尼希米记(Nehemiah)", "以斯帖记 (Esther)", "约伯记 (Job)", "诗篇 (Psalms)", "箴言 (Proverbs)", "传道书 (Ecclesiastes)", "雅歌 (Song of Songs)", "以赛亚书 (Isaiah)", "耶利米书 (Jeremiahr)", "耶利米哀歌(Lamentations)", "以西结书 (Ezekielk)", "何西阿书 (Hosea)", "约珥书 (Joel)", "阿摩司书 (Amoss)", "俄巴底亚书 (Obadiah)", "约拿书 (Jonah)", "弥迦书 (Micah)", "那鸿书 (Nahum)", "哈巴谷书 (Habakkuk)", "西番雅书 (Zephaniah)", "哈该书 (Haggai)", "撒迦利亚书 (Zechariah)", "玛拉基书 (Malachi)", "马太福音 (Matthew)", "马可福音 (Mark)", "路加福音 (Luke)", "约翰福音 (John)", "使徒行传 (Acts)", "罗马书 (Romans, Rom)", "哥林多前书 (1 Corinthians)", "哥林多后书 (2 Corinthians)", "加拉太书 (Galatians)", "以弗所书 (Ephesus)", "腓立比书 (Philippians)", "歌罗西书 (Colossians)", "帖撒罗尼迦前书 (1 Thessalonians)", "帖撒罗尼迦后书 (2 Thessalonians)", "提摩太前书 (1 Timothy)", "提摩太后书 (2 Timothy)", "提多书 (Titus)", "腓利门书 (Philemon)", "希伯来书 (Hebrews) ", "雅各书 (James)", "彼得前书(1 Peter)", "彼得后书(2 Peter)", "约翰一书(1 John)", "约翰二书(2 John)", "约翰三书(3 John)", "犹大书 (Jude)", "启示录 (Revelation)"})
        Me.Combo1.Location = New System.Drawing.Point(6, 65)
        Me.Combo1.Name = "Combo1"
        Me.Combo1.Size = New System.Drawing.Size(218, 24)
        Me.Combo1.TabIndex = 2
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label2.Location = New System.Drawing.Point(219, 38)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 19)
        Me.Label2.TabIndex = 1
        Me.Label2.Text = "所在章号"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Font = New System.Drawing.Font("华文行楷", 14.25!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label1.Location = New System.Drawing.Point(6, 38)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(142, 19)
        Me.Label1.TabIndex = 0
        Me.Label1.Text = "您想查阅的书卷"
        '
        '经文搜索
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(477, 382)
        Me.Controls.Add(Me.GroupBox1)
        Me.Name = "经文搜索"
        Me.Text = "Form1"
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
End Class
