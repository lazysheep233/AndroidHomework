package es.source.code.model;

/**
 * Created by apple on 2017/10/16.
 */

public class Food {
    private String name;
    private int price;
    private int photo;
    private boolean state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int age) {
        this.price = age;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public boolean getState() {return  state;}

    public void setState(boolean a) {this.state = a;}
}
