package com.team.semiTravelRecommend.paging;
 /**
    * Version : 1.0
   * 클래스명: pagenation
   * 작성일자 : 2023/01/04
 * 작성자 : heojaehong
   * 설명 : 페이징 처리를 위한 클래스
   * 수정일자 :
   * 수정자 :
   * 수정내역 :
 */
public class Pagenation {

  public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount){
   return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null);
  }
    // 페이징 처리⁄
  public static SelectCriteria getSelectCriteria(int pageNo, int totalCount, int limit, int buttonAmount, String tagCode) {

   int maxPage;   // 전체 페이지에서 가장 마지막 페이지 표시
   int startPage; // 한번에 표시될 페이지 버튼의 시작할 페이지
   int endPage;   // 한번에 표시될 페이지 버튼의 끝나는 페이지
   int starRow;
   int endRow;

   // 총 페이지 수 계산
   maxPage = (int) Math.ceil((double) totalCount/ limit);

   // 현재 페이지에 보여주는 시작 페이지
   startPage = (int)(Math.ceil((double) pageNo/buttonAmount)-1) * buttonAmount + 1;
   // 현재 페이지에 보여지는 마지막 페이지
   endPage = startPage + buttonAmount - 1;

   // maxpage가 더 작으면 마지막 페이지를 maxPage로 지정
   if(maxPage < endPage) endPage = maxPage;
   // 마지막 페이지가 0
   if(maxPage == 0 && endPage == 0) {
    maxPage = startPage;
    endPage = startPage;
   }
    // 시작 번호, 마지막 번호를 계산
    starRow = (pageNo -1) * limit + 1;
    endRow = starRow + limit -1;

    System.out.println("starRow = " + starRow);
    System.out.println("endRow = " + endRow);

    SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount, maxPage, startPage, endPage, starRow, endRow, tagCode);
    return selectCriteria;
  }

}
