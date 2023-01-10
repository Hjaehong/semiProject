package com.team.semiTravelRecommend.paging;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SelectCriteria implements Serializable {
    private int pageNo;         // 요청한 페이지 번호
    private int totalCount;     // 전체 게시물 수
    private int limit;          // 한 페이지에 보여줄 게시물 수
    private int buttonAmount;   // 한 번에 보여줄 페이징 버튼의 수
    private int maxPage;        // 가장 마지막 페이지
    private int startPage;      // 한 번에 보여줄 페이징 버튼의 시작하는 페이지 수
    private int endPage;        // 한 번에 보여줄 페이징 버튼의 마지막 페이지 수
    private int startRow;       // DB 조회 시 최신 글 부터 조회해야 하는 행의 시작 수
    private int endRow;         // DB 조회 시 최신 글 부터 조회해야 하는 행의 마지막 수
}
