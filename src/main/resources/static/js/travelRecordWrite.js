function readCity(locCode) {

    $.ajax({
        type: "GET",
        url: "/record/city",
        data: locCode,
        dataType: "JSON",
        success: function (cityDTO) {
            console.log(cityDTO[0]);
            let cityCode = document.cityCode;
            for (let i = 0; i < cityDTO.length; i++) {
                cityCode.options[i] = new Option(cityDTO[i].getCityName, cityDTO[i].getCityCode);
            }
        }, error: function () {
            alert(" 도시코드 불러오기 실패");
        }
    })
};
