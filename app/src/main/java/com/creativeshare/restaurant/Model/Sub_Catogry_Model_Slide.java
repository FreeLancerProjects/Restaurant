package com.creativeshare.restaurant.Model;

import java.io.Serializable;

public class Sub_Catogry_Model_Slide


  implements Serializable {



            private int id;
            private String name;

            private int image;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getImage() {
                return image;
            }

            public String getCreated_at() {
                return created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

