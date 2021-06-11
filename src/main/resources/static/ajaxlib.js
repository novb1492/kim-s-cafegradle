
window.onload=function(){
 
        document.querySelectorAll('.button').forEach(function(item) {
            item.addEventListener('click', function() {
                    var xhr = new XMLHttpRequest(); //new로 생성
                    xhr.open('POST', item.id, true); //j쿼리 $ajax.({type,url},true가 비동기)
                    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");////이게없으면 psot전송불가 조금 찾았네
                    xhr.send("test="+item.name); /// ajax data부분
                    xhr.onload = function() { 
                        if(xhr.status==200){ // success:function(data)부분 통신 성공시 200반환
                            alert(xhr.response);
                        }
                    }
            });
            });  
    
    
}
