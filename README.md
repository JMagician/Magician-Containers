<h1> 
    <a href="https://magician-io.com">Magician-Containers</a> ·
    <img src="https://img.shields.io/badge/licenes-MIT-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/jdk-11+-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/maven-3.5.4+-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/release-master-brightgreen.svg"/>
</h1>

Magician-Containers is a container management module that allows for the unified management of beans in a project, which brings two extensions: AOP and timed tasks

## Documentation

[https://magician-io.com](https://magician-io.com)

## Example

### Importing dependencies

```xml
<!-- This is the jar package build by this project -->
<dependency>
    <groupId>com.magician.containers</groupId>
    <artifactId>Magician-Containers</artifactId>
    <version>1.0.0</version>
</dependency>

<!-- This is Magician -->
<dependency>
    <groupId>com.github.yuyenews</groupId>
    <artifactId>Magician</artifactId>
    <version>2.0.5</version>
</dependency>

<!-- This is the log package, which supports any package that can be bridged with slf4j -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-jdk14</artifactId>
    <version>1.7.12</version>
</dependency>
```

### Specify bean

Cannot be used on controllers

```java
@MagicianBean
public class DemoBean {

}
```

### Aop

```java
public class DemoAop implements BaseAop {

    public void startMethod(Object[] args) {
        
    }
    
    public void endMethod(Object[] args, Object result) {

    }
    
    public void exp(Throwable e) {

    }
}
```

```java
@MagicianBean
public class DemoBean {

    @MagicianAop(className = DemoAop.class)
    public void demoAopMethod() {

    }
}
```

### Timer

```java
@MagicianBean
public class DemoBean {
    
    @MagicianTimer(loop=1000)
    public void demoTimerMethod() {

    }
}
```

### getBean

```java
DemoBean demoBean = BeanUtil.get(DemoBean.class);
```

### Start HTTP service

You must wait for the san method of the magician to execute before you can load the bean

```java
HttpServer httpServer = Magician
        .createHttp()
        .scan("com.test"); // Scanning range (package name)

// You must wait for the san method of the magician to execute before you can load the bean
MagicianContainers.load();

httpServer.bind(8080);
```