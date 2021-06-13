
window.onload=function(){
        document.querySelectorAll('.updateCommnetButton2').forEach(function(item) {
            item.addEventListener('click', function() {
                    var xhr = new XMLHttpRequest(); //new로 생성
                    var value=document.getElementsByClassName(item.id+'comment')[0];
                    var bid=document.getElementById('bid').value;
                    alert(bid);
                    xhr.open('POST', item.name, true); //j쿼리 $ajax.({type,url},true가 비동기)
                    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");////이게없으면 psot전송불가 조금 찾았네
                    xhr.send('comment='+value.value+'&cid='+item.id); /// ajax data부분
                    xhr.onload = function() { 
                        if(xhr.status==200){ // success:function(data)부분 통신 성공시 200반환
                            if(xhr.response){
                                alert('댓글수정성공');
                                location.href='/auth/content?bid='+bid;            
                            }
                        }
                    }
            });
        });
        document.querySelectorAll('.deleteCommentButton').forEach(function(item) {
            item.addEventListener('click', function() {
                    var xhr = new XMLHttpRequest(); //new로 생성
                    var cid=item.id;
                    var bid=document.getElementById('bid').value;
                    alert(cid);
                    xhr.open('POST', item.name, true); //j쿼리 $ajax.({type,url},true가 비동기)
                    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");////이게없으면 psot전송불가 조금 찾았네
                    xhr.send('cid='+cid); /// ajax data부분
                    xhr.onload = function() { 
                        if(xhr.status==200){ // success:function(data)부분 통신 성공시 200반환
                            if(xhr.response){
                                alert('댓글삭제성공');
                                location.href='/auth/content?bid='+bid;            
                            }
                        }
                    }
            });
        });

      
    
    
}

