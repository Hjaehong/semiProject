package com.team.semiTravelRecommend.model.dto.planner;

import lombok.Data;

@Data
public class PlannerDTO {

    private int plan_no; // Integer
    private int user_no;
    private String travel_place;
    private String start_due_date; // string -> date로 바꿔야함
    private String end_due_date;
    private String lodging_info;
    private String transportation;
    private String checklist;
    private String pl_detail; // string -> clob로 바꿔야함
    }
}
