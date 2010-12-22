<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class 圣经版本简介
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
        Dim TreeNode14 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("和合本修订版")
        Dim TreeNode15 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("中文", New System.Windows.Forms.TreeNode() {TreeNode14})
        Dim TreeNode16 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ESV")
        Dim TreeNode17 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("KJV")
        Dim TreeNode18 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ISV")
        Dim TreeNode19 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("BWE")
        Dim TreeNode20 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("WEB")
        Dim TreeNode21 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("JST")
        Dim TreeNode22 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("英语", New System.Windows.Forms.TreeNode() {TreeNode16, TreeNode17, TreeNode18, TreeNode19, TreeNode20, TreeNode21})
        Dim TreeNode23 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("路德圣经")
        Dim TreeNode24 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("德语路德圣经", New System.Windows.Forms.TreeNode() {TreeNode23})
        Dim TreeNode25 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("希伯来语原版")
        Dim TreeNode26 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("古语", New System.Windows.Forms.TreeNode() {TreeNode25})
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(圣经版本简介))
        Me.TreeView1 = New System.Windows.Forms.TreeView
        Me.Button4 = New System.Windows.Forms.Button
        Me.Button2 = New System.Windows.Forms.Button
        Me.RichTextBox1 = New System.Windows.Forms.RichTextBox
        Me.SuspendLayout()
        '
        'TreeView1
        '
        Me.TreeView1.Font = New System.Drawing.Font("华文楷体", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.TreeView1.ForeColor = System.Drawing.Color.MidnightBlue
        Me.TreeView1.LineColor = System.Drawing.Color.White
        Me.TreeView1.Location = New System.Drawing.Point(12, 79)
        Me.TreeView1.Name = "TreeView1"
        TreeNode14.Name = "节点2"
        TreeNode14.Tag = "Chi"
        TreeNode14.Text = "和合本修订版"
        TreeNode15.Name = "节点0"
        TreeNode15.Text = "中文"
        TreeNode16.Name = "节点7"
        TreeNode16.Tag = "ESV"
        TreeNode16.Text = "ESV"
        TreeNode17.Name = "节点8"
        TreeNode17.Tag = "KJV"
        TreeNode17.Text = "KJV"
        TreeNode18.Name = "节点9"
        TreeNode18.Tag = "ISV"
        TreeNode18.Text = "ISV"
        TreeNode19.Name = "节点10"
        TreeNode19.Tag = "BWE"
        TreeNode19.Text = "BWE"
        TreeNode20.Name = "节点11"
        TreeNode20.Tag = "WEB"
        TreeNode20.Text = "WEB"
        TreeNode21.Name = "节点12"
        TreeNode21.Tag = "JST"
        TreeNode21.Text = "JST"
        TreeNode22.Name = "节点4"
        TreeNode22.Text = "英语"
        TreeNode23.Name = "节点3"
        TreeNode23.Tag = "German"
        TreeNode23.Text = "路德圣经"
        TreeNode24.Name = "节点1"
        TreeNode24.Text = "德语路德圣经"
        TreeNode25.Name = "节点15"
        TreeNode25.Tag = "Herb"
        TreeNode25.Text = "希伯来语原版"
        TreeNode26.Name = "节点14"
        TreeNode26.Text = "古语"
        Me.TreeView1.Nodes.AddRange(New System.Windows.Forms.TreeNode() {TreeNode15, TreeNode22, TreeNode24, TreeNode26})
        Me.TreeView1.Size = New System.Drawing.Size(163, 274)
        Me.TreeView1.TabIndex = 0
        '
        'Button4
        '
        Me.Button4.BackColor = System.Drawing.Color.Transparent
        Me.Button4.BackgroundImage = CType(resources.GetObject("Button4.BackgroundImage"), System.Drawing.Image)
        Me.Button4.Font = New System.Drawing.Font("华文隶书", 15.75!, System.Drawing.FontStyle.Bold)
        Me.Button4.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer), CType(CType(0, Byte), Integer))
        Me.Button4.Image = CType(resources.GetObject("Button4.Image"), System.Drawing.Image)
        Me.Button4.Location = New System.Drawing.Point(97, 405)
        Me.Button4.Name = "Button4"
        Me.Button4.Size = New System.Drawing.Size(95, 57)
        Me.Button4.TabIndex = 25
        Me.Button4.Text = "退出"
        Me.Button4.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button4.UseVisualStyleBackColor = False
        '
        'Button2
        '
        Me.Button2.AllowDrop = True
        Me.Button2.AutoEllipsis = True
        Me.Button2.BackColor = System.Drawing.Color.Transparent
        Me.Button2.BackgroundImage = CType(resources.GetObject("Button2.BackgroundImage"), System.Drawing.Image)
        Me.Button2.Font = New System.Drawing.Font("华文隶书", 15.75!, System.Drawing.FontStyle.Bold)
        Me.Button2.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
        Me.Button2.Image = CType(resources.GetObject("Button2.Image"), System.Drawing.Image)
        Me.Button2.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.Button2.Location = New System.Drawing.Point(3, 405)
        Me.Button2.Name = "Button2"
        Me.Button2.Size = New System.Drawing.Size(88, 57)
        Me.Button2.TabIndex = 24
        Me.Button2.Text = "主页"
        Me.Button2.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.Button2.UseVisualStyleBackColor = False
        '
        'RichTextBox1
        '
        Me.RichTextBox1.BackColor = System.Drawing.Color.LavenderBlush
        Me.RichTextBox1.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.RichTextBox1.Font = New System.Drawing.Font("华文楷体", 15.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.RichTextBox1.ForeColor = System.Drawing.Color.Indigo
        Me.RichTextBox1.Location = New System.Drawing.Point(292, 161)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.Size = New System.Drawing.Size(398, 209)
        Me.RichTextBox1.TabIndex = 26
        Me.RichTextBox1.Text = ""
        '
        '圣经版本简介
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackColor = System.Drawing.Color.DarkRed
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(786, 463)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Controls.Add(Me.Button4)
        Me.Controls.Add(Me.Button2)
        Me.Controls.Add(Me.TreeView1)
        Me.Name = "圣经版本简介"
        Me.Text = "Form2"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TreeView1 As System.Windows.Forms.TreeView
    Friend WithEvents Button4 As System.Windows.Forms.Button
    Friend WithEvents Button2 As System.Windows.Forms.Button
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
End Class
