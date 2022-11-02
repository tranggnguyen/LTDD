package com.example.btgk;

public class Food {
    int id;
    String title;
    String content;
    String money;
    String imageId;
    String add;
    String remove;
    public Food() {
    }

    public Food( int id, String title, String content, String money, String imageId, String add, String remove) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.money = money;
        this.imageId = imageId;
        this.add =add;
        this.remove = remove;
    }
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
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
