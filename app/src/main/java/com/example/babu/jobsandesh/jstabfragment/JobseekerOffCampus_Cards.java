package com.example.babu.jobsandesh.jstabfragment;

/**
 * Created by Alok on 25/Jan/17.
 */

public class JobseekerOffCampus_Cards {
    String emp_id,com_name,post;

    public JobseekerOffCampus_Cards(String emp_id, String com_name, String post) {
        this.emp_id = emp_id;
        this.com_name = com_name;
        this.post = post;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
