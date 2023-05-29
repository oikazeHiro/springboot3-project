package com.oik.api;

import com.oik.api.entity.ACTrie;
import com.oik.api.entity.SensitiveWords;
import com.oik.api.service.SensitiveWordsService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OikApplicationTests {
    @Test
    void contextLoads() {
    }
    @Resource
    private SensitiveWordsService sensitiveWordsService;
    @Test
    void acTest(){
        ACTrie acTrie3 = new ACTrie();
        long start = System.currentTimeMillis();
        acTrie3.insert("电焊");
        acTrie3.insert("电焊工");
        acTrie3.insert("电焊工人");
        acTrie3.insert("电焊学员");
        acTrie3.insert("电焊学徒");
        acTrie3.insert("电焊学徒工");
        acTrie3.insert("普工电焊工");
        acTrie3.insert("普工电商");
        acTrie3.insert("普工");
        long insertEnd = System.currentTimeMillis();
        acTrie3.buildFailurePointer();
        long buildEnd = System.currentTimeMillis();
        System.out.println(acTrie3.parseText("你好，我想找一个普工电焊工相关的工作"));
        long end = System.currentTimeMillis();
        System.out.println("start = " + start);
        System.out.println("insertTime = " + (insertEnd-start));
        System.out.println("buildTime = " + (buildEnd-insertEnd));
        System.out.println("allTime = " + (end-start));
        System.out.println("end = " + end);
    }

    @Test
    void saveWord(){
        sensitiveWordsService.saveOne(new SensitiveWords().setWords("电焊工人"));
    }
}
