package com.example.btgk;

public class Food {
    String title;
    String content;
    String money;
    int imageId;
    String add;
    String remove;
    public Food() {
    }

    public Food(String title, String content, String money, int imageId, String add, String remove) {
        this.title = title;
        this.content = content;
        this.money = money;
        this.imageId = imageId;
        this.add =add;
        this.remove = remove;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

}
