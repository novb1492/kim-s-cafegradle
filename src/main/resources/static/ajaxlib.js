window.onload=function(){
        var insertcomment=document.querySelector('.insertCommentButton');
        insertcomment.addEventListener('click', function() {
            var value=document.getElementById('comment');
            var bid=document.getElementById('bid').value;
            doajax(insertcomment.name,'comment='+value.value+'&bid='+bid,'/auth/content?bid='+bid);
        });
        //댓글등록
        document.querySelectorAll('.updateCommnetButton2').forEach(function(item) {
            item.addEventListener('click', function() {
                var value=document.getElementsByClassName(item.id.replace("update","")+'comment')[0];
                var bid=document.getElementById('bid').value;
                doajax(item.name,'comment='+value.value+'&cid='+item.id.replace("update",""),'/auth/content?bid='+bid);            
            });
        });
        //댓글수정
        document.querySelectorAll('.deleteCommentButton').forEach(function(item) {
            item.addEventListener('click', function() {
                var cid=item.id;
                var bid=document.getElementById('bid').value;
                doajax(item.name,'cid='+cid,location.href='/auth/content?bid='+bid);              
            });
        });
        //댓글삭제
        document.querySelectorAll('.updateCommentButton').forEach(function(item) {
            item.addEventListener('click', function() {
                var id = item.id;
                var commentinput=document.getElementsByClassName(id+'comment')[0];
                var update2button=document.getElementById(id+'update');
                commentinput.disabled = false;
                update2button.disabled=false;
            });
        });
////////////////////////////////////////comment에 관련된 함수들20210613

    var deletearticle=document.getElementById('deletearticle');
    deletearticle.addEventListener('click',function(){
        
    })
      
/////////////////////////////////////////ajax
function doajax(url,data,sucsessurl){
    var xhr = new XMLHttpRequest(); //new로 생성
    xhr.open('POST', url, true); //j쿼리 $ajax.({type,url},true가 비동기)
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");////이게없으면 psot전송불가 조금 찾았네
    xhr.send(data); /// ajax data부분
    xhr.onload = function() { 
        if(xhr.status==200){ // success:function(data)부분 통신 성공시 200반환
            if(xhr.response){
                alert('댓글등록성공');
                location.href=sucsessurl;            
            }
        }
    }
    
}
}

