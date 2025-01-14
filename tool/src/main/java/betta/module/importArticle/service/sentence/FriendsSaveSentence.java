package betta.module.importArticle.service.sentence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component("friends")
public class FriendsSaveSentence extends SaveSentence{

    @Override
    Pattern getPattern() {
        return Pattern.compile("(\\[[a-zA-Z:]+\\])|(字幕转换助手|tvsubtitles\\.net)");
    }

    @Override
    String getCharSet() {
        return "GBK";
    }

    @Override
    public Long getGroupId() {
        return 6L;
    }
}
