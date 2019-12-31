package com.wy.sysmanage.shiro;

import com.wy.sysmanage.util.SHA256Util;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
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
    public SecurityManager securityManager(ShiroRealm shiroRealm){
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        hashedCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
}
