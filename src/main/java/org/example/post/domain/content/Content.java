package org.example.post.domain.content;

import org.example.post.domain.common.DateTimeInfo;

// PostContent, 댓글 기능 추상화해서 다형성 적용한다.
// 책임 바탕으로 Content가 하는 일을 만들음
public abstract class Content {

    protected String contentText;
    protected final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        this.dateTimeInfo.updateEditeDatetime();
    }

    protected abstract void checkText(String content);

    public String getContentText() {
        return contentText;
    }
}