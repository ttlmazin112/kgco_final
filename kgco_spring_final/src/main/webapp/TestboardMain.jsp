
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
.item1 {
	grid-area: header;
}

.item2 {
	grid-area: menu;
}

.item3 {
	grid-area: main;
}

.item4 {
	grid-area: right;
}

.item5 {
	grid-area: footer;
}

.grid-container {
	display: grid;
	grid-template-areas: 'header header header header header header'
		'menu main main main right right'
		'footer footer footer footer footer footer';
	grid-gap: 10px;
	background-color: white;
	padding: 10px;
}

.grid-container>div {
	text-align: center;
	padding: 20px 0;
	font-size: 30px;
}

.item2 {
	background-color: #009FD3;
}
</style>
</head>
<body>

	<h1>Grid Layout</h1>



	<div class="grid-container">
		<div class="item1">Header</div>
		<div class="item2">Menu
					Right
			<ul>
				<li>1</li>
				<li>2</li>
				<li>3</li>
			</ul>
		</div>
		<div class="item3">Main</div>
		<div class="item4">

		
		</div>
		<div class="item5">Footer</div>
	</div>

</body>
</html>
