[FileComparator](https://github.com/s0uths1de/FileComparator/blob/main/README.md)

## 快速比对作业查询的GUI界面工具

推荐环境：Liberica JDK Full 64bit `[链接](https://www.injdk.cn/)`

其他JDK17以上带有JavaFx均可

## 使用说明：

#### 1、读信息文件

> 读取文件建议为csv，按行读取，每一行可以包含正则表达式匹配的内容也可以不包含
> 
> csv文件，即每一行包括数字和对应的名字，中间用逗号隔开，也可无逗号隔开
> 
> 为什么使用vsc文件？
> 
>> 文件的格式简单，每个数据用,和换行分离，可用excel编辑

#### 2、读取文件

> 读取一个文件夹，其中为所有人的作业

#### 3、点击开始

> 出现未完成作业的人，命名错误的人（此功能暂未实现）

#### 4、替换

> []{KEY}[]{VALUE}[]
>
> 其中{KEY}{VALUE}为保留字符，[可替换的内容]，[]可选可不选
> 
> <p style="color: darkcyan">例子：JAVA{KEY}{VALUE}.docx<p/>
>

#### 5、修改正则表达式的匹配规则（可选）

> 在程序的第一次运行，会在程序的当前文字生成.fileComparator文件夹，当中包含config.ini
> 
> 配置项：
> 
> regularExpressionKey：匹配key的正则表达式
> 
> regularExpressionValue：匹配value的正则表达式(可选)

## 截图

![jietu.png](src/main/resources/top/s0uths1de/filecomparator/assets//jietu.png)

## 运行方式：

>Java -jar [你下载jar包的路径]

## 欢迎提出你的建议