#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) 
{
	//Declaramos variables de todos los tipos de C
	char c;
	short s;
	int i;
	long l;
	float f;
	double d;
	
	//Asignamos valores a las variables
	c = 'X';
	s = 23;
	i = 45676;
	l = 746368924;
	f = 23.56879;
	d = 56765.987654321;
	
	//Mostramos las variables
	printf("El char c vale %c\n", c);
	printf("El short s vale %hd\n", s);
	printf("El int i vale %d\n", i);
	printf("El long l vale %ld\n", l);
	printf("El float f vale %f\n", f);
	printf("El double d vale %f\n", d);
	
		
	system("pause");	
	return 0;
}
