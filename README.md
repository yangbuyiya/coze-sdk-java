[//]: # (<h1>)

[//]: # (<p align="center">)

[//]: # (	<strong>愿世界和平</strong>)

[//]: # (</p>)

[//]: # (</h1>)

[//]: # ()
[//]: # (<p align="center">)

[//]: # (    铭记苦难，心系巴勒斯坦，哀悼逝者。)

[//]: # (    <br>中国屹立东方，自强自立，方能更好地守护和平，担当国际责任，为世界公正贡献力量。)

[//]: # (    <br>愿悲剧不再重演，和平永驻人间。)

[//]: # (    <br />)

[//]: # (    <img src="./img/img.png" alt="Easy-Manager-Tool" width="75" style="align-content: center" /> )

[//]: # (    <span>Peace And Love</span>)

[//]: # (    <img src="./img/img_1.png" alt="Easy-Manager-Tool" width="99" style="align-content: center" />)

[//]: # (</p>)

[//]: # ()
[//]: # (<br />)

[//]: # (<br />)

[//]: # (<br />)

[//]: # (<br />)

[//]: # (<br />)

[//]: # (<br />)

[//]: # ()

<h1 align="center">
    <img src="img/coze.svg" width="128" />
    <br />
	<strong>COZE-SDK-JAVA v1.0.0</strong>
</h1>
<h3 align="center">
     扣子智能体 API Java SDK 是对扣子智能体的API进行了封装，方便Java开发者接入系统调用。
</h3>

<div align="center"><b>您是第<img src="https://profile-counter.glitch.me/coze-sdk-java/count.svg"></img>个访问COZE-SDK-JAVA~</b></div>

# 接入方式

## 引入依赖
### Maven
```yaml

最新版本获取：https://central.sonatype.com/artifact/com.yby6.coze/coze-sdk-java

<dependency>
    <groupId>com.yby6.coze</groupId>
    <artifactId>coze-sdk-java</artifactId>
    <version>${last}</version>
</dependency>

```
### Gradle
```yaml

implementation group: 'com.yby6.coze', name: 'coze-sdk-java', version: '${last}'

```

## 单元测试

```java

@Before
public void test_session_Factory() {
    // 1. 配置文件
    CoZeConfiguration cozeConfiguration = new CoZeConfiguration();
    cozeConfiguration.setApiHost("https://api.coze.cn/open_api/");
    cozeConfiguration.setApiKey("Bearer 你的APIKEY");
    cozeConfiguration.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    // 2. 会话工厂
    DefaultCoZeSessionFactory factory = new DefaultCoZeSessionFactory(cozeConfiguration);
    // 3. 开启会话
    this.coZeSession = factory.openSession();
    log.info("openAiSession:{}", coZeSession);
}


```
