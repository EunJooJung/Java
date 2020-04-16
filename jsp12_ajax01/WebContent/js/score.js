

function load() {
   var url = getParameterValues();
   httpRequest = new XMLHttpRequest();		//서버와 통신 (비동기 통신)
   httpRequest.onreadystatechange=callback;	//readystate가 change되는 이벤트 발생할 때 //처리할 함수 call하면 bakc할거야
   httpRequest.open("GET",url,true);		// 여기서 서블릿으로 감 true: 비동기 / false : 동기
   httpRequest.send();						// "GET" : send() / "POST" : send(String)
}

/*
 * XMLHttpRequest : 서버와 통신을 도와주는 객체 http를 통한 데이터 송수신 지원.
 * 					(javascript object)
 * 
 * readState
 * 0 : uninitialized - 실행(load)되지 않음
 * 1 : loading - 로드 중
 * 2 : loaded - 로드 됨
 * 3 : interactive - 통신 됨
 * 4 : complete - 통신 완료
 * 
 * status
 *  200 : 통신 성공
 *  400 : bad request 잘못요청
 *  401 : unauthorized 인증안됨
 *  403 : forbidden 권한을 잘못줬다
 *  404 : not found
 *  500 : internal server error
 * 
 * 
 * encodeURIComponent() : 안에 있는 모든 문자를 인코딩
 * encodeURI() : 주소줄에 사용되는 특스문자는 제외하고 인코딩
 * 
 * JSON.parse() : String을 json으로 바꿔주는 애(parsing해주는ㅇ ㅐ?)
 * 
 * 
 * */


function callback(){
   alert("readyState : " + httpRequest.readyState);
   if(httpRequest.readyState==4) {
      alert("status : " + httpRequest.status);
      
      if(httpRequest.status==200) {
         var obj = JSON.parse(httpRequest.responseText);
         document.getElementById("result").innerHTML=decodeURIComponent(obj.name)+"님의 총점은 "+obj.sum+"이고, 평균은 "+obj.avg+"입니다.";
      } else {
         alert("통신 실패");
      }
   }
}

function getParameterValues() {
   var name = "name="+encodeURIComponent(document.getElementById("name").value);
   var kor = "kor="+document.getElementById("kor").value;
   var eng = "eng="+document.getElementById("eng").value;
   var math = "math="+document.getElementById("math").value;

   var url = "score.cal?"+name+"&"+kor+"&"+eng+"&"+math;
   console.log(url);
   
   return url;
}