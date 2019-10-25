#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	char cadena[128]="Yo quiero bailar toda la noche, baila bailando va";
	char *ptr;
	
	ptr=strtok(cadena, " ");
	do{
		printf("%s\n",ptr);
	}while((ptr=strtok(NULL, " "))!=NULL);
		
	
	return 0;
}
