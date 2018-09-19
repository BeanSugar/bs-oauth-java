# bs-oauth-java
자바 OAuth2 Client 라이브러리

1.0a 안씀. 제대로된 사이트는 전부 1.0 지원 끊음.

spec
https://tools.ietf.org/html/rfc6749

## 기능목록
Facebook
  로그인
  bearer
Naver
Kakao

2차. SpringSecurity 연동

OAuth 1,2 클라이언트.


## 사용법 Usage

적절히


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
			<id>bintray-archmagece-jvm-repo</id>
			<url>https://dl.bintray.com/archmagece/jvm-repo</url>
	</repository>
</repositories>
```

```xml
<!-- dependency -->
<dependency>
	<groupId>org.scriptonbasestar.oauth</groupId>
	<artifactId>sb-oauth-java</artifactId>
	<version>sb-oauth-20180914-DEV</version>
</dependency>
```

