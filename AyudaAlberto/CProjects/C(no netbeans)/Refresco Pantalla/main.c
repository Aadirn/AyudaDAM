#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	long entrada, ahora;
	int i;
	int posAst=0;
	int sentido;//Con 0 inica atrás y con 1, adelante
	
	char linea [11] = "*_________";
	
	srand(time(NULL));
	
	//imprimimos la linea antes del for
	printf("%s\n",linea);
	
	do{
	
		//generar un sentido aleatorio y guardarlo
		sentido=generaSentido();
		//en funcion del sentido mover el asterisco
		if(posAst<9&&sentido==1){
			linea[posAst+1]='*';
			linea[posAst]= '_';
			posAst++;
		}else if(posAst>0&&sentido==0){
			//mover hacia atras
			linea[posAst-1]='*';
			linea[posAst]= '_';
			posAst--;
		}else{
			//no hacer nada
		}
		//borramos la pantalla anterior
		system("cls");
		//imprimimos la linea
		printf("%s",linea);
		
		//esperamos 1 segundo
		entrada = time(NULL);
		do{
			ahora=time(NULL);
		}while(ahora<=entrada);
	}while(TRUE);
	
	system("pause");
	return 0;
}
int generaSentido(){
	
	return (rand()%2);
}
