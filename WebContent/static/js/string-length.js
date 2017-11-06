function stringlength(inputtxt, minlength, maxlength)
{ 
var field = inputtxt.value; 
var mnlen = minlength;
var mxlen = maxlength;

if(field.length<mnlen || field.length> mxlen)
{ 
alert("le nombre de caractère minimal est 3");
return false;
}
else
{ 
alert('Your Search is good ');
return true;
}
}