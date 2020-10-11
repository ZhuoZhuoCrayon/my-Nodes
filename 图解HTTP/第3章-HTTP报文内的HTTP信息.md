# 第3章-HTTP报文内的HTTP信息

> Author: Crayon
>
> Date: 2020/10/08

## HTTP报文

> 用于HTTP协议交互的信息被称为HTTP报文

### 报文结构

* **报文首部**
  * 状态行（响应报文） or 请求行（请求报文）
  * 各种首部字段
* **空行：CR + LF**
  * CR（Carriage Return，回车符：16进制0x0d）
  * LF（Line Feed，换行符：16进制0x0a）
* **报文主体**
  * 应被发送的数据

### 请求报文

* **请求行**
  * 包含请求方法，请求URI，HTTP版本
  * eg：GET /user/1/ HTTP/1/.1
* **首部字段**
  * **请求首部字段**
  * 通用首部字段
  * 实体首部字段
  * 其他首部字段，可能包含RFC中未定义的首部（Cookie等）
* **空行：CR + LF**
* **报文主体**



### 响应报文

* **状态行**
  * 包含HTTP版本，状态码，原因短语
  * eg：HTTP/1.1 200 OK
* **首部字段**
  * **响应首部字段**
  * 通用首部字段
  * 实体首部字段
  * 其他首部字段，可能包含RFC中未定义的首部（Cookie等）
* **空行：CR + LF**
* **报文主体**



## 编码提升传输速率

> HTTP可以通过编码提升传输速率，通过在传输时进行编码，能有效处理大量访问请求。
>
> 编码需要消耗CPU资源

### 报文主体和实体主体的差异

* **报文（message）**
  * HTTP通信的基本单位，通过HTTP通信传输
  * 8位组字节流（octset sequence，其中octet为8个比特）
* **实体（entity）**
  * 作为请求或响应的**有效荷载数据（补充项）**被传输
  * 由实体首部和实体主体组成

**HTTP报文的主体用于传输请求或响应的实体主体**

通常报文主体等于实体主体，只有当传输中进行编码操作时，**实体主体内容发生变化**，才会导致差异

### 压缩传输中的内容编码

**内容编码**：HTTP协议的一种功能

* 指明应用在实体内容上的编码格式，保持实体信息原样压缩
* 内容编码后的实体由**客户端接收并负责解码**

常见的内容编码

* gzip（GNU zip）
* compress（UNIX 系统的标准压缩）
* deflate（zlib）
* identity（不进行编码）

### 分割发送的分块传输编码

在HTTP通信过程中，请求的编码实体资源未全部传输完成之前，浏览器无法显示请求页面

传输大容量数据时，通过把数据分割，能让浏览器**逐步显示页面**

这种把实体主体分块的功能称为**分块传输编码（Chunk Transfer Coding）**

**特性**

* 实体主体分块，用十六进制标记块大小
* 实体主体最后的分块用**0（CR + LF）**标记
* 接收的客户端负责解码

HTTP/1/1中存在**传输编码（Transfer Coding）**的机制，可以在通信时按某种编码方式传输，**只定义于分块传输编码中**



## 多部分对象集合

> 用于发送多种数据

**MIME (Multipurpose Internet Mail Extensions，多用途因特网邮件扩展) **

* 允许邮件处理文本，图片，视频等多个不同类型的数据，例如图片等二进制数据以ASCII码字符串编码的方式指明，就是利用MIME来描述标记数据类型
* MIME会使用到**多部分对象集合（Multipart）的方法**，来**容纳多份不同类型的数据**

**HTTP协议也采用了多部分对象集合的方法，发送一份报文主体中可能含有多类型主体**，通常在图片或文本文件上传中使用



#### 多部分对象集合包含的对象

* multipart/form-data

  * 用于表单上传

  ```
  Content-Type: multipart/form-data; boundary=AaB03x
  
  --AaB03x
  Content-Dispostion: form-data; name="field1"
  
  Joe Blow
  --AaB03x
  Content-Dispostion: form-data; name="pics"; filename="file1.txt"
  Content-Type: text/plain
  
  ... file1.txt的数据..
  结束段
  --AaB03x--
  ```

* multipart/byteranges

  * 状态码206（Partial Content，部分内容）
  * 响应报文包含多个范围的内容时使用

  ```
  Content-Type: multipart/byteranges; boundary=THIS_STRING_SEPARTES
  
  --THIS_STRING_SEPARTES
  Content-Type: application/pdf
  Content-Range: bytes 500-999/8000
  
  --THIS_STRING_SEPARTES
  Content-Type: application/pdf
  Content-Range: bytes 7000-7999/8000
  
  结束段
  --THIS_STRING_SEPARTES--
  ```

  

无论是`form-data`还是`byteranges`，都会用到`boundary`划分多部分对象集合指明的各类实体

* **在`boundary`指定的各个实体的起始行之前插入“--”标记**，例如`--THIS_STRING_SEPARTES` `--AaB03x`
* **在多部分对象集合对应的字符串最后插入“--”标记作为结束**，例如`--THIS_STRING_SEPARTES--` `--AaB03x--`

**多部分对象集合的每个部分类型中，都可以包含首部字段（比如指明类型的Conten-Type），也支持嵌套**



## 获取部分内容的范围请求

> **指定范围发送的请求叫做范围请求（Range Request）**，用于**断点续传**

执行范围请求会用到首部字段`Range`指定byte范围

```
Range: bytes=5001-10000
```

```
Range: bytes=5001-
```

```
Range: bytes=0-3000, 5000-7000
```

对于范围请求，响应会返回状态码**206（Partial Content）**的响应报文

对于**多重范围的范围请求**，响应报文会在首部字段`Content-Type`标明`multipart/byteranges`后返回响应报文



## 内容协商

> 同一个Web网站的同一页面，可能存在多种语言，根据客户端返回不同语言的页面，这种机制称为**内容协商（Content Negotiation）**

包含在请求报文中的某些首部字段作为内容协商机制的判断基准

* Accept
* Accept-Charset
* Accept-Encoding
* Accept-Language
* Content-Language

