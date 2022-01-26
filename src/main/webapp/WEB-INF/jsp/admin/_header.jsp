<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<link href="css/table.css" rel="stylesheet" >
<div style="background: rgba(2,186,171,0.37); height: 30px; padding: 5px; border-radius: 10px;">
    <div style="float: right; padding: 5px; text-align: right;">
        Hello <b>${sessionScope.name}</b> <a href="welcome?command=LogOut"> Log out </a>
        <br/>
    </div>
    <div style="padding: 5px;">
        <a href="admin">Main</a>
        |
        <a href="?command=GoToOrdersPage">Orders</a>
        |
        <a href="?command=GoToEmployeesPage">Employees</a>
        |
        <a href="?command=GoToCustomersPage">Customers</a>
        |
        <a href="?command=GoToCarsPage">Cars</a>
    </div>
</div>
