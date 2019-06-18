<!-- 소켓통신 -->

function openChat() {
	window.open("/openChat", "", "width=430, height=450");
}



<!-- 차트 -->

	google.charts.load('current', {
		packages : [ 'corechart' ]
	});

	$(document).ready(function() {
		google.charts.setOnLoadCallback(function() {
			$.ajax({
				url : '/member/pieChart',
				success : function(result) {
					console.log('result :' + result);
					pieChart(result);
				}
			});

		});
	});

	function pieChart(arrayList) {
		console.log('arrayList' + arrayList);
		var dataTable = google.visualization.arrayToDataTable(arrayList);

		var options = {
			title : '직원 근무 현황',
			is3D : true,
			backgroundColor : "transparent",
			titleTextStyle : {
				color : 'black',
				fontSize : 20,
				fontName : "Times New Roman"
			},
			pieSliceTextStyle : {
				color : 'black'
			},
			legendTextStyle : {
				color : 'black',
				fontName : "Times New Roman",
				fontSize : 20

			}
		};
		var objDiv = document.getElementById('pie_chart_div');
		var chart = new google.visualization.PieChart(objDiv);
		chart.draw(dataTable, options);
	}

<!-- 회원정보수정 -->

	function selfEid() {
		window.open("/selfSchedule", "", "width=650, height=300");
	}

//	function userUpdate(epassword) {
//		var param = $('#modi').serialize();
//		console.log('param :' + param);
//
//	
//		$.ajax({
//			url : '/modifyPwCheck',
//			method : 'POST',
//			data :{
//				epassword : epassword
//			},
//			beforeSend : function(xhr) {
//				xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
//			},
//			success : function (result) {
//				console.log('result :'+result);
//				
//				
//				if(result == 0){
//					alert('비밀번호가 틀립니다.');
//				}else{
//					if(opener != null){
//						var upForm = document.getElementById("tableList");
//						var upSaveBtn = document.getElementById("saveBtn");
//						var modiForm = document.getElementById("modi");	
//						modiForm.style.display = 'none';
//						upForm.style.display = 'block';
//						upSaveBtn.style.display ='block';
//					}
//				}
//				
//			}
//		
//		});
//	
//		window.close();
//			
//	}

	function windowOpenPwCheck() {
		window.open("/modifyPwCheck1", "", "width=650, height=300");
	}


	

	function showDocument(){
		var select = $('select[name=confirmSelect]').val();
		var id = $('input[name=confirmId]').val();
		alert(select);
		alert(id);
	 	window.open('/confirm/document?select=' + select
				+ '&id=' + id, '', 'width=500,height=500,location=no, resizable=no'); 
	}
	
