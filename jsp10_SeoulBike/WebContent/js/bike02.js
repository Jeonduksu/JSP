/**
 * 
 */

 $(function(){
	parseJson();
});

function parseJson(){
	$.getJSON("json/bike.json",function(data){
		$.ajax({
			url:"bike.do?command=second_db",
			method:"post",
			//String 형태로 서버로 넘겨주자
			data:{"obj":JSON.stringify(data)},
			success:function(){
				if (msg > 0) {
					alert("저장 성공");
					$.each(data, function(key, val) {

						if (key == "DESCRIPTION") {
							//테이블 생성
							$("table").attr("border", "1");
							//thead 생성
							//tr 생성
							//th생성해서 안에다가 val값 넣어주기
							$("thead").append(
								"<tr>" +
								"<th>" + val.ADDR_GU + "</th>" +
								"<th>" + val.CONTENT_ID + "</th>" +
								"<th><a href='bike.do?command=detail'>" + val.CONTENT_NM + "</a></th>" +
								"<th>" + val.NEW_ADDR + "</th>" +
								"<th>" + val.CRADLE_COUNT + "</th" > +
								"<th>" + val.LONGITUDE + "</th>" +
								"<th>" + val.LATITUDE + "</th>" +
								"</tr>"
							);
							//key값이 DESCRIPTION가 아니고 DATA인 경우
						} else {
							//변수에 list(배열)을 담기
							//list:배열
							//str : 배열 안에 있는 JSON데이터
							let list = val;
							for (let i = 0; i < list.length; i++) {
								let str = list[i];
								//위에꺼랑 똑같이 배열 하나 꺼내서 값 넣기
								$("tbody").append(
									"<tr>" +
									//대소문자 구분해서 주의해야한다
									"<td>" + str.addr_gu + "</td>" +
									"<td>" + str.content_id + "</td>" +
									"<td>" + str.content_nm + "</td>" +
									"<td>" + str.new_addr + "</td>" +
									"<td>" + str.cradle_count + "</td>" +
									"<td>" + str.longitude + "</td>" +
									"<td>" + str.latitude + "</td>" +
									"<input type='hidden' name='bike' value='" +
									str.addr_gu + "/" + str.content_id + "/" + str.content_nm + "/" +
									str.new_addr + "/" + str.cradle_count + "/" + str.longitude + "/" + str.latitude +
									"'>" +
									"</tr>"
								);
							}
						}

					});
					
					
					
					
				}else{
					alert("저장 실패");
				}
			},
			error:function(){
				alert("통신실패");
			}
		});
	});
}