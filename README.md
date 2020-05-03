[![codecov](https://codecov.io/gh/a252937166/help-test/branch/master/graph/badge.svg)](https://codecov.io/gh/a252937166/help-test)
[![Build Status](https://www.travis-ci.org/a252937166/help-test.svg?branch=master)](https://www.travis-ci.org/a252937166/help-test)
[![GitHub license](https://img.shields.io/github/license/a252937166/help-test)](https://github.com/a252937166/help-test/blob/master/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/a252937166/help-test)](https://github.com/a252937166/help-test/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/a252937166/help-test)](https://github.com/a252937166/help-test/network)


# 框架初衷

在编写自己模块的单元测试的时候，依赖了其他模块的`service`或者其他`bean`，这时需要做mock，但是`mockito`或者`powermockito`对于我的需求来说又过于臃肿，而且我也不需要太过复杂的mock语法，所以就撸了这个小框架，只需要写一个`Mock`类来代替需要的`bean`就好了。

# 用法

详细用法可以查看[github](https://github.com/a252937166/help-test)上的测试用例

## maven

当前版本: 1.0.2

```xml
<!-- https://mvnrepository.com/artifact/com.ouyanglol/spring-help-mock-starter -->
<dependency>
    <groupId>com.ouyanglol</groupId>
    <artifactId>spring-help-mock-starter</artifactId>
    <version>1.0.2</version>
</dependency>
```

## 配置文件

```profile
mock.help.packageRoot=com.xxx.xxx
```

`mock.help.packageRoot`配置当前所有测试代码的包路径，虽然不是必须的，当时建议配置，不然会扫描所有包下的文件(包括其他maven的包)，非常耗时。`mock.help.packageRoot`也不用太过精确，一般建议配置项目启动类的包路径就行了。

## @EnableMock

在启动类，或者Config类上加上`@EnableMock`表示启用`mock`类，不然`mock`不会生效。

## Mock类

mock的原理，是在spring容器，注入bean的时候，用另外一个实例代替它。那么Mock类也不能是任意的class。原则上需要是原本`bean`的子类或者和`bean`实现的同一个接口。

比如原本的bean：

```java
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        log.info("test");
    }
}
```
mock类：

```java
@Slf4j
public class TestServiceMock implements TestService {
    @Override
    public void test() {
        log.info("test Mock");
    }

}
```

## 使用mock

在正常逻辑的代码之上，只需要加个注解就行了:

```java
@HelpMockBean(beanClass = TestServiceMock.class)
private TestService mock;
```

`beanClass`代表你使用的mock类，`TestService`就是需要被mock的`bean`。就这么简单，一看就懂了。