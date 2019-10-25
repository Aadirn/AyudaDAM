#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	char nombres[5][128];
	int i;
	for(i=0;i<5;i++){
		printf("Dame un nombre completo: ");
		gets(nombres[i]);
	}
	for(i=0;i<5;i++){
		printf("%d. %s\n", i, nombres[i]);
	}
	return 0;
}
