package com.betta.eng.utils.dict;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.file.FileUtils;
import com.betta.common.utils.http.HttpUtils;
import com.betta.eng.domain.EngIcibaSentence;
import com.betta.eng.domain.EngWord;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class IcibaUtils {

    private final static String KEY = "C772DB1F60B2839AD948507D91E7B04A";

    private final static String URL = "http://dict-co.iciba.com/api/dictionary.php";

    public EngWord getWord(String wordName) {
        EngWord word;
        try {
            String rspStr = HttpUtils.sendGet(URL, "w=" + wordName + "&key=" + KEY, Constants.UTF8);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取爱词霸单词出错：", wordName);
                throw new ServiceException("获取爱词霸单词出错：" + wordName);
            }
            word = parse(rspStr, wordName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取爱词霸单词出错：" + wordName +
                    ",信息:" + e.getMessage());
        }
        return word;
    }

    /**
     * 解析查询结果
     *
     * @param data
     * @param wordName
     * @return
     * @throws Exception
     */
    private static EngWord parse(String data, String wordName) throws Exception {
        EngWord word = new EngWord();
        word.setWordName(wordName);

        //设置xml的值都以String类型显示
        JSONObject jsonObject = JSONUtil.xmlToJson(data);
        Dict dict = jsonObject.getBean("dict", Dict.class);
        List<String> ps = dict.getPs();//音标
        if (!ps.isEmpty()) {
            word.setPhonetics(ps.get(ps.size() - 1));//最后一个音标是美式音标
        }

        List<String> pron = dict.getPron();//mp3

        if (!pron.isEmpty()) {
            String relativePath = DictUtils.getWordMp3RelativePath(word.getWordName());
            FileUtils.writeBytes(pron.get(pron.size() - 1), relativePath);
            word.setPhMp3(Constants.RESOURCE_PREFIX + relativePath);
        }

        List<String> pos = dict.getPos();//词性
        List<String> acceptation = dict.getAcceptation();//词性对应的解释
        if (pos.size() != acceptation.size()) {
            throw new ServiceException("意思和时态配对失败");
        }

        String acceptationStr = "";


        //处理解释
        for (int i = 0; i < pos.size(); i++) {

            String posStr = pos.get(i);
            String acceStr = acceptation.get(i);

            if (StringUtils.isNotBlank(posStr)) {
                acceptationStr += posStr + "  ";
            }
            acceptationStr += (acceStr.replace("\n", "")) + "|";
        }
        word.setAcceptation(acceptationStr);

        //处理例句
        List<Sent> sents = dict.getSent();

        List<EngIcibaSentence> isList = new ArrayList();
        if (sents != null) {
            for (Sent sent : sents) {
                EngIcibaSentence is = new EngIcibaSentence();
                is.setOrig(sent.getOrig());
                is.setTrans(sent.getTrans());
                isList.add(is);
            }
        }

        word.setIcibaSentenceList(isList);
        return word;
    }
}

/**
 * 爱词霸返回的对象
 */
@Data
class Dict {

    /**
     * 单词
     */
    private String key;

    /**
     * 音标
     */
    private List<String> ps;

    /**
     * 发音MP3
     */
    private List<String> pron;

    /**
     * 词性
     */
    private List<String> pos;

    /**
     * 词性对应的解释
     */
    private List<String> acceptation;

    /**
     * 例句
     */
    private List<Sent> sent;

}

/**
 * 例句
 */
@Data
class Sent {
    /**
     * 例句
     */
    private String orig;

    /**
     * 解释
     */
    private String trans;
}