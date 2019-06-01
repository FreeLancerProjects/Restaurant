package com.creativeshare.restaurant.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sub_Catogry_Model_Slide


        implements Serializable {
    public ArrayList<Sub> getSub() {
        return sub;
    }

    ArrayList<Sub> sub;

    public class Sub {
        private int id;
        private String ar_title;
        private String en_title;
        private String ar_des;
        private String en_des;
        private String image;
        private String price;
        private int cat_id;
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

        public String getAr_des() {
            return ar_des;
        }

        public String getEn_des() {
            return en_des;
        }

        public String getImage() {
            return image;
        }

        public String getPrice() {
            return price;
        }

        public int getCat_id() {
            return cat_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }


}

