#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	
	char c = 'H';	//Ocupa 1 byte en memoria
	short s = 22;	//Ocupa 2 bytes memoria
	int i = 1200;	//Ocupa 4 bytes memoria
	long l = 100000;	//Ocupa 8 bytes memoria
	float f = 23.33;	//Ocupa 4 bytes memoria
	double d = 3.14159;	//Ocupa 8 bytes memoria
	
	char* pc = &c;
	short* ps = &s;
	int* pi = &i;
	float* pf =&f;
	double* pd = &d;
	long* pl = &l;
	
	
	printf("La variable c vale %c, esta en la direccion %ld y ocupa %d bytes\n",c,&c,sizeof(c));
	printf("La variable s vale %hd, esta en la direccion %ld y ocupa %d bytes\n",s,&s,sizeof(s));
	printf("La variable i vale %d, esta en la direccion %ld y ocupa %d bytes\n",i,&i,sizeof(i));
	printf("La variable l vale %ld, esta en la direccion %ld y ocupa %d bytes\n",l,&l,sizeof(l));
	printf("La variable f vale %.4f, esta en la direccion %ld y ocupa %d bytes\n",f,&f,sizeof(f));
	printf("La variable d vale %.8f, esta en la direccion %ld y ocupa %d bytes\n",d,&d,sizeof(d));
	
	return 0;
}
