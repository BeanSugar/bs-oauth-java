# bs-oauth-java
자바 OAuth 라이브러리


## 기능목록
Facebook
  로그인
  bearer
Naver
Kakao
Twitter


2차. SpringSecurity 연동

OAuth 1,2 클라이언트.


## 사용법 Usage

사용법은 test - org.beansugar.oauth.examples.~ 참고


### 스프링에서 사용 예시(예 facebook)
```java
@Configuration
public class BeanConfig {
	@Bean
	public OAuth20Service facebookService(){
		return new OAuth20ServiceSimple(
				new FacebookApi(),
				new OAuth20Config(
						"your_api_key",
						"your_api_secret",
						//your redirect url(풀 주소인 경우도 있고 일부 사이트는 경로만 쓰는 경우도있고)
						"http://sso.beansugar.org/oauth_callback",
						//null해도 되는데 값을 더 받아오려면 추가해야함
						null
				)
		);
	}
}
```

### Maven Setting

```xml
<!-- repository -->
<repositories>
	<repository>
		<id>beansugar-public-snapshot</id>
		<url>http://nexus.beansugar.org/content/repositories/public-snapshot</url>
	</repository>
</repositories>

<!-- dependency -->
<dependency>
	<groupId>org.beansugar.oauth</groupId>
	<artifactId>bs-oauth-java</artifactId>
	<version>bs-oauth-201612-2-BUILD-SNAPSHOT</version>
</dependency>
```

