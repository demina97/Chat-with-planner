package kz.aupet.vt152.diplom.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class GetTasksRequest {
    public String phone;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public Date date;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
