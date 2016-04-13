# AnnotationProcessor
Think in Java 中使用APT工具对应的mirror api.但是这是在JAVA 6以前的使用方式，但是在 JAVA 6以后已经不使用了。  
现在使用的是AbstractProcessor。参考文章：https://deors.wordpress.com/2011/10/08/annotation-processors/。
该文章中详细讲了JAVA 6 以后的Annotation Processor的使用变化并且实现了一个Demo。  

因为自己没有使用过或者不了解MAVEN和ANT，所以都是摸索着实现文章中的DEMO。
首先是注解`Complexity.java`：  
```java
package com.dcnh35.myprocessor;
import ...

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Complexity {
    String value();
}
```

然后是注解处理器``：
```java
package com.dcnh35.myprocessor;
import ...
@SupportedAnnotationTypes("com.dcnh35.myprocessor.Complexity")
    @SupportedSourceVersion(SourceVersion.RELEASE_7)
    public class ComplexityProcessor extends AbstractProcessor {

        public ComplexityProcessor() {
            super();
        }

        @Override
        public boolean process(Set<? extends TypeElement> annotations,
                               RoundEnvironment roundEnv) {
            for (Element elem : roundEnv.getElementsAnnotatedWith(Complexity.class)) {
                Complexity complexity = elem.getAnnotation(Complexity.class);
                String message = "annotation found in " + elem.getSimpleName()
                       + " with complexity " + complexity.value();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
            }
            return true;
        }
    }
```

接着使用eclipse将该工程导出JAR包。此时JAR包文件结构：  
*  com.dcnh35.myprocessor  
*  -  Complexity.class  
*  -  ComplexityProcessor.class   
*  META-INF  
*  -  MANIFEST.MF  

此时需要添加文件结构。使得JAR包文件结构变为：  
*  com.dcnh35.myprocessor  
*  -  Complexity.class  
*  -  ComplexityProcessor.class   
*  META-INF  
*  -  services/javax.annotation.processing.Processor  
*  -  MANIFEST.MF  

文件`javax.annotation.processing.Processor`内容为该JAR包中所包含所有的Annotation Processor Name。
比如该JAR包中内容为：`com.dcnh35.myprocessor.ComplexityProcessor`。

 我添加文件结构的方式是最普通的，使用的命令行`jar -uf <your jar file name> ./META-INF/services/javax.annotation.processing.Processor`

 最后进行实验，我是在android studio中进行测试的。  
 把`myprocesser.jar` add to android project，然后Rebuild，最后在Gradle Console中看到信息：`注: annotation found in AppBarActivity with complexity haha`

 >注：
 > 各种情况、iIDE下使用 annotation processor 的方法：https://docs.jboss.org/hibernate/validator/4.1/reference/en-US/html/ch08.html  
 > eclipse中查看 Processor printMessage输出的信息，Window->Show View->Error Log。只是将JAR包add to path还是不行的，上面的链接有操作说明  
 > android studio 中好像可以直接使用，gradle console中查看输出信息。