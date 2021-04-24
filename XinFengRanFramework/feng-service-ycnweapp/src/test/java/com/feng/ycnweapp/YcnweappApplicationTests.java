package com.feng.ycnweapp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YcnweappApplicationTests {

    // @Autowired
    // GradeClbumRepository gradeClbumRepository;

    @Test
     public void contextLoads() {
        System.out.println("进入测试");

        List<String> list = new ArrayList();
        list.add("123");
        System.out.println("测试list:"+list);
        List<String> list1 = new ArrayList<>();
        list1.add("234");
        System.out.println("测试list1:"+list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("456");
        System.out.println("测试list2:"+list2);






    }

}
