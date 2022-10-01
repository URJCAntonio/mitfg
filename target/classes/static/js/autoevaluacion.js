		function getRandom(){ //Genera un número aleatorio 0 o 1
					var A=Math.random();
					if (A<0.5){
						A=0;
					}else{
						A=1;
					}
					return A;
				}
		function puertaOR (entrada1, entrada2){
			var salida;//genera la salida de una puerta OR en funcion de las dos entradas
			
			if (entrada1||entrada2){
				salida=1;
			}else{
				salida=0;
				}
			return salida;
		}
		function puertaAND (entrada1, entrada2){
			var salida;
			if (entrada1&&entrada2){
				salida=1;
			}else{
				salida=0;
				}
			return salida;
		}
		function puertaNOT(entrada){
			var salida
			if (entrada==1){
				salida=0;
			}else{
				salida=1;
			}
			return salida;
		}

		//--------------------funciones logicas
		var A_1=getRandom();
		document.getElementById("A_1").innerHTML=A_1;//escribe la variable A1 aleatoria
		document.getElementById("corr_1").value=puertaNOT(A_1);//calcula el resultado de la correccion
		
		var A_2=getRandom();
		document.getElementById("A_2").innerHTML=A_2;
		var B_2=getRandom();
		document.getElementById("B_2").innerHTML=B_2;
		document.getElementById("corr_2").value=puertaAND(A_2,B_2);
		
		var A_3=getRandom();
		document.getElementById("A_3").innerHTML=A_3;
		var B_3=getRandom();
		document.getElementById("B_3").innerHTML=B_3;
		document.getElementById("corr_3").value=puertaAND(A_3,puertaNOT(B_3));
		
		var A_4=getRandom();
		document.getElementById("A_4").innerHTML=A_4;
		var B_4=getRandom();
		document.getElementById("B_4").innerHTML=B_4;
		document.getElementById("corr_4").value=puertaOR(puertaNOT(A_4),B_4);
		
		var A_5=getRandom();
		document.getElementById("A_5").innerHTML=A_5;
		var B_5=getRandom();
		document.getElementById("B_5").innerHTML=B_5;
		document.getElementById("corr_5").value=puertaAND(A_5, puertaOR(puertaNOT(A_5),B_5));
		
		var A_6=getRandom();
		document.getElementById("A_6").innerHTML=A_6;
		var B_6=getRandom();
		document.getElementById("B_6").innerHTML=B_6;
		document.getElementById("corr_6").value=puertaNOT(puertaAND(A_6,B_6));
		
		var A_7=getRandom();
		document.getElementById("A_7").innerHTML=A_7;
		var B_7=getRandom();
		document.getElementById("B_7").innerHTML=B_7;
		document.getElementById("corr_7").value=puertaOR(puertaAND(puertaNOT(A_7),B_7),B_7);
		
		var A_8=getRandom();
		document.getElementById("A_8").innerHTML=A_8;
		var B_8=getRandom();
		document.getElementById("B_8").innerHTML=B_8;
		document.getElementById("corr_8").value=puertaOR(puertaNOT(puertaOR(A_8,B_8)),B_8);
		
		
		
		var A_9=getRandom();
		document.getElementById("A_9").innerHTML=A_9;
		var B_9=getRandom();
		document.getElementById("B_9").innerHTML=B_9;
		var C_9=getRandom();
		document.getElementById("C_9").innerHTML=C_9;
		document.getElementById("corr_9").value=puertaOR(puertaAND(C_9,A_9),B_9);
		
		
		var A_10=getRandom();
		document.getElementById("A_10").innerHTML=A_10;
		var B_10=getRandom();
		document.getElementById("B_10").innerHTML=B_10;
		var C_10=getRandom();
		document.getElementById("C_10").innerHTML=C_10;
		document.getElementById("corr_10").value=puertaOR(puertaAND(B_10,C_10),A_10);
		
		
		var A_11=getRandom();
		document.getElementById("A_11").innerHTML=A_11;
		var B_11=getRandom();
		document.getElementById("B_11").innerHTML=B_11;
		var C_11=getRandom();
		document.getElementById("C_11").innerHTML=C_11;
		document.getElementById("corr_11").value=puertaAND(puertaOR(puertaNOT(A_11),puertaNOT(C_11)),B_11);
		
		
		var A_12=getRandom();
		document.getElementById("A_12").innerHTML=A_12;
		var B_12=getRandom();
		document.getElementById("B_12").innerHTML=B_12;
		var C_12=getRandom();
		document.getElementById("C_12").innerHTML=C_12;
		document.getElementById("corr_12").value=puertaOR(puertaNOT(puertaAND(A_12,B_12)),C_12);
		
		
		
		var A_13=getRandom();
		document.getElementById("A_13").innerHTML=A_13;
		var B_13=getRandom();
		document.getElementById("B_13").innerHTML=B_13;
		var C_13=getRandom();
		document.getElementById("C_13").innerHTML=C_13;
		document.getElementById("corr_13").value=puertaOR(puertaNOT(puertaAND(A_13,C_13)),puertaAND(A_13,B_13));
		
		
		var A_14=getRandom();
		document.getElementById("A_14").innerHTML=A_14;
		var B_14=getRandom();
		document.getElementById("B_14").innerHTML=B_14;
		var C_14=getRandom();
		document.getElementById("C_14").innerHTML=C_14;
		document.getElementById("corr_14").value=puertaOR(puertaAND(B_14,C_14),puertaNOT(puertaAND(A_14,C_14)));
		
		
		var A_15=getRandom();
		document.getElementById("A_15").innerHTML=A_15;
		var B_15=getRandom();
		document.getElementById("B_15").innerHTML=B_15;
		var C_15=getRandom();
		document.getElementById("C_15").innerHTML=C_15;
		document.getElementById("corr_15").value=puertaAND(puertaNOT(puertaOR(A_15,B_15)),puertaNOT(puertaAND(A_15,C_15)));
		
		
		var A_16=getRandom();
		document.getElementById("A_16").innerHTML=A_16;
		var B_16=getRandom();
		document.getElementById("B_16").innerHTML=B_16;
		var C_16=getRandom();
		document.getElementById("C_16").innerHTML=C_16;
		document.getElementById("corr_16").value=puertaAND(puertaNOT(puertaOR(A_16,C_16)),B_16);
		
		
		//------------puertas logicas
		
		var A_25=getRandom();
		document.getElementById("A_25").innerHTML=A_25;
		var B_25=getRandom();
		document.getElementById("B_25").innerHTML=B_25;
		document.getElementById("corr_25").value=puertaOR(A_25,B_25);
		
		var A_26=getRandom();
		document.getElementById("A_26").innerHTML=A_26;
		var B_26=getRandom();
		document.getElementById("B_26").innerHTML=B_26;
		document.getElementById("corr_26").value=puertaNOT(puertaAND(A_26,B_26));
		
		var A_27=getRandom();
		document.getElementById("A_27").innerHTML=A_27;
		document.getElementById("corr_27").value=puertaNOT(A_27);
		
		
		var A_28=getRandom();
		document.getElementById("A_28").innerHTML=A_28;
		var B_28=getRandom();
		document.getElementById("B_28").innerHTML=B_28;
		document.getElementById("corr_28").value=puertaNOT(puertaOR(A_28,B_28));
		
		var A_29=getRandom();
		document.getElementById("A_29").innerHTML=A_29;
		var B_29=getRandom();
		document.getElementById("B_29").innerHTML=B_29;
		document.getElementById("corr_29").value=puertaAND(A_29,B_29);
		
		//--------------------circuitos logicos
		
		var A_30=getRandom();
		document.getElementById("A_30").innerHTML=A_30;
		var B_30=getRandom();
		document.getElementById("B_30").innerHTML=B_30;
		var C_30=getRandom();
		document.getElementById("C_30").innerHTML=C_30;
		document.getElementById("corr_30").value=puertaAND(puertaOR(A_30,B_30),C_30);
		
		var A_31=getRandom();
		document.getElementById("A_31").innerHTML=A_31;
		var B_31=getRandom();
		document.getElementById("B_31").innerHTML=B_31;
		document.getElementById("corr_31").value=puertaNOT(puertaAND(puertaOR(A_31,B_31),puertaNOT(B_31)));
		
		var A_32=getRandom();
		document.getElementById("A_32").innerHTML=A_32;
		var B_32=getRandom();
		document.getElementById("B_32").innerHTML=B_32;
		var C_32=getRandom();
		document.getElementById("C_32").innerHTML=C_32;
		document.getElementById("corr_D_32").value=puertaAND(A_32,B_32);
		document.getElementById("corr_E_32").value=puertaNOT(B_32);
		document.getElementById("corr_F_32").value=puertaNOT(puertaAND(puertaAND(A_32,B_32),puertaNOT(B_32)));
		document.getElementById("corr_G_32").value=puertaNOT(puertaOR(puertaAND(A_32,B_32),puertaNOT(puertaAND(puertaAND(A_32,B_32),puertaNOT(B_32)))));
		document.getElementById("corr_H_32").value=puertaOR(puertaNOT(B_32),C_32);
		document.getElementById("corr_S_32").value=puertaAND(puertaNOT(puertaOR(puertaAND(A_32,B_32),puertaNOT(puertaAND(puertaAND(A_32,B_32),puertaNOT(B_32))))),puertaOR(puertaNOT(B_32),C_32));
		
		
		var A_33=getRandom();
		document.getElementById("A_33").innerHTML=A_33;
		var B_33=getRandom();
		document.getElementById("B_33").innerHTML=B_33;
		var C_33=getRandom();
		document.getElementById("C_33").innerHTML=C_33;
		document.getElementById("corr_D_33").value=puertaNOT(B_33);
		document.getElementById("corr_E_33").value=puertaAND(A_33,puertaNOT(B_33));
		document.getElementById("corr_F_33").value=puertaNOT(puertaAND(A_33,puertaNOT(B_33)));
		document.getElementById("corr_G_33").value=puertaNOT(B_33);
		document.getElementById("corr_H_33").value=puertaAND(puertaNOT(B_33),C_33);
		document.getElementById("corr_S_33").value=puertaOR(puertaNOT(puertaAND(A_33,puertaNOT(B_33))),puertaAND(puertaNOT(B_33),C_33));
		
		

		
		function corregirtodo(corr_1,corr_2,corr_3,corr_4,corr_5,corr_6,corr_7,corr_8,corr_9,corr_10,corr_11,corr_12,corr_13,corr_14,corr_15,corr_16,corr_17,corr_18,corr_19,corr_20,corr_21,corr_22,corr_23,corr_24,corr_25,corr_26,corr_27,corr_28,corr_29,corr_30,corr_31,corr_D_32,corr_E_32,corr_F_32,corr_G_32,corr_H_32,corr_S_32,corr_D_33,corr_E_33,corr_F_33,corr_G_33,corr_H_33,corr_S_33,S_1, S_2, S_3, S_4, S_5, S_6, S_7, S_8, S_9, S_10, S_11, S_12, S_13, S_14, S_15, S_16, S_17, S_18, S_19, S_20, S_21, S_22, S_23, S_24, S_25, S_26, S_27, S_28, S_29, S_30, S_31, D_32, E_32, F_32, G_32, H_32, S_32, D_33, E_33, F_33, G_33, H_33, S_33, P_1, P_2, P_3, P_4, P_5, P_6, P_7, P_8, P_9, P_10, P_11, P_12, P_13, P_14, P_15, P_16, P_17, P_18, P_19, P_20, P_21, P_22, P_23, P_24, P_25, P_26, P_27, P_28, P_29, P_30, P_31, P_D_32, P_E_32, P_F_32, P_G_32, P_H_32, P_S_32, P_D_33, P_E_33, P_F_33, P_G_33, P_H_33, P_S_33, textofinal){
		var correctas =0;
			function corregir1(corr_1,S_1,P_1){
				var correctas=0;
				if (S_1==""){
					document.getElementById(P_1).style.display="block";
					document.getElementById(P_1).style.color="red";
					document.getElementById(P_1).innerHTML="¡No has contestado!";
					
				}else if ((S_1!=0)&&(S_1!=1)){//si el valor de la caja de texto es distinto de 0 o 1 o no pone nada ¡solo valen 0 o 1!
					document.getElementById(P_1).style.display="block";
					document.getElementById(P_1).style.color="red";
					document.getElementById(P_1).innerHTML="¡Sólo valen 0 y 1!";
					
				}else if(corr_1==S_1){//si el valor de la caja de texto y el de la funcion puerta OR coincide ¡Correcto!
					document.getElementById(P_1).style.display="block";
					document.getElementById(P_1).style.color="green";
					document.getElementById(P_1).innerHTML="¡Correcto!";
					correctas=1;
				}else{//si no incorrecto
					document.getElementById(P_1).style.display="block";
					document.getElementById(P_1).style.color="red";
					document.getElementById(P_1).innerHTML="Incorrecto";	
				}
				return correctas;
			}
		
		
			
			correctas = correctas + corregir1(corr_1,S_1, P_1);
			correctas = correctas + corregir1(corr_2,S_2, P_2);
			correctas = correctas + corregir1(corr_3,S_3, P_3);
			correctas = correctas + corregir1(corr_4,S_4, P_4);
			correctas = correctas + corregir1(corr_5,S_5, P_5);
			correctas = correctas + corregir1(corr_6,S_6, P_6);
			correctas = correctas + corregir1(corr_7,S_7, P_7);
			correctas = correctas + corregir1(corr_8,S_8, P_8);
			correctas = correctas + corregir1(corr_9,S_9, P_9);
			correctas = correctas + corregir1(corr_10,S_10, P_10);
			correctas = correctas + corregir1(corr_11,S_11, P_11);
			correctas = correctas + corregir1(corr_12,S_12, P_12);
			correctas = correctas + corregir1(corr_13,S_13, P_13);
			correctas = correctas + corregir1(corr_14,S_14, P_14);
			correctas = correctas + corregir1(corr_15,S_15, P_15);
			correctas = correctas + corregir1(corr_16,S_16, P_16);
			correctas = correctas + corregir1(corr_17,S_17, P_17);
			correctas = correctas + corregir1(corr_18,S_18, P_18);
			correctas = correctas + corregir1(corr_19,S_19, P_19);
			correctas = correctas + corregir1(corr_20,S_20, P_20);
			correctas = correctas + corregir1(corr_21,S_21, P_21);
			correctas = correctas + corregir1(corr_22,S_22, P_22);
			correctas = correctas + corregir1(corr_23,S_23, P_23);
			correctas = correctas + corregir1(corr_24,S_24, P_24);
			correctas = correctas + corregir1(corr_25,S_25, P_25);
			correctas = correctas + corregir1(corr_26,S_26, P_26);
			correctas = correctas + corregir1(corr_27,S_27, P_27);
			correctas = correctas + corregir1(corr_28,S_28, P_28);
			correctas = correctas + corregir1(corr_29,S_29, P_29);
			correctas = correctas + corregir1(corr_30,S_30, P_30);
			correctas = correctas + corregir1(corr_31,S_31, P_31);
			correctas = correctas + corregir1(corr_D_32,D_32, P_D_32);
			correctas = correctas + corregir1(corr_E_32,E_32, P_E_32);
			correctas = correctas + corregir1(corr_F_32,F_32, P_F_32);
			correctas = correctas + corregir1(corr_G_32,G_32, P_G_32);
			correctas = correctas + corregir1(corr_H_32,H_32, P_H_32);
			correctas = correctas + corregir1(corr_S_32,S_32, P_S_32);
			correctas = correctas + corregir1(corr_D_33,D_33, P_D_33);
			correctas = correctas + corregir1(corr_E_33,E_33, P_E_33);
			correctas = correctas + corregir1(corr_F_33,F_33, P_F_33);
			correctas = correctas + corregir1(corr_G_33,G_33, P_G_33);
			correctas = correctas + corregir1(corr_H_33,H_33, P_H_33);
			correctas = correctas + corregir1(corr_S_33,S_33, P_S_33);
			
		document.getElementById(textofinal).innerHTML="¡Has acertado "+correctas+" respuestas de un total de 43!";
			
			
			
		
		}
