package com.team.semiTravelRecommend.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
//import oracle.sql.CLOB;
//import java.sql.Clob;
//import java.sql.Date;
import java.sql.Clob;
import java.util.Date;


@Data
public class PlannerDTO {

    private int plan_no; // Integer
    private int user_no;
    private String travel_place;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_due_date;  /*string -> date로 바꿔야함*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_due_date;
    private String lodging_info;
    private String transportation;
    private String checklist;
    private String pl_detail; /*string -> clob로 바꿔야함*/

}
