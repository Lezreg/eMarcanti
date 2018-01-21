function stringlength(inputtxt, minlength, maxlength)
{ 
	var field = inputtxt.value; 
	var mnlen = minlength;
	var mxlen = maxlength;

	if(field.length >=2)
	{ 
		alert("le nombre de caractère minimal est 3");
		return false;
		}
	
	else if(field.length == 0){
		
		alert('Your Search is Empty');
		return false;
	}
	else
		{ 
		alert('Your Search is good ');
		return true;
		}
}




