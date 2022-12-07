package models.pojo;

public class UserResponseModel {
//    {
//        "name": "Регина Леонидовна Тетерина",
//        "job": "Legacy Technology Planner",
//        "id": "643",
//        "createdAt": "2022-12-07T07:41:30.895Z"
//    }

    String name, job, id, createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
