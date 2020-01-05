# GitHub搜索技巧——搜索仓库

### 搜索fork仓库

| 修饰语      | 例子                                                         |
| :---------- | :----------------------------------------------------------- |
| `fork:true` | **github fork:true** 匹配所有的包含关键字“github”的仓库，包括 fork仓库. |
|             | **android language:java fork:true** 匹配包含关键字“android”并且使用java写的fork仓库或者是常规的仓库. |
| `fork:only` | **github fork:only** 匹配所有的包含关键字“github”的fork仓库. |
|             | **forks:>500 fork:only**  匹配fork超过500次的fork仓库.       |

### 指定搜索域

`in` 修饰符限制了搜索的范围. 你能指定搜索仓库名、仓库简介、README文件或者是这几种的组合.默认是仓库名以及仓库简介.

| 修饰语           | 例子                           |
| :--------------- | :----------------------------- |
| `in:name`        | **jquery in:name**             |
| `in:description` | **jquery in:name,description** |
| `in:readme`      | **jquery in:readme**           |

### 通过一个仓库所使用的主要的语言来进行搜索

| Qualifier           | Example                                                      |
| :------------------ | :----------------------------------------------------------- |
| `language:LANGUAGE` | **rails language:javascript** 匹配带有关键字“rails”，并且主要是由javascript写的仓库. |

### 基于仓库所拥有的星的数量的搜索

| Qualifier | Example                                                      |
| :-------- | :----------------------------------------------------------- |
| `stars:n` | **stars:500**                                                |
|           | **stars:10..20**                                             |
|           | **stars:>=500 fork:true language:php** 匹配超过500颗星并且主要是由php写的fork仓库. |

### 基于issues标签数量的搜索

| 修饰语                  | 例子                                                         |
| :---------------------- | :----------------------------------------------------------- |
| `good-first-issues:>n`  | **good-first-issues:>2 javascript** 匹配包含关键字“javascript”并且带有`good-first-issues`标签的issues的数量大于2. |
| `help-wanted-issues:>n` | **help-wanted-issues:>4 react**                              |

### 基于仓库大小的搜索

`size`修饰符寻找匹配相应大小的仓库(以kb为单位).

| 修饰语     | 例子                              |
| :--------- | :-------------------------------- |
| `size:*n*` | **size:1000**  匹配刚好1mb的仓库. |
|            | **size:>=30000**                  |
|            | **size:<50**                      |
|            | **size:50..120**                  |

### 基于仓库是公有还是私有的搜索

| 修饰语       | 例子                                                         |
| :----------- | :----------------------------------------------------------- |
| `is:private` | **is:private pages**  匹配自己有相应权限并且带有关键字“pages”的私有仓库 |
| `is:public`  | **is:public org:github** 匹配属于github的公开仓库            |

### 基于仓库是否是镜像的搜索

| 修饰语         | 例子                                                  |
| :------------- | :---------------------------------------------------- |
| `mirror:true`  | **mirror:true GNOME** 匹配带有关键字“GNOME”的镜像仓库 |
| `mirror:false` | **mirror:false GNOME**                                |

### 基于仓库是否废弃的搜索

| 修饰语           | 例子                                                         |
| :--------------- | :----------------------------------------------------------- |
| `archived:true`  | **archived:true GNOME** 匹配带有关键字“GNOME”并且不再维护的仓库 |
| `archived:false` | **archived:false GNOME**                                     |

### 基于fork数量的搜索

| 修饰语      | 例子                                   |
| :---------- | :------------------------------------- |
| `forks:*n*` | **forks:5**  匹配fork数量刚好为5的仓库 |
|             | **forks:>=205**                        |
|             | **forks:<90**                          |
|             | **forks:10..20**                       |

### 基于仓库创造或者最后一次更新的时间的搜索

你可以通过创造的时间或者最后一次更新的时间来过滤你的仓库。如果你想寻找一个仓库是多久更新的，你可能会使用`pushed`修饰符，`pushed`修饰符将会返回通过最近一次push（不管是push到哪一个分支）来排序的仓库列表

使用一个时间作为参数。时间格式遵循[ISO8601](http://en.wikipedia.org/wiki/ISO_8601)标准，`YY-MM-DD`（年-月-日），你同样也可以增加额外的时间信息`THH:MM:SS+00:00`（时-分-秒和UTC偏移量）

| 修饰语                 | 例子                                                         |
| :--------------------- | :----------------------------------------------------------- |
| `created:*YYYY-MM-DD*` | **webos created:<2011-01-01**                                |
| `pushed:*YYYY-MM-DD*`  | **css pushed:>2013-02-01**                                   |
|                        | **case pushed:>=2013-03-06 fork:only** 匹配带有关键字“case”并且在2013年3月6日以后push过的fork仓库. |

### 搜索一个用户或者一个组织的仓库

| 修饰语            | 例子                                                         |
| :---------------- | :----------------------------------------------------------- |
| `user:*USERNAME*` | **user:defunkt forks:>100** 匹配来自defunkt用户并且fork数量超过100的仓库 |
| `org:*ORGNAME*`   | **org:github**  匹配来自github组织的仓库                     |

### 通过话题搜索

你可以寻找被一个话题所归为一类的所有仓库

| Qualifier     | Example                                       |
| :------------ | :-------------------------------------------- |
| `topic:TOPIC` | **topic:jekyll** 匹配带有jekyll话题标签的仓库 |

### 基于仓库所拥有的话题数量的搜索

| Qualifier  | Example                            |
| :--------- | :--------------------------------- |
| `topics:n` | **topics:5** 匹配带有5个话题的仓库 |
|            | **topics:>3**                      |

### 通过证书搜索

你可以通过仓库的证书来进行搜索，但是你必须使用 [license keyword](https://help.github.com/articles/licensing-a-repository/#searching-github-by-license-type)来进行过滤

| Qualifier                 | Example                                                      |
| :------------------------ | :----------------------------------------------------------- |
| `license:LICENSE_KEYWORD` | **license:apache-2.0** 匹配使用Apache License 2.0的证书的仓库. |