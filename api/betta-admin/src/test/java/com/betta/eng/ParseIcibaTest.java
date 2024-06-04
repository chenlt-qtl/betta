package com.betta.eng;

import com.betta.eng.domain.EngWord;
import com.betta.eng.service.IEngWordService;
import com.betta.eng.utils.ParseIciba;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class ParseIcibaTest {

    @Autowired
    IEngWordService engWordService;

    @Test
    void test(){
        EngWord afford = ParseIciba.getWordFromIciba("afford");
        System.out.println(afford);
    }
}
