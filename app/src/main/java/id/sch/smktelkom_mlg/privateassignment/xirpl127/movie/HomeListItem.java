package id.sch.smktelkom_mlg.privateassignment.xirpl127.movie;

/**
 * Created by Qoriatul Masfufah on 5/8/2017.
 */

//seperti model, diterapkan pada adapter

public class HomeListItem {

    private String imageUrl;
    private String head;
    private String desc;


    public HomeListItem(String imageUrl, String head, String desc) {

        this.imageUrl = imageUrl;
        this.head = head;
        this.desc = desc;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
