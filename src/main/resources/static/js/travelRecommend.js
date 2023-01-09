const modals = document.getElementsByClassName('modalClick')
const modalView = document.getElementsByClassName('modalView')
const tagButton = document.getElementsByClassName('tagButton')

let func = []

// Modal 띄우는 함수
function Modal(num) {
    return function() {
        // 그림 클릭시 modal창 띄우는 함수
        // modals[num].onclick = function(){
        //     modalView[num].style.display = 'block';
        //     console.log(num);
        // };
    }

}
// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의
for(let i = 0; i < modalView.length; i++){
    func[i] = Modal(i);
    console.log('Modal함수 호출 후 함수에 정의' + func[i])
}
// 원하는 Modal 수만큼 funcs 함수를 호출한다.
for(let j = 0; j < modalView.length; j++){
    func[j]();
    console.log('func 함수 호출' + func[j])
}

// Modal영역 밖에서 클릭시 Modal를 닫는다.
window.onclick = function (event){
    if(event.target.className == "modalView"){
        event.target.style.display = "none";
    }
}

// 다음페이지로 이동하는 함수
function nextPage(text) {
    location.href = "/recommend/travelRecommend" + "?currentPage=" + text;
}

// 페이징 버튼 미표시
for(let i = 0; i < tagButton.length; i++){
    tagButton[i].addEventListener('click', function (){
        document.getElementById('page').style.setProperty('display','none');
        // document.getElementsByClassName('paging')[i].style.setProperty('display', 'none');
    })
}
