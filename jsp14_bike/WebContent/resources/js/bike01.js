

$(function(){
	getJsonTest();
});

function getJsonTest(){
	$.getJSON("resources/json/bike.json",function(data){	
		$.each(data,function(key,val){
			if(key=="DESCRIPTION"){
				$("table").attr("border","1"); //attr은 table 속성을 추가한다
				
				$("thead").append(	//append는 값을 추가한다
						"<tr>"+
						"<th>"+val.ADDR_GU+"</th>"+
						"<th>"+val.CONTENT_ID+"</th>"+
						"<th>"+val.CONTENT_NM+"</th>"+
						"<th>"+val.NEW_ADDR+"</th>"+
						"<th>"+val.CRADLE_COUNT+"</th>"+
						"<th>"+val.LONGITUDE+"</th>"+
						"<th>"+val.LATITUDE+"</th>"+
						"</tr>"	
				);
			}else if(key=="DATA"){
				var list = val;
				for(var i=0; i<list.length; i++){
					var str = list[i];
					$("tbody").append(
							"<tr>"+
							"<td>"+str.addr_gu+"</td>"+
							"<td>"+str.content_id+"</td>"+
							"<td>"+str.content_nm+"</td>"+
							"<td>"+str.new_addr+"</td>"+
							"<td>"+str.cradle_count+"</td>"+
							"<td>"+str.longitude+"</td>"+
							"<td>"+str.latitude+"</td>"+
							"<input type='hidden' name='bike' value='"+
							str.addr_gu+"/"+
							str.content_id+"/"+
							str.content_nm+"/"+
							str.new_addr+"/"+
							str.cradle_count+"/"+
							str.longitude+"/"+
							str.latitude+"'>"+
							"</tr>"
									//form태그를 사용할 때 name속성을 가져야 db로 가져가고 db에서 가져올 수 있기때문에 input타입 사용
					);
					
				}
			}
		});
	});
	
};