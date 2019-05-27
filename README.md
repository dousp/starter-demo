# 自定义spring boot starter

- 具体细节请参考官网，或其他教程

## 一个完整的Spring Boot Starter可能包含以下组件：

- autoconfigure模块：包含自动配置的代码
- starter模块：提供对autoconfigure模块的依赖，以及一些其它的依赖

```
    PS：如果你不需要区分这两个概念的话，也可以将自动配置代码模块与依赖管理模块合并成一个模块
    PS：本例子合并成了一个模块
```

##  autoconfigure模块

- 建议在autoconfigure模块中包含下列依赖：
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-autoconfigure-processor</artifactId>
        <optional>true</optional>
    </dependency>
```


##  starter模块

- starter是一个空jar。它唯一的目的是提供这个库所必须的依赖。
- 你的starter必须直接或间接引用核心的Spring Boot starter（spring-boot-starter）
    

##  starter创建步骤

1.  实现XxxxxxAutoConfiguration.class类，里面配置了starter默认自动使用的配置，并加上@Conditional限制条件.这需要实现需要自动装载的bean；
2.  限制Auto-Configuration的顺序，使用 @AutoConfigureAfter or @AutoConfigureBefore可以限制XxxxxxAutoConfiguration.class的加载顺序；
3.  在META-INF/spring.factories里面加上如下：
     ```
        # 如果有多个XxxxxAutoConfiguration，用逗号分隔
        org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
           com.example.starter.XxxxxxAutoConfiguration
     ```
4.  创建metadata，在目录META-INF/spring-configuration-metadata.json下，作用：
    - XxxxxxAutoConfiguration需要用到的一些properties的，这些properties我们可以写在application.properties里面，然后通过这个文件的规约给出提示，或者对应的properties类上要有注释；
    - 这个xx-metadata.json文件（由spring-boot-autoconfigure-processor）生成在 target/classes/META-INF/spring-configuration-metadata.json；
    - 同时，IDEA中检查下：Preferences->Build, Execution, Deployment->Compile->Annotation Processors->Enable annonation processing 
    - mvn clean install后需要copy到xxx-Starter项目的 resources/META-INF/ 目录下
5.  创建starter，其实对于每个starter里面并没有源码，只是依赖了一个对应的autoconfiguration；


##  注意

1. 在其他项目pom文件中引入我们新创建的starter依赖后，Class import不了，提示 Cannot resolve symbol 'packageName'
    - 这时候要检查我们starter中，是否删除pom文件中的spring-boot-maven-plugin插件
    - 因为starter生成的jar与可执行jar的目录是有区别的
    
## 测试使用

### pom

```
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>starter-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```

### controller

```
    @RestController
    @RequestMapping("/say")
    public class SayController {
    
        @Resource
        public com.example.starter.demo.service.StartDemoService startDemoService;
    
        @GetMapping("/{name}")
        public String hello(@PathVariable("name") String name) {
            return startDemoService.sayHello(name);
        }
    
        @GetMapping("/info")
        public String info() {
            return startDemoService.helloWorld();
        }
    }
```