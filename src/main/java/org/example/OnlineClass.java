package org.example;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    private Progress progress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        // 자바 8 이전에는 이렇게, 에러를 던지는 방법..
        /*
        if(this.progress == null) {
            throw new IllegalStateException();
        }
        */

        // 혹은 그냥 null 을 리턴하는 방법 , 본 코드에서 알아서 잘 처리하는 것.


        // 자바8 이상에서는 optional 을 감싸서 리턴한다.
        return Optional.ofNullable(progress);
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}