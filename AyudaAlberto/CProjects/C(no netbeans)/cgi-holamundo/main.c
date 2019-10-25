#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char *argv[]) {
	char html1[1024]="<!DOCTYPE html><html><head><title>CGI-Hola Mundo</title></head><body><h1>";
	char html2[1024]="</h1></body></html>";
	printf("Content-type: text/html\n");
	printf("\n");
	printf("%s%s%s\n",html1,getenv("QUERY_STRING"),html2);
	int i,j,k;
	int longitud = strlen(getenv("QUERY_STRING"));
	for(i=0; i < longitud;i++){
		if(getenv("QUERY_STRING")==38){
			getenv("QUERY_STRING")=" ";
			printf("\n");
		}else{
			printf("%c",getenv("QUERY_STRING"));
		}
	}
	char tablita [4][128];
	while(getenv("QUERY_STRING")!='&'){
		tablita[0][i]=getenv("QUERY_STRING");
		
	}
	return 0;
}
