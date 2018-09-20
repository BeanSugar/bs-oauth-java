# bs-oauth-java
자바 OAuth2 Client 라이브러리

로그인 기능까지만 개발. oauth-integration에서 spring 연동기능을 추가하려고 했으나
sso-helper과 겹쳐서 제외예정.
여기선 테스트 코드정도만
oauth1.0a은 제외. 제대로된 사이트는 전부 1.0 지원 끊음.


## 모듈 설명

```text
run
oauth-client <- oauth-storage <- oauth-connector 

test
oauth-client <- test-helper <- oauth-connector-* 
```
spec
https://tools.ietf.org/html/rfc6749


## 지원모듈

* Naver
* Kakao
* Google
* Facebook


## 사용법 Usage

테스트시에 oauth-connector-* 프로젝트의 test/resources/setting.cfg 를 추가
```properties
client_id=clientidstring
client_secret=clientsecretstring
service_name=NAVER
redirect_uri=http://test4.polypia.net:8080/auth/action/social/naver/redirect
resource_profile_uri=https://openapi.naver.com/v1/nid/me
```
까까오는 secret에 아무거나 넣던가 secret를 사용하면 그걸 넣던가 해야함


### 스프링에서 사용 예시(예 facebook)(구버전. 변경필요)
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

gradle은 알아서

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

## exit()

oauth 프로토콜은 공통코드를 쓸 수 없지 않을까

스펙이 너무 다양해서 로그인 프로세스나 파라미터도 제가각이다.
사실상 표준화 실패로 봐야하지 않나.
로그인 및 access_token 획득까지만 처리. 이후엔 해당 사이트별로 처리.

어차피 진짜 미친듯이 많아야 열개, 보통 2~4개 할건데 각 사이트 개별 SDK 사용하는것도 괜찮을 것 같다.
