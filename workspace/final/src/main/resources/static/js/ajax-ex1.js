document.getElementById('submitBtn').addEventListener('click', function (event) {
  event.preventDefault();  // 取消 form 預設的送出
  var inputText = document.getElementById('myMessage').value;
  var dtoObject = { "text": inputText };  // js 物件
  var dtoJsonString = JSON.stringify(dtoObject);  // 將 js 物件轉換為 JSON 字串

  fetch('http://localhost:8080/my-app/messages/ajax/post', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json; charset=UTF-8'
    },
    body: dtoJsonString
  })
    .then(response => response.json())
    .then(result => {
      document.getElementById("msgForm").reset(); // 清空 input
      console.log(result);

      let msg_data = '<tbody>';
      for (let i = 0; i < result.content.length; i++) {
        console.log(result.content[i].text);
        msg_data += '<tr>'
        msg_data += '<td>' + result.content[i].text + '</td>'
        msg_data += '<td>' + result.content[i].added + '</td>'
        msg_data += '</tr>'
      }
      msg_data += '</tbody>';

      var myTable = document.getElementById("list_table_json"); // Get the table
      myTable.getElementsByTagName("tbody")[0].innerHTML = msg_data;
    })
    .catch(err => {
      console.log(err)
    });
});

//$("#submitBtn").on("click", () => {
//	var inputText = document.getElementById('myMessage').value;
//	
//	$.ajax({
//		url: "http://localhost:8080/my-app/messages/ajax/post",
//		type: "post",
//		data:JSON.stringify({"text":inputText}),
//		contentType: "application/json;charset=UTF-8",
//		async: true,
//		timeout: 5000,
//		success: function (response) {
//			
//			alert("success");
//			//let result = data.json();
//			//$("#demo").append(data);
////			console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
////			console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
////			console.log(result);
////			let msg_data = '<tbody>';
////			
////			for (let i = 0; i < result.content.length; i++) {
////        		msg_data += '<tr>'
////        		msg_data += '<td>' + result.content[i].text + '</td>'
////        		msg_data += '<td>' + result.content[i].added + '</td>'
////        		msg_data += '</tr>'
////      		}
////      		msg_data += '</tbody>';
////      		
////      		$("#list_table_json").html(msg_data);
//			
//		},
//		error: function() {
//			alert("fail");
//		}
// 	});
//});