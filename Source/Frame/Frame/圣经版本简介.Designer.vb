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
        Dim TreeNode20 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("什么是圣经")
        Dim TreeNode21 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的来源")
        Dim TreeNode22 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的功能")
        Dim TreeNode23 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的目标")
        Dim TreeNode24 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经的应用")
        Dim TreeNode25 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("圣经概述", New System.Windows.Forms.TreeNode() {TreeNode20, TreeNode21, TreeNode22, TreeNode23, TreeNode24})
        Dim TreeNode26 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("和合本修订版")
        Dim TreeNode27 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("中文", New System.Windows.Forms.TreeNode() {TreeNode26})
        Dim TreeNode28 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ESV")
        Dim TreeNode29 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("KJV")
        Dim TreeNode30 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("ISV")
        Dim TreeNode31 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("BWE")
        Dim TreeNode32 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("WEB")
        Dim TreeNode33 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("JST")
        Dim TreeNode34 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("英语", New System.Windows.Forms.TreeNode() {TreeNode28, TreeNode29, TreeNode30, TreeNode31, TreeNode32, TreeNode33})
        Dim TreeNode35 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("路德圣经")
        Dim TreeNode36 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("德语", New System.Windows.Forms.TreeNode() {TreeNode35})
        Dim TreeNode37 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("希伯来语原版")
        Dim TreeNode38 As System.Windows.Forms.TreeNode = New System.Windows.Forms.TreeNode("古语", New System.Windows.Forms.TreeNode() {TreeNode37})
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(圣经版本简介))
        Me.Tree_version = New System.Windows.Forms.TreeView
        Me.bexit = New System.Windows.Forms.Button
        Me.bhome = New System.Windows.Forms.Button
        Me.RichTextBox1 = New System.Windows.Forms.RichTextBox
        Me.SuspendLayout()
        '
        'Tree_version
        '
        Me.Tree_version.Font = New System.Drawing.Font("华文楷体", 12.0!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(134, Byte))
        Me.Tree_version.ForeColor = System.Drawing.Color.MidnightBlue
        Me.Tree_version.LineColor = System.Drawing.Color.White
        Me.Tree_version.Location = New System.Drawing.Point(12, 79)
        Me.Tree_version.Name = "Tree_version"
        TreeNode20.Name = "B_conception"
        TreeNode20.Tag = "b_concp"
        TreeNode20.Text = "什么是圣经"
        TreeNode21.Name = "B_original"
        TreeNode21.Tag = "b_orig"
        TreeNode21.Text = "圣经的来源"
        TreeNode22.Name = "B_use"
        TreeNode22.Tag = "b_use"
        TreeNode22.Text = "圣经的功能"
        TreeNode23.Name = "B_aim"
        TreeNode23.Tag = "b_aim"
        TreeNode23.Text = "圣经的目标"
        TreeNode24.Name = "B_application"
        TreeNode24.Tag = "b_app"
        TreeNode24.Text = "圣经的应用"
        TreeNode25.Name = "节点0"
        TreeNode25.Text = "圣经概述"
        TreeNode26.Name = "节点2"
        TreeNode26.Tag = "Chi"
        TreeNode26.Text = "和合本修订版"
        TreeNode27.Name = "节点0"
        TreeNode27.Text = "中文"
        TreeNode28.Name = "节点7"
        TreeNode28.Tag = "ESV"
        TreeNode28.Text = "ESV"
        TreeNode29.Name = "节点8"
        TreeNode29.Tag = "KJV"
        TreeNode29.Text = "KJV"
        TreeNode30.Name = "节点9"
        TreeNode30.Tag = "ISV"
        TreeNode30.Text = "ISV"
        TreeNode31.Name = "节点10"
        TreeNode31.Tag = "BWE"
        TreeNode31.Text = "BWE"
        TreeNode32.Name = "节点11"
        TreeNode32.Tag = "WEB"
        TreeNode32.Text = "WEB"
        TreeNode33.Name = "节点12"
        TreeNode33.Tag = "JST"
        TreeNode33.Text = "JST"
        TreeNode34.Name = "节点4"
        TreeNode34.Text = "英语"
        TreeNode35.Name = "节点3"
        TreeNode35.Tag = "German"
        TreeNode35.Text = "路德圣经"
        TreeNode36.Name = "节点1"
        TreeNode36.Text = "德语"
        TreeNode37.Name = "节点15"
        TreeNode37.Tag = "Herb"
        TreeNode37.Text = "希伯来语原版"
        TreeNode38.Name = "节点14"
        TreeNode38.Text = "古语"
        Me.Tree_version.Nodes.AddRange(New System.Windows.Forms.TreeNode() {TreeNode25, TreeNode27, TreeNode34, TreeNode36, TreeNode38})
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
        Me.Controls.Add(Me.Tree_version)
        Me.Name = "圣经版本简介"
        Me.Text = "圣经版本简介"
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents Tree_version As System.Windows.Forms.TreeView
    Friend WithEvents bexit As System.Windows.Forms.Button
    Friend WithEvents bhome As System.Windows.Forms.Button
    Friend WithEvents RichTextBox1 As System.Windows.Forms.RichTextBox
End Class
