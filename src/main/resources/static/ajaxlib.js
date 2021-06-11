window.onload=function(){

        document.querySelectorAll('.button').forEach(function(item) {
            item.addEventListener('click', function() {
                    var xhr = new XMLHttpRequest(); //new로 생성
                    var value=document.getElementsByClassName(item.id)[0];
                    alert(value.value);
                    xhr.open('POST', item.name, true); //j쿼리 $ajax.({type,url},true가 비동기)
                    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");////이게없으면 psot전송불가 조금 찾았네
                    xhr.send("test="+value.value); /// ajax data부분
                    xhr.onload = function() { 
                        if(xhr.status==200){ // success:function(data)부분 통신 성공시 200반환
                            alert(xhr.response);
                        }
                    }
            });
        });  
    
    
}
