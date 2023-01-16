package com.team.semiTravelRecommend.model.dto.record;

import lombok.Data;
import java.sql.Date;

@Data
public class PlannerDTO {

    private int rowNum;
    private int planNo;
    private int userNo;
    private String travelPlace;
    private Date startDueDate;
    private Date endDueDate;
    private String lodgingInfo;
    private String transportation;
    private String checkList;
    private String plDetail;
//    private UserDTO userDTO;
}
