package com.wy.sysmanage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysmanageApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        String userName="admin";
        String password="111111";
        String enPassword= SHA256Util.sha256(password,userName);
        System.out.printf(enPassword);
    }


}
