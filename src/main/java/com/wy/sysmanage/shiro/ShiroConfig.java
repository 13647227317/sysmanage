package com.wy.sysmanage.shiro;

import cn.hutool.crypto.digest.DigestAlgorithm;
import com.wy.sysmanage.util.ShiroUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置类
 * @author wangyong
 */
@Configuration
public class ShiroConfig {

    @Value("${shiro.anon-urls}")
    private List<String> anonUrlList;

    @Value("${shiro.login-url}")
    private String loginUrl;

    @Value("${shiro.session.timeout:30}")
    private Long globalSessionTimeout;

    /**
     * Shiro基础配置
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        if(CollectionUtils.isNotEmpty(anonUrlList)){
            anonUrlList.forEach(anonUrl -> filterChainDefinitionMap.put(anonUrl,"anon"));
        }
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm, CacheManager ehCacheManager){
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(new ShiroSessionManager(globalSessionTimeout));
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager=new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 鉴权advisor
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        hashedCredentialsMatcher.setHashAlgorithmName(DigestAlgorithm.SHA256.getValue());
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(ShiroUtil.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
}
