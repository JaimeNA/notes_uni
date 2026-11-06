# Unidad 8: What we actually know about software development

Es una ciencia, un arte, o algo mas? En la charla arranca con: Ls quimioterapia, es 
efectiva contra el cancer? Con nuestro limitado conocimiento de la medicina, pensamos 
que a veces si, pero es lo mejor que tenemos. 

Entonces, nos podemos preguntar: Scum redice el desvio del  proyecto respecto de 
metodologias como cascada? Depende si lo haces bien o lo haces mal, del proyecto, 
pero en general, scrum solo reduce el desvio. 

En ingenieria de software, el autor Martin Fowler dijo dos afirmaciones que se 
publicaron en un journal academino. No cito ninguna fuente, no es nada mas que una 
opinion, que puede ser cierta. Algo asi se tomo como cierto sin ningun tipo de 
fundamento, mientras que en el resto de las industrias se necesita de estudios y 
evidencias. 

Tenemos un estandar de pruebas muy bajo, deberiamos saber cuando admitir que no 
sabemos algo. Habria que ser mas profesionales y no simplemente creer en lo que dice 
el mas experimentado. Estuvo mejorando en algunas areas en los ultimos tiempso. 

Lo de 10x engineer sale de un paper, que comparo cuanto tardaban algunos desarrolladores 
y los mejores son 10 veces mejores. Sin embargo, el paper en si es muy malo pues salio 
en la decada de los 60s, cuando no existia el ingeniero de software, y solo participaron 
30 personas. 

## Cosas que de verdad sabemos 

Demostradas y corroboradas.

- Las estimaciones deben hacerse sin contacto con el stakeholder(sin contaminar)
- Independientemente del lenguaje, un dev produce una cantidad constante de errores 
  o bugs por determinada cantidad de lineas. Pero hay que tener en cuenta que 10 lineas 
  de Java rinden mucho mas que 10 lineas de ASM
- La mayoria de los errores de software se produce en etapas tempranas
- Por cada incremento de 25% en la complejidad del problema, la complejidad de la 
  solucion se duplica
- Las dos mayores causas de fallas en proyectos son una pobre estimacion y 
  requerimientos inestables
- Si hay que reescribir mas del 20-25% de un componente, es mejor reescribirlo de cero 
- Una lectura rigurosa del codigo puede remover entre 60 y 90% de los errores antes 
  de la primer ejecucion. Es mucho mas efectivo que testearlo a mano o escribir tests 
  unitarios en costo/beneficio

