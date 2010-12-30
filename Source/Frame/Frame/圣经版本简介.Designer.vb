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
        Dim TreeNode1 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("什么是圣经")
        Dim TreeNode2 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的来源")
        Dim TreeNode3 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的功能")
        Dim TreeNode4 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的目标")
        Dim TreeNode5 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的应用")
        Dim TreeNode6 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经概述", New System.Windows.Forms.TreeNode() {TreeNode1, TreeNode2, TreeNode3, TreeNode4, TreeNode5})
        Dim TreeNode7 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("和合本修订版")
        Dim TreeNode8 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("中文", New System.Windows.Forms.TreeNode() {TreeNode7})
        Dim TreeNode9 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ESV")
        Dim TreeNode10 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("KJV")
        Dim TreeNode11 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ISV")
        Dim TreeNode12 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("BWE")
        Dim TreeNode13 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("WEB")
        Dim TreeNode14 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("JST")
        Dim TreeNode15 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("英语", New System.Windows.Forms.TreeNode() {TreeNode9, TreeNode10, TreeNode11, TreeNode12, TreeNode13, TreeNode14})
        Dim TreeNode16 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("路德圣经")
        Dim TreeNode17 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("德语", New System.Windows.Forms.TreeNode() {TreeNode16})
        Dim TreeNode18 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("希伯来语原版")
        Dim TreeNode19 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("古语", New System.Windows.Forms.TreeNode() {TreeNode18})
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(圣经版本简介))
        Me.Tree_version = New System.Windows.Forms.TreeView
        Me.bexit = New System.Windows.Forms.Button
        Me.bhome = New System.Windows.Forms.Button
        Me.RichTextBox1 = New System.Windows.Forms.RichTextBox
        Me.Label5 = New System.Windows.Forms.Label
        Me.SuspendLayout()
        '
        'Tree_version
        '
        Me.Tree_version.Font = New System.Drawing.Font("华文楷体", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Tree_version.ForeColor = System.Drawing.Color.MidnightBlue
        Me.Tree_version.LineColor = System.Drawing.Color.White
        Me.Tree_version.Location = New System.Drawing.Point(12, 79)
        Me.Tree_version.Name = "Tree_version"
        TreeNode1.Name = "B_conception"
        TreeNode1.Tag = "b_concp"
        TreeNode1.Text = "什么是圣经"
        TreeNode2.Name = "B_original"
        TreeNode2.Tag = "b_orig"
        TreeNode2.Text = "圣经的来源"
        TreeNode3.Name = "B_use"
        TreeNode3.Tag = "b_use"
        TreeNode3.Text = "圣经的功能"
        TreeNode4.Name = "B_aim"
        TreeNode4.Tag = "b_aim"
        TreeNode4.Text = "圣经的目标"
        TreeNode5.Name = "B_application"
        TreeNode5.Tag = "b_app"
        TreeNode5.Text = "圣经的应用"
        TreeNode6.Name = "节点0"
        TreeNode6.Text = "圣经概述"
        TreeNode7.Name = "节点2"
        TreeNode7.Tag = "Chi"
        TreeNode7.Text = "和合本修订版"
        TreeNode8.Name = "节点0"
        TreeNode8.Text = "中文"
        TreeNode9.Name = "节点7"
        TreeNode9.Tag = "ESV"
        TreeNode9.Text = "ESV"
        TreeNode10.Name = "节点8"
        TreeNode10.Tag = "KJV"
        TreeNode10.Text = "KJV"
        TreeNode11.Name = "节点9"
        TreeNode11.Tag = "ISV"
        TreeNode11.Text = "ISV"
        TreeNode12.Name = "节点10"
        TreeNode12.Tag = "BWE"
        TreeNode12.Text = "BWE"
        TreeNode13.Name = "节点11"
        TreeNode13.Tag = "WEB"
        TreeNode13.Text = "WEB"
        TreeNode14.Name = "节点12"
        TreeNode14.Tag = "JST"
        TreeNode14.Text = "JST"
        TreeNode15.Name = "节点4"
        TreeNode15.Text = "英语"
        TreeNode16.Name = "节点3"
        TreeNode16.Tag = "German"
        TreeNode16.Text = "路德圣经"
        TreeNode17.Name = "节点1"
        TreeNode17.Text = "德语"
        TreeNode18.Name = "节点15"
        TreeNode18.Tag = "Herb"
        TreeNode18.Text = "希伯来语原版"
        TreeNode19.Name = "节点14"
        TreeNode19.Text = "古语"
        Me.Tree_version.Nodes.AddRange(New System.Windows.Forms.TreeNode() {TreeNode6, TreeNode8, TreeNode15, TreeNode17, TreeNode19})
        Me.Tree_version.Size = New System.Drawing.Size(163, 274)
        Me.Tree_version.TabIndex = 0
        '
        'bexit
        '
        Me.bexit.BackColor = System.Drawing.Color.Transparent
        Me.bexit.BackgroundImage = CType(resources.GetObject("bexit.BackgroundImage"), System.Drawing.Image)
        Me.bexit.Font = New System.Drawing.Font("华文隶书", 15.75!, System.Drawing.FontStyle.Bold)
        Me.bexit.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer), CType(CType(0, Byte), Integer))
        Me.bexit.Image = CType(resources.GetObject("bexit.Image"), System.Drawing.Image)
        Me.bexit.Location = New System.Drawing.Point(679, 428)
        Me.bexit.Name = "bexit"
        Me.bexit.Size = New System.Drawing.Size(95, 32)
        Me.bexit.TabIndex = 25
        Me.bexit.Text = "退出"
        Me.bexit.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bexit.UseVisualStyleBackColor = False
        '
        'bhome
        '
        Me.bhome.AllowDrop = True
        Me.bhome.AutoEllipsis = True
        Me.bhome.BackColor = System.Drawing.Color.Transparent
        Me.bhome.BackgroundImage = CType(resources.GetObject("bhome.BackgroundImage"), System.Drawing.Image)
        Me.bhome.Font = New System.Drawing.Font("华文隶书", 15.75!, System.Drawing.FontStyle.Bold)
        Me.bhome.ForeColor = System.Drawing.Color.Navy
        Me.bhome.Image = CType(resources.GetObject("bhome.Image"), System.Drawing.Image)
        Me.bhome.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft
        Me.bhome.Location = New System.Drawing.Point(679, 390)
        Me.bhome.Name = "bhome"
        Me.bhome.Size = New System.Drawing.Size(95, 32)
        Me.bhome.TabIndex = 24
        Me.bhome.Text = "主页"
        Me.bhome.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText
        Me.bhome.UseVisualStyleBackColor = False
        '
        'RichTextBox1
        '
        Me.RichTextBox1.BackColor = System.Drawing.Color.LavenderBlush
        Me.RichTextBox1.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.RichTextBox1.Font = New System.Drawing.Font("华文楷体", 15.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.RichTextBox1.ForeColor = System.Drawing.Color.Indigo
        Me.RichTextBox1.Location = New System.Drawing.Point(292, 161)
        Me.RichTextBox1.Name = "RichTextBox1"
        Me.RichTextBox1.ReadOnly = True
        Me.RichTextBox1.Size = New System.Drawing.Size(398, 209)
        Me.RichTextBox1.TabIndex = 26
        Me.RichTextBox1.Text = ""
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.BackColor = System.Drawing.Color.Transparent
        Me.Label5.Font = New System.Drawing.Font("Freestyle Script", 14.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label5.ForeColor = System.Drawing.Color.Goldenrod
        Me.Label5.Location = New System.Drawing.Point(8, 437)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(258, 23)
        Me.Label5.TabIndex = 27
        Me.Label5.Text = "Designed by Farseer Yang and Angela Liu"
        '
        '圣经版本简介
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackColor = System.Drawing.Color.DarkRed
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(786, 463)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Controls.Add(Me.bexit)
        Me.Controls.Add(Me.bhome)
        Me.Controls.Add(Me.Tree_version)
        Me.Name = "圣经版本简介"
        Me.Text = "圣经版本简介"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Tree_version As System.Windows.Forms.TreeView
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents bhome As System.Windows.Forms.Button
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
    Friend WithEvents Label5 As System.Windows.Forms.Label
End Class
