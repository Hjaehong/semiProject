// 다음페이지로 이동하는 함수
function nextPage(text) {
    location.href = "/record/recordList" + "?currentPage=" + text;
}