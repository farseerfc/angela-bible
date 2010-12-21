Module Module1
    Public fstart As New 登录界面
    Public fregister As New 用户注册界面
    Public fbrowse As New 圣经章节概览
    Public fsearch As New 经文精确搜索
    Public fcompare As New 经文对比显示
    Public fcarols As New 赞美诗
    Public fhome As New 主页
    Public fwelcome As New 欢迎界面



    Public Sub Main()
        Application.EnableVisualStyles()
        Application.Run(fregister)
    End Sub


    Public Function GetDataSet() As DataSet

    End Function
End Module
