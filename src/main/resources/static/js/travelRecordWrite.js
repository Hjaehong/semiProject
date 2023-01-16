// // 선택한 지역에 따라 도시를 가져오는 함수
// function readCity(locCode) {
//
//     let city = document.getElementById("cityList");
//     // let location = document.getElementById("locationList");
//     // location.attr("required", true);
//     city.options.length = null;
//
//     $.ajax({
//         type: "POST",
//         url: "/record/city",
//         data: {'locCode': locCode},
//         dataType: "json",
//         success: function (cityDTO) {
//             // console.log(cityDTO[0]);
//
//             // let city = document.getElementById("city-code");
//
//             for (let i = 0; i < cityDTO.length; i++) {
//                 city.options[i] = new Option(cityDTO[i].cityName, cityDTO[i].cityCode);
//             }
//
//             city.innerHTML += '<option value="" disabled selected>--- 도시선택 ---</option>';
//             // city.attr("required", true);
//
//         }, error: function (e) {
//             alert("발생한 에러 확인" + e);
//         }
//     })
// };
//
// // 업로드된 파일 용량 확인하는 함수
// function checkFileSize(uploadFile){
//
//     if (uploadFile && uploadFile[0]) {
//
//         let maxSize = 10 * 1024 * 1024;
//         let fileSize = uploadFile[0].size;
//
//         if(fileSize > maxSize){
//             alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
//             $(this).val('');
//             return false;
//         }
//     }
// };
//
// // input이 모두 채워졌는지 확인하는 함수
// function registerRecord(){
//     let selectLoc = document.getElementById("location");
//     let selectCity = document.getElementById("cityList");
//     let selectTag = document.getElementById("tagList");
//
//     if (selectLoc.val() =="" || selectCity.val() =="" || selectTag.val() =="" ){
//         return false
//     } else {
//         return true;
//     }
// }