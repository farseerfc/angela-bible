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
        Dim TreeNode1 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("和合本修订版")
        Dim TreeNode2 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("中文", New System.Windows.Forms.TreeNode() {TreeNode1})
        Dim TreeNode3 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ESV")
        Dim TreeNode4 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("KJV")
        Dim TreeNode5 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ISV")
        Dim TreeNode6 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("BWE")
        Dim TreeNode7 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("WEB")
        Dim TreeNode8 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("JST")
        Dim TreeNode9 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("英语", New System.Windows.Forms.TreeNode() {TreeNode3, TreeNode4, TreeNode5, TreeNode6, TreeNode7, TreeNode8})
        Dim TreeNode10 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("路德圣经")
        Dim TreeNode11 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("德语", New System.Windows.Forms.TreeNode() {TreeNode10})
        Dim TreeNode12 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("希伯来语原版")
        Dim TreeNode13 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("古语", New System.Windows.Forms.TreeNode() {TreeNode12})
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(圣经版本简介))
        Me.TreeView1 = New System.Windows.Forms.TreeView
        Me.bexit = New System.Windows.Forms.Button
        Me.bhome = New System.Windows.Forms.Button
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
        TreeNode1.Name = "节点2"
        TreeNode1.Tag = "Chi"
        TreeNode1.Text = "和合本修订版"
        TreeNode2.Name = "节点0"
        TreeNode2.Text = "中文"
        TreeNode3.Name = "节点7"
        TreeNode3.Tag = "ESV"
        TreeNode3.Text = "ESV"
        TreeNode4.Name = "节点8"
        TreeNode4.Tag = "KJV"
        TreeNode4.Text = "KJV"
        TreeNode5.Name = "节点9"
        TreeNode5.Tag = "ISV"
        TreeNode5.Text = "ISV"
        TreeNode6.Name = "节点10"
        TreeNode6.Tag = "BWE"
        TreeNode6.Text = "BWE"
        TreeNode7.Name = "节点11"
        TreeNode7.Tag = "WEB"
        TreeNode7.Text = "WEB"
        TreeNode8.Name = "节点12"
        TreeNode8.Tag = "JST"
        TreeNode8.Text = "JST"
        TreeNode9.Name = "节点4"
        TreeNode9.Text = "英语"
        TreeNode10.Name = "节点3"
        TreeNode10.Tag = "German"
        TreeNode10.Text = "路德圣经"
        TreeNode11.Name = "节点1"
        TreeNode11.Text = "德语"
        TreeNode12.Name = "节点15"
        TreeNode12.Tag = "Herb"
        TreeNode12.Text = "希伯来语原版"
        TreeNode13.Name = "节点14"
        TreeNode13.Text = "古语"
        Me.TreeView1.Nodes.AddRange(New System.Windows.Forms.TreeNode() {TreeNode2, TreeNode9, TreeNode11, TreeNode13})
        Me.TreeView1.Size = New System.Drawing.Size(163, 274)
        Me.TreeView1.TabIndex = 0
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
        Me.bhome.ForeColor = System.Drawing.Color.FromArgb(CType(CType(0, Byte), Integer), CType(CType(0, Byte), Integer), CType(CType(64, Byte), Integer))
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
        '圣经版本简介
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackColor = System.Drawing.Color.DarkRed
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(786, 463)
        Me.Controls.Add(Me.RichTextBox1)
        Me.Controls.Add(Me.bexit)
        Me.Controls.Add(Me.bhome)
        Me.Controls.Add(Me.TreeView1)
        Me.Name = "圣经版本简介"
        Me.Text = "圣经版本简介"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents TreeView1 As System.Windows.Forms.TreeView
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents bhome As System.Windows.Forms.Button
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
End Class
