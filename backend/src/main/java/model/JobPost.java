package model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "jobposts")
public class JobPost {
/*    @MongoId
    @Indexed(unique = true)
    @JsonIgnore
    private int id;*/
    private String desc;
    private int exp;
    private String profile;
    private List<String> techs;

    public JobPost() {};

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<String> getTechs() {
        return techs;
    }

    public void setTechs(List<String> techs) {
        this.techs = techs;
    }


    @Override
    public String toString() {
        return "JobPost{" +
                "desc='" + desc + '\'' +
                ", exp=" + exp +
                ", profile='" + profile + '\'' +
                ", techs=" + techs +
                '}';
    }
}
