package org.example.quick_start.pojo;

public class Emp {
    private String gender;
    private String job;

    public Emp(String gender, String job) {
        this.gender = gender;
        this.job = job;
    }

    public Emp() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
