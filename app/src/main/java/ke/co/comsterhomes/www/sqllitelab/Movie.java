package ke.co.comsterhomes.www.sqllitelab;

/**
 * Created by collinsnyamao on 10/24/17.
 */

public class Movie {

    int _id;
    String _name,_genre;


    public Movie(int id,String name , String genre){
        this._id = id;
        this._name = name;
        this._genre = genre;
    }

    public int get_id() {
        return this._id;
    }

    public String get_genre() {
        return this._genre;
    }

    public String get_name() {
        return this._name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_genre(String _genre) {
        this._genre = _genre;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
