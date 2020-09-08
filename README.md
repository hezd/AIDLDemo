#### 1.AIDL是什么

> AIDL（Android interface defination language）是一种dl语言，用于生成进程间通信的IPC代码。基于c/s架构，使用代理类进行客户端和服务端交互。

#### 2.AIDL应用场景

> 在官方文档中提到只有在需要不同应用客户端通过IPC方式访问服务,并且需要在服务中进行多线程操作时才有必要使用AIDL.如果无需在跨应用执行并发IPC应该通过扩展Binder类(创建普通服务)的方式实现客户端和服务的交互.或者你想执行IPC但不需要处理多线程可以使用Messenger来实现.

#### 3.AIDL实现

**3.1创建AIDLDemo项目以及服务端aidlserver和客户端aidlclient**
![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL01.jpg?raw=true)

**3.2服务端实现**

> - 创建AIDL文件
>   在src/main目录下用As可视化工具new一个aidl文件会自动生成一个aidl文件夹用来存放aidl文件，我们这里以图书管理为例，创建IBookManager.aidl、Book.aidl和Book.java
![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL02.jpg?raw=true)

> Book是我们用到的图书类，在AIDL中如果要用到类需要创建同名的Java类和aidl文件
> 	IBookManager中定义了远程服务提供的方法
> 	AIDL支持的数据类型包括以下几种：
> 	Java的8种基本数据类型：int，short，long，char，double，byte，float，boolean；
> 	CharSequence类型，如String、SpannableString等；
> 	ArrayList，并且T必须是AIDL所支持的数据类型；
> 	HashMap<K，V>，并且K和V必须是AIDL所支持的数据类型；
> 	所有Parceable接口的实现类，因为跨进程传输对象时，本质上是序列化与反序列化的过程；
> 	AIDL接口，所有的AIDL接口本身也可以作为可支持的数据类型；

> - 创建远程服务
>   创建远程服务，服务端Binder对象并在onBind方法中返回
>   ![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL03.jpg?raw=true)

> - 清单文件中注册远程服务
>   ![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL04.jpg?raw=true)
注意：AS默认配置编译会报错需要配置aidl路径
![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL08.jpg?raw=true)
**3.3客户端实现**

> - 创建AIDL文件
>   将服务端的AIDL文件拷贝到客户端相同包名下
>   ![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL05.jpg?raw=true)

> - 绑定服务
>   ![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL06.jpg?raw=true)

> 通过代码我们可以看到在客户端调用服务端addBook方法向图书列表中添加一本图书并查询添加后的结果，首先启动服务端，启动客户端可以看到控制台日志，添加成功。

![](https://github.com/dreamkid/Image-Folders/blob/master/AIDLDemo/AIDL07.jpg?raw=true)

**以上就是AIDL的用法，代码github地址，欢迎star。下一篇介绍Binder进阶**

参考：
	《Android艺术探索》
	 [《AIDL使用详解》](https://www.cnblogs.com/sqchen/p/10660939.html "AIDL使用详解")
	[《Android官方文档》](https://developer.android.google.cn/guide/components/aidl?hl=zh_cn "《Android官方文档》")
