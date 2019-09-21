package shebi.library.golibraryapp.Model;

public class AddCategoryModel {
   private String name,imageUrl;

    public AddCategoryModel(String name, String imageUrl) {
        if (name.trim().equals("")){
            name="No Name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public AddCategoryModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

