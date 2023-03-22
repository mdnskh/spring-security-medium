# Spring Security with Web Applications
In this project you will find ways to organize your code to make it more testable with spring-security.


### Customize security to make it more unit testable


 
#### - You can mock spring security by calling SecurityContextHolder.getContext().authentication. 
   This will make a mocked user be present in your currently running application thread.   

```kotlin

@BeforeEach
fun setup(){
    SecurityContextHolder.getContext().authentication =
        TestingAuthenticationToken("testUserName", "testPassword", "ROLE_USER")
}

```
Complete code on this [link](https://github.com/mdnskh/spring-security-medium/commit/3e56d82e9333272cec4373b2c05d2441c62e98c8).

As you can see there are certain drawbacks to this approach 
1. This will be invoked for each test and same user will be available for all tests.
2. It does not help us in reducing code coupling with spring-security.

#### - Use utility class to communicate with spring-securityCreate utility class and make it injectable as dependency so it is clear that a class needs data from spring security

```kotlin
@Component
class SecurityUtils {
    
    fun getCurrentUser(): User {
        return SecurityContextHolder.getContext().authentication.details as User
    }
}
```
Inject this utility class in your units show that it become clear for caller that there is security dependency and caller might have to mock user.
Complete code on this [link](https://github.com/mdnskh/spring-security-medium/commit/c6f6e635108ca6611a521e9f295de0c4e83b9110).


### Enable/disable spring security based on profiles

You can control spring security based on profile. You can enable/disable or mock security feature using spring profile as shown below 

#### 
```kotlin
    @Bean
    @Profile("dev")
    fun securityCustomizer() : WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers("/**")
        }
    }
```

Complete code on this [link](https://github.com/mdnskh/spring-security-medium/commit/117d1ede7da93aaecf0a975f70cfef7fe27aebfc).


