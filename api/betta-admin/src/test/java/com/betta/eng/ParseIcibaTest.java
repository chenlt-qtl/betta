package com.betta.eng;

import com.betta.eng.domain.EngWord;
import com.betta.eng.utils.dict.DictUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ParseIcibaTest {

    @Autowired
    DictUtils dictUtils;

    @Test
    void test(){
        EngWord afford = dictUtils.getWord("afford");
        System.out.println(afford);
    }
}
