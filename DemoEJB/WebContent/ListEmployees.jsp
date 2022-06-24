<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="user_form" action="insert" method="post">
					<div class="modal-header">
						<h3 class="modal-title">Thêm Nhân viên</h3>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>NAME</label> <input type="text" id="name" name="name"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label>EMAIL</label> <input type="email" id="email" name="email"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label>PHONE</label> <input type="tel" id="phone" name="phone"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label>Address</label> <input type="text" id="address"
								name="address" class="form-control" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Hủy">
						<button type="button" class="btn btn-success" id="btn-add">Thêm</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="editEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="update_form" action="update" method="POST">
					<div class="modal-header">
						<h4 class="modal-title">Hiệu chỉnh</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
					</div>
					<div class="modal-body">
						<input type="hidden" id="id_u" name="id" class="form-control"
							required>
						<div class="form-group">
							<label>Name</label> <input type="text" id="name_u" name="name"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" id="email_u"
								name="email" class="form-control" required>
						</div>
						<div class="form-group">
							<label>PHONE</label> <input type="tel" id="phone_u" name="phone"
								class="form-control" required>
						</div>
						<div class="form-group">
							<label>Address</label> <input type="text" id="address_u"
								name="address" class="form-control" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" value="2" name="type"> <input
							type="button" class="btn btn-default" data-dismiss="modal"
							value="Hủy">
						<button type="button" class="btn btn-info" id="update">Ghi</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="list" class="container mt-4 ">
		<div class="container">
			<h3 class="text-center">Danh sách Nhân viên</h3>
			<hr>
			<div class="container text-left">
				<a href="#addEmployeeModal" class="btn btn-success"
					data-toggle="modal"> <span>Thêm Nhân viên</span></a>
			</div>
			<br>
			<table id="datatable" class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Address</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="employee" items="${listEmployee}">

						<tr>

							<td><c:out value="${employee.getId()}" /></td>
							<td><c:out value="${employee.getName()}" /></td>
							<td><c:out value="${employee.getEmail()}" /></td>
							<td><c:out value="${employee.getPhone()}" /></td>
							<td><c:out value="${employee.getAddress()}" /></td>

							<td><a class="btn btn-danger"
								onclick="deleteEmployee( ${employee.getId()})">Xóa</a> <a
								href="#editEmployeeModal" data-toggle="modal" type="button"
								class="btn btn-warning editbtn"> Hiệu chỉnh </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>


	<script type="text/javascript">
     function deleteEmployee(id){
    	 var result = confirm('Bạn thực sự muốn xóa Nhân viên này?')
          if(result)  { $.ajax({
             url: 'http://localhost:8080/DemoEJB/delete?id='+id,
             method: 'POST',
             success: function () {
                 alert('Xóa thành công');
                 getAllEmployee();
             },
             error: function (error) {
                 alert(error);
             }
         })
     }}
     $(document).on('click','#btn-add',function(e) {
 		var data = $("#user_form").serialize();
 		$.ajax({
 			data: data,
 			type: "post",
 			url: "http://localhost:8080/DemoEJB/insert",
 			success: function(){
 			    $(".modal-backdrop").remove();
 				$("#addEmployeeModal").modal("hide");
 				$('#addEmployeeModal').on('hidden.bs.modal', function () {
 				    $(this).find('form').trigger('reset');
 				})
				getAllEmployee();
         
 			}
 		});
 	});
     
  </script>
	<script>
    $(document).on('click','#update',function(e) {
 		var data = $("#update_form").serialize();
 		$.ajax({
 			data: data,
 			type: "post",
 			url: "http://localhost:8080/DemoEJB/update",
 			success: function(){
 				$("#editEmployeeModal").removeClass("in");
 			    $(".modal-backdrop").remove();
 				$('#editEmployeeModal').modal('hide');
 				
 				alert('Hiệu chỉnh thành công'); 
 				getAllEmployee();
 			}
 		});
 	});
    $(document).ready(function () {
     $('.editbtn').on('click', function () { 
            	$tr = $(this).closest('tr');

                var data = $tr.children("td").map(function () {
                    return $(this).text();
                }).get();
                console.log(data);
                $('#id_u').val(data[0]);
                $('#name_u').val(data[1]);
                $('#email_u').val(data[2]);
                $('#phone_u').val(data[3]);
                $('#address_u').val(data[4]);
            });
        });
    </script>
	<script>
    function getAllEmployee() {
        $.ajax({
            url: "http://localhost:8080/DemoEJB/employee",
            method: "GET",
            dataType: "json",
            success: function (data) {
            	console.log(data);
            	var tableBody = $('#datatable tbody');
                tableBody.empty();
                $(data).each(function (index, element) {
                    tableBody.append('<tr><td>'+element.id+'</td><td>'+element.name+'</td><td>'+element.email+'</td><td>'+element.phone+'</td><td>'+element.address+'</td><td><a class="btn btn-danger"onclick="deleteEmployee( '+element.id+')">Xóa</a> <a href="#editEmployeeModal" data-toggle="modal" type="button"class="btn btn-warning editbtn"> Hiệu chỉnh </a></td></tr>');
                })
            },
            error: function (error) {
                alert(error);
            }
        })
    }
    </script>
</body>
</html>