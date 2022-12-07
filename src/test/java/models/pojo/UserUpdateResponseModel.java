package models.pojo;

public class UserUpdateResponseModel {
//    {
//        "name": "Крюкова Раиса",
//        "job": "Principal Accounting Analyst",
//        "updatedAt": "2022-12-07T09:39:28.522Z"
//    }
    String name, job, updatedAt;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
