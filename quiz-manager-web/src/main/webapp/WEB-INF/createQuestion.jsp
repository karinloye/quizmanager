<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question Edition</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<form action="/questionAction" method="post">
			<div class="form-group row">
				<label for="question" class="col-sm-2 col-form-label">Question
					Title</label>
				<div class="col-sm-10">
					<textarea type="text" class="form-control" id="question"
						placeholder="Your question here"></textarea>
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0">Options</legend>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-control" type="text" name="option1"
								id="option1" placeholder="first option">

						</div>
						<div class="form-check">
							<input class="form-control" type="text" name="option2"
								id="option1" placeholder="second option">
						</div>
						<div class="form-check">
							<input class="form-control" type="text" name="option3"
								id="option1" placeholder="third option">
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<div class="col-sm-10">
					<button type="submit" class="btn btn-primary">Validate</button>
				</div>
			</div>
	</div>


	</form>
	</div>

</body>
</html>