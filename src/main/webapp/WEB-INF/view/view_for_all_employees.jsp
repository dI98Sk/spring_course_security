<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>

<h3>Information for all Employees</h3>

<br><br>

<security:authorize access="hasRole('hr')">
<input type="button" value="Salary" onclick="window.location.href = 'hr_info'">
    Only for HR Staff
</security:authorize>

<br><br>
<security:authorize access="hasRole('manager')">
<input type="button" value="Performance" onclick="window.location.href = 'manager_info'">
Only for HR Managers
</security:authorize>



</body>
</html>