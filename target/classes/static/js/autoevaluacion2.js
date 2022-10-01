function getRandom(){ //Genera un número aleatorio 0 o 1
			var A=Math.random();
			if (A<0.5){
				A=0;
			}else{
				A=1;
			}
			return A;
		}

var A1=getRandom();
document.getElementById("A1").innerHTML=A1;
var B1=getRandom();
document.getElementById("B1").innerHTML=B1;
var S1=document.preg1.S1.value;


<script type="text/javascript" src="js\autoevaluacion.js"> </script>
/*
//preg1
document.getElementById("A1").innerHTML = ;

function puertaor (A,B){
	respuesta=document.preg1.S1.value;
	if (respuesta!=1||respuesta!=0){
		document.getElementById("parrafopreg1").style.color="red";
		document.getElementById("parrafopreg1").innerHTML"¡Sólo valen 0 y 1!";
	}else if(respuesta==(A||B)){
		document.getElementById("parrafopreg1").style.color="green";
		document.getElementById("parrafopreg1").innerHTML"¡Correcto!";
	}else{
		document.getElementById("parrafopreg1").style.color="red";
		document.getElementById("parrafopreg1").innerHTML"Incorrecto";
	}
}
		
		function getRandom(){
			var A=Math.random();
			if (A<0.5){
				A=0;
			}else{
				A=1;
			}
			return A;
		}ç
		<script> 
		function getRandom(){ //Genera un número aleatorio 0 o 1
			var A=Math.random();
			if (A<0.5){
				A=0;
			}else{
				A=1;
			}
			return A;
		}
		
		</script>
		
		<script>
							var A1=getRandom();
							document.getElementById("A1").innerHTML=A1;
							document.getElementById("A1").value=A1;
							var B1=getRandom();
							document.getElementById("B1").innerHTML=B1;
							document.getElementById("B1").value=B1;
							var S1=document.preg1.S1.value;
							function correccion (A1,B1,S1)
							if (S1!=0||S1!=1){
								document.getElementById("correccion").style.color="red";
								document.getElementById("correccion").innerHTML"¡Sólo valen 0 y 1!";
							}else if(S1==(A1||B1)){
								document.getElementById("correccion").style.color="green";
								document.getElementById("correccion").innerHTML"¡Correcto!";
							}else{
								document.getElementById("correccion").style.color="red";
								document.getElementById("correccion").innerHTML"Incorrecto";
							}

						
							</script>
		function correccion (A1,B1,S1)
							if (S1!=0||S1!=1){
								document.getElementById("correccion").style.color="red";
								document.getElementById("correccion").innerHTML"¡Sólo valen 0 y 1!";
							}else if(S1==(A1||B1)){
								document.getElementById("correccion").style.color="green";
								document.getElementById("correccion").innerHTML"¡Correcto!";
							}else{
								document.getElementById("correccion").style.color="red";
								document.getElementById("correccion").innerHTML"Incorrecto";
							}
							
							*/