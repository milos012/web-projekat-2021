$.noConflict()
function getDateTime(datetime){	
	var month={"01": "Januar",
            "02": "Februar",
            "03": "Mart",
            "04": "April",
            "05": "Maj",
            "06": "Jun",
            "07": "Jul",
            "08": "Avgust",
            "09": "Septembar",
            "10": "Oktobar",
            "11": "Novembar",
            "12": "Decembar"}

	
    var date = datetime.split("T")[0];
    var time = datetime.split("T")[1];

    return date.split("-")[2] + ". "+month[date.split("-")[1]] + " " + date.split("-")[0] + ". u " + time.split(":")[0] +":"+time.split(":")[1];
}

function postaviPolja(user){
	$("input[name='username']").val(user.username);
	$("input[name='password']").val(user.password);
	$("input[name='firstName']").val(user.firstName);
	$("input[name='lastName']").val(user.lastName);
	$("input[name='dateOfBirth']").val(user.dateOfBirth);
	$("input[name='gender']").val(user.gender);
	$("input[name='role']").val(user.role);
	
	if(user.role == "BUYER"){
		$("#profil-dugme1").text("Manifestations");
		$("#profil-dugme2").text("Tickets");
		$("input[name='userTypeName']").val(user.userTypeName);
		
	} else if(user.role == "ADMIN"){
		$("#profil-dugme1").text("Register new seller");
		$("#profil-dugme2").text("New manifestations");
		$("#profil-dugme3").text("Users");
		$("#profil-dugme4").text("Tickets");
	}else{
		$("#profil-dugme1").text("Add manifestation");
		$("#profil-dugme2").text("My manifestations");
		$("#profil-dugme3").text("Sold tickets");
	}
}