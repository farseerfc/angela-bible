Module Module1
    Public fstart As New 登录界面
    Public fregister As New 用户注册界面
    Public fbrowse As New 圣经章节概览
    Public fsearch As New 经文精确搜索
    Public fcompare As New 经文对比显示
    Public freader As New 经文查询显示
    Public fcarols As New 赞美诗
    Public fhome As New 主页
    Public fwelcome As New 欢迎界面

    Public strConnect As String = "server=localhost;user id=angela;password=angela;initial catalog=angelabible;"

    Public Sub Main()
        Application.EnableVisualStyles()
        'GetDataSet()
        Application.Run(fregister)
    End Sub

    '测试用
    '
    Public Function GetDataSet() As SqlClient.SqlDataReader

        Dim strConnect As String = "server=localhost;user id=angela;password=angela;initial catalog=angelabible;"
        Dim sqlCommand As SqlClient.SqlCommand

        Using sqlConnection As SqlClient.SqlConnection = New SqlClient.SqlConnection(strConnect)
            sqlConnection.Open()
            sqlConnection.CreateCommand()
            sqlCommand = sqlConnection.CreateCommand
            sqlCommand.CommandText = "use angelabible;select * from [dbo].[Version];"

            Dim sqlReader As SqlClient.SqlDataReader = sqlCommand.ExecuteReader()
            While sqlReader.Read()

                Dim initial As String = sqlReader.GetString(0)
                Dim title As String = sqlReader.GetString(1)

                MsgBox(title, MsgBoxStyle.OkOnly, initial)
            End While




            Return sqlReader
        End Using

    End Function
End Module
