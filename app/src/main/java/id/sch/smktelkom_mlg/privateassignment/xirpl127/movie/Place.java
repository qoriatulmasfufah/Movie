package id.sch.smktelkom_mlg.privateassignment.xirpl127.movie;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Qoriatul Masfufah on 5/14/2017.
 */

public class Place extends SugarRecord implements Serializable {
    String title;
    String deskripsi;
    String urlgambar;

    public Place() {

    }

    public Place(String title, String descripsi, String urlgambar) {
        this.title = title;
        this.deskripsi = descripsi;
        this.urlgambar = urlgambar;

    }


}
