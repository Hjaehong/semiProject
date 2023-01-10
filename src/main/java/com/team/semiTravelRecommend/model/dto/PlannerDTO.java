package com.team.semiTravelRecommend.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
//import oracle.sql.CLOB;
//import java.sql.Clob;
//import java.sql.Date;
import java.sql.Clob;
import java.sql.Date;


@Data
public class PlannerDTO {

    private int planNo; // Integer
    private int userNo;
    private String travelPlace;
    private Date startDueDate;  /*string -> date로 바꿔야함*/
    private Date endDueDate;
    private String lodgingInfo;
    private String transportation;
    private String checklist;
    private String plDetail; /*string -> clob로 바꿔야함*/

}
