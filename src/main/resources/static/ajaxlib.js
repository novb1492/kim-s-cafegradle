window.onload=function(){

////////////////////////////////////////comment에 관련된 함수들20210613
        //댓글등록
        var insertcomment=document.querySelector('.insertCommentButton');
        insertcomment.addEventListener('click', function() {
            var bid=document.getElementById('bid').value;
            var url='/insertcomment'
            var data='comment='+document.getElementById('comment').value+'&bid='+bid;
            var suurl='/auth/content?bid='+bid;
            doajax(url,data,suurl);
        });
        //댓글disable
        document.querySelectorAll('.updateCommentButton').forEach(function(item) {
            item.addEventListener('click', function() {
                var id = item.id;
                document.getElementsByClassName(id+'comment')[0].disabled=false;
                document.getElementById(id+'update').disabled=false;
            });
        });
        //댓글수정
        document.querySelectorAll('.updateCommnetButton2').forEach(function(item) {
            item.addEventListener('click', function() {
                var bid=document.getElementById('bid').value;
                var url='/updatecomment';
                var data='comment='+document.getElementsByClassName(item.id.replace("update","")+'comment')[0].value+'&cid='+item.id.replace("update","");
                var sucurl='/auth/content?bid='+bid;
                doajax(url,data,sucurl);            
            });
        });
        //댓글삭제
        document.querySelectorAll('.deleteCommentButton').forEach(function(item) {
            item.addEventListener('click', function() {
                var bid=document.getElementById('bid').value;
                var url='/deletecomment';
                var data='cid='+item.id;
                var suurl='/auth/content?bid='+bid;
                doajax(url,data,suurl);              
            });
        });
///////////////////////////////////////게시글삭제
    var deletearticle=document.getElementById('deletearticle');
    deletearticle.addEventListener('click',function(){
        var url='/deletearticle'
        var data='bid='+document.getElementById('bid').value;
        var sucurl='/auth/boardlist';
        doajax(url,data,sucurl);
    })
      
/////////////////////////////////////////ajax실행 하는 곳
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
/////////////////////////////////////////////css수정하는곳
    var commnetcss=document.getElementById('commnet');
    commnetcss.addEventListener('keyup',function(){
        if(commnetcss.value.lenght>50){
            alert('d50');
        }
    })
}

