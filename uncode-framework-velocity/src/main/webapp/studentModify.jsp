<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="modifyForm">
	<input name="id" value="${student.id }" type="hidden" />
	<label>Name</label> : <input name="name" value="${student.name }" /><br/>
	<label>Age</label> : <input name="age" value="${student.age }" /><br/>
	<label>Height</label> : <input name="height" value="${student.height }" /><br/>
	<label>Weight</label> : <input name="weight" value="${student.weight }" /><br/>
</form>