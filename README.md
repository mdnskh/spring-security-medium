# Spring Security with web

### Enable/disable spring security based on profiles

#### Configure filterChain bean as usual with for all profile

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

### Customize security to make it more unit testable

#### create a utility in every test class to inject TestUser

```kotlin

@BeforeEach
fun setup(){
    SecurityContextHolder.getContext().authentication =
        TestingAuthenticationToken("testUserName", "testPassword", "ROLE_USER")
}

```

It does not allow very flexible way of providing control over how to handle current authentication


#### Create utility class and make it injectable as dependency so it is clear that a class needs data from spring security

```kotlin

securityUtils.getAutheticatedUser() : CustomeUser 


```

in test securityUtils can be mocked.

