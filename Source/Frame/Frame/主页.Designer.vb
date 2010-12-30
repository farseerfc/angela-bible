<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 主页
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(主页))
        Me.b_browse = New System.Windows.Forms.Button
        Me.b_search = New System.Windows.Forms.Button
        Me.b_carol = New System.Windows.Forms.Button
        Me.b_help = New System.Windows.Forms.Button
        Me.b_version = New System.Windows.Forms.Button
        Me.b_suggestion = New System.Windows.Forms.Button
        Me.Label1 = New System.Windows.Forms.Label
        Me.Timer1 = New System.Windows.Forms.Timer(Me.components)
        Me.bexit = New System.Windows.Forms.Button
        Me.Label5 = New System.Windows.Forms.Label
        Me.SuspendLayout()
        '
        'b_browse
        '
        Me.b_browse.BackColor = System.Drawing.Color.DarkRed
        Me.b_browse.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center
        Me.b_browse.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_browse.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.b_browse.ForeColor = System.Drawing.Color.Gold
        Me.b_browse.Image = CType(resources.GetObject("b_browse.Image"), System.Drawing.Image)
        Me.b_browse.Location = New System.Drawing.Point(12, 58)
        Me.b_browse.Name = "b_browse"
        Me.b_browse.Size = New System.Drawing.Size(84, 92)
        Me.b_browse.TabIndex = 0
        Me.b_browse.Text = "圣经概览"
        Me.b_browse.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_browse.UseVisualStyleBackColor = False
        '
        'b_search
        '
        Me.b_search.BackColor = System.Drawing.Color.DarkRed
        Me.b_search.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_search.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold)
        Me.b_search.ForeColor = System.Drawing.Color.Yellow
        Me.b_search.Image = CType(resources.GetObject("b_search.Image"), System.Drawing.Image)
        Me.b_search.Location = New System.Drawing.Point(97, 95)
        Me.b_search.Name = "b_search"
        Me.b_search.Size = New System.Drawing.Size(84, 92)
        Me.b_search.TabIndex = 1
        Me.b_search.Text = "经文查询"
        Me.b_search.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_search.UseVisualStyleBackColor = False
        '
        'b_carol
        '
        Me.b_carol.BackColor = System.Drawing.Color.DarkRed
        Me.b_carol.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_carol.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold)
        Me.b_carol.ForeColor = System.Drawing.Color.Gold
        Me.b_carol.Image = CType(resources.GetObject("b_carol.Image"), System.Drawing.Image)
        Me.b_carol.Location = New System.Drawing.Point(183, 58)
        Me.b_carol.Name = "b_carol"
        Me.b_carol.Size = New System.Drawing.Size(84, 92)
        Me.b_carol.TabIndex = 2
        Me.b_carol.Text = "圣乐崇拜"
        Me.b_carol.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_carol.UseVisualStyleBackColor = False
        '
        'b_help
        '
        Me.b_help.BackColor = System.Drawing.Color.DarkRed
        Me.b_help.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center
        Me.b_help.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_help.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.b_help.ForeColor = System.Drawing.Color.Gold
        Me.b_help.Image = CType(resources.GetObject("b_help.Image"), System.Drawing.Image)
        Me.b_help.Location = New System.Drawing.Point(183, 321)
        Me.b_help.Name = "b_help"
        Me.b_help.Size = New System.Drawing.Size(84, 92)
        Me.b_help.TabIndex = 3
        Me.b_help.Text = "使用帮助"
        Me.b_help.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_help.UseVisualStyleBackColor = False
        '
        'b_version
        '
        Me.b_version.BackColor = System.Drawing.Color.DarkRed
        Me.b_version.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center
        Me.b_version.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_version.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.b_version.ForeColor = System.Drawing.Color.Gold
        Me.b_version.Image = CType(resources.GetObject("b_version.Image"), System.Drawing.Image)
        Me.b_version.Location = New System.Drawing.Point(97, 290)
        Me.b_version.Name = "b_version"
        Me.b_version.Size = New System.Drawing.Size(84, 92)
        Me.b_version.TabIndex = 4
        Me.b_version.Text = "圣经版本"
        Me.b_version.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_version.UseVisualStyleBackColor = False
        '
        'b_suggestion
        '
        Me.b_suggestion.BackColor = System.Drawing.Color.DarkRed
        Me.b_suggestion.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center
        Me.b_suggestion.Cursor = System.Windows.Forms.Cursors.Hand
        Me.b_suggestion.Font = New System.Drawing.Font("幼圆", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.b_suggestion.ForeColor = System.Drawing.Color.Gold
        Me.b_suggestion.Image = CType(resources.GetObject("b_suggestion.Image"), System.Drawing.Image)
        Me.b_suggestion.Location = New System.Drawing.Point(12, 321)
        Me.b_suggestion.Name = "b_suggestion"
        Me.b_suggestion.Size = New System.Drawing.Size(84, 92)
        Me.b_suggestion.TabIndex = 5
        Me.b_suggestion.Text = "意见反馈"
        Me.b_suggestion.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText
        Me.b_suggestion.UseVisualStyleBackColor = False
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.BackColor = System.Drawing.Color.Transparent
        Me.Label1.Font = New System.Drawing.Font("华文行楷", 15.75!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Label1.ForeColor = System.Drawing.Color.GreenYellow
        Me.Label1.Location = New System.Drawing.Point(22, 218)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(848, 22)
        Me.Label1.TabIndex = 7
        Me.Label1.Text = " 你或向左、或向右、你必听见后边有声音说、这是正路、要行在其间。——以赛亚书30：21"
        '
        'Timer1
        '
        '
        'bexit
        '
        Me.bexit.BackColor = System.Drawing.SystemColors.ActiveCaptionText
        Me.bexit.Font = New System.Drawing.Font("幼圆", 10.5!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.bexit.ForeColor = System.Drawing.Color.LightSkyBlue
        Me.bexit.Image = CType(resources.GetObject("bexit.Image"), System.Drawing.Image)
        Me.bexit.Location = New System.Drawing.Point(413, 1)
        Me.bexit.Name = "bexit"
        Me.bexit.Size = New System.Drawing.Size(109, 40)
        Me.bexit.TabIndex = 14
        Me.bexit.Text = "退出系统"
        Me.bexit.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bexit.UseVisualStyleBackColor = False
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.BackColor = System.Drawing.Color.Transparent
        Me.Label5.Font = New System.Drawing.Font("Freestyle Script", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label5.ForeColor = System.Drawing.Color.Yellow
        Me.Label5.Location = New System.Drawing.Point(104, 1)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(258, 23)
        Me.Label5.TabIndex = 15
        Me.Label5.Text = "Designed by Farseer Yang and Angela Liu"
        '
        '主页
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(524, 477)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.bexit)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.b_suggestion)
        Me.Controls.Add(Me.b_version)
        Me.Controls.Add(Me.b_help)
        Me.Controls.Add(Me.b_carol)
        Me.Controls.Add(Me.b_search)
        Me.Controls.Add(Me.b_browse)
        Me.Name = "主页"
        Me.Text = "主页"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents b_browse As System.Windows.Forms.Button
    Friend WithEvents b_search As System.Windows.Forms.Button
    Friend WithEvents b_carol As System.Windows.Forms.Button
    Friend WithEvents b_help As System.Windows.Forms.Button
    Friend WithEvents b_version As System.Windows.Forms.Button
    Friend WithEvents b_suggestion As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Timer1 As System.Windows.Forms.Timer
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents Label5 As System.Windows.Forms.Label
End Class
