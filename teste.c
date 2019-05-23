include <stdio.h>
#include <stdlib.h>
#include <locale.h>

void calcDesconto (float media, int matricula)
{
	if (media >= 9.0)
	printf("Matrícula do aluno:\n%d \n\nValor do desconto:\n30\%%\n\n", matricula);
	else if (media >= 8.0)
	printf("Matrícula do aluno:\n%d \n\nValor do desconto:\n10\%%\n\n", matricula);
	else if (media >= 7.0)
	printf("Matrícula do aluno:\n%d \n\nValor do desconto:\n5\%%\n\n", matricula);
	else
	printf("Matrícula do aluno:\n%d \n\nValor do desconto:\n0\%%\n\n", matricula);
}

int main ()
{
	int mat, cont;
	float mediaGeral;

	
	cont = 0; //Inicialização do contador
	while (cont < 4)
	{
		printf("----------------------------------------------\n");
		printf("\n");
		printf ("Digite a matrícula:\n");
		scanf ("%d",&mat);
		printf("\n");
		printf ("Digite a média geral:\n");
		scanf ("%f",&mediaGeral);
		printf("\n");
		printf("----------------------------------------------");
		printf("\n");
		calcDesconto (mediaGeral, mat);
		cont++;
		fflush(stdin);
	}

	system("PAUSE");
	return 0; 


