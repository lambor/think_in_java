# Annotation Processor Jar打包
### 使用eclipse 打包JAR
没有使用maven等工具，只是使用eclipse自带的export。导出后，还需要使用`jar  -uf   your_jar_file_name   your_meta-inf_directory`

### 使用 Android Studio 打包JAR
Android Studio使用的是gradle工具，在编译时自动打包。  
new 一个 JAVA Library，然后构建工程结构：  
* src
*  - main
*  - + java
*  - + resources/ META-INF/services/javax.annotation.processing.Processor
编译后会发现module下出现一个build文件夹，然后JAR包即在libs中

### 使用Intellij maven打包JAR
Intellij 自带MAVEN GRADLE工具
maven 打包很方便。只需要使用命令`mvn package`就生成了jar包
Intellij中有一个“BUG”，mvn工程的结构和上面的Android Studio 的工程结构相同，但是在rebuild时会一直出现  
`Error:java: 服务配置文件不正确, 或构造处理程序对象javax.annotation.processing.Processor: Provider xxx not found时抛出异常错误`  
出现此问题的原因是配置文件中的xxx（your annotation processor providor）对应的class没有被找到。所以需要先编译成class。  
解决方法：先将配置文件中的内容删去，然后make你的工程，然后再将配置文件内容复原，再make。  
最后使用`mvn package`就行了。