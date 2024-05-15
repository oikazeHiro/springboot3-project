package com.oik.api;

import com.oik.api.entity.ACTrie;
import com.oik.api.entity.NumberRules;
import com.oik.api.entity.SensitiveWords;
import com.oik.api.service.NumberRulesService;
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

    @Resource
    private NumberRulesService numberRulesService;
    @Test
    void acTest(){
        ACTrie acTrie3 = new ACTrie();
        long start = System.currentTimeMillis();
        acTrie3.insert("程");
        acTrie3.insert("程序");
        acTrie3.insert("程序员");
        acTrie3.insert("程序学员");
        acTrie3.insert("学徒");
        acTrie3.insert("初级");
        acTrie3.insert("开发");
        acTrie3.insert("开发商");
        acTrie3.insert("初级开发");
        acTrie3.insert("工程师");
        acTrie3.insert("初级开发工程师");
        long insertEnd = System.currentTimeMillis();
        acTrie3.buildFailurePointer();
        long buildEnd = System.currentTimeMillis();
        System.out.println(acTrie3.parseText("你好，我想找一个初级开发相关的程序员工作，从学徒做起"));
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

    @Test
    void  testRules(){
        NumberRules numberRules = new NumberRules();
        numberRules.setCode("PC");
        numberRulesService.saveOrUpdate(numberRules);
    }

    @Test
    void testRule2(){
        SensitiveWords sensitiveWords = new SensitiveWords();
        sensitiveWords.setWords("奶奶的");
        sensitiveWordsService.saveOrUpdate(sensitiveWords);
    }
}
