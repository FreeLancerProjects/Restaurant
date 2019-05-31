package com.creativeshare.restaurant.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catogry_Model_Slide


  implements Serializable {
List<Cat> cat;

    public List<Cat> getCat() {
        return cat;
    }

    public class Cat implements Serializable {

        private int id;
        private String ar_title;
        private String en_title;
        private String cat_image;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public String getAr_title() {
            return ar_title;
        }

        public String getEn_title() {
            return en_title;
        }

        public String getCat_image() {
            return cat_image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
