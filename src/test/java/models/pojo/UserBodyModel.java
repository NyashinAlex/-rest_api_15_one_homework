package models.pojo;

public class UserBodyModel {
//    String user = "{\"name\": \"" + name + "\",\"job\": \"" + job + "\"}";

    private String name, job;

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
