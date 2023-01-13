
$(function (){
    commentList(); // selectReplyList
    // 댓글 등록 버튼 클릭시
    $("#commentSubmit").click(function (){

        const userId = document.getElementById('userId').value;
        let recordNo = document.getElementById('recordNo').value;
        // 댓글을 작성하지 않고 버튼 클릭시 작성 안되게
        if($("#commentContain").val().trim().length != 0){

            $.ajax({
                url : "/record/insertComment" ,
                type : "post" ,
                data:{comContain:$("#commentContain").val() ,
                    recordNo: recordNo ,
                    userNo: userId},
                success:function (result){
                    console.log(result);
                    if(result > 0){
                        $("#commentContain").val("");
                        commentList();
                    }else{
                        alert("댓글을 등록할 수 없습니다.");
                    }
                },error:function (){
                    console.log("ajax 실패");
                }
            });
        }else{
            alert("댓글을 작성하고 눌러주세요");
        }
    });
});

function commentList(){
    let recordNo = document.getElementById('recordNo').value;
    $.ajax({
        url:"/record/listComment" ,
        data:{'recordNo':recordNo} ,
        type:"post",
        dataType: "json",
        success:function (list){
            $("#comCount").text(list.length);// 총 댓글수

            let value="";
            $.each(list, function (i, obj){

                value += "<span>" + "작성자: "+ obj.userNo + "</span>"
                    + "<span>" +" | " + obj.comContain + "</span>" + "</br>";
                console.log(value);
            });
            $("#commentListView").html(value);
        },error:function (){
            console.log("댓글 리스트 ajax 실패");
        }
    });

}