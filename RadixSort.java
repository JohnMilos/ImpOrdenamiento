// Radix sort Java implementation
import java.util.*;

public class RadixSort {
    private DoublyLinkedList<Driver> theArray;          // ref to array theArray
    private int nElems;               // number of data items
    private int columna = 0;
	private int cambios =0;
	private int comp =0;
	private long tiempoEjecucion = 0;

    //-----------------------------------------------------------
    public RadixSort(DoublyLinkedList<Driver> list)   {
        theArray = list;     // create array
        nElems = list.GetSize();
    }
	    //Declara La Columna por la cual deseas ordenar
		public void SetColumna(int col)
		{
		   columna = col;
		}

		public int GetCambios()
		{
			 return cambios;
		}
	 
		public long GetTiempo()
		{
			 return tiempoEjecucion;
		}
	 
		public int GetComparaciones()
		{
			 return comp;
		}
    
    //-----------------------------------------------------------
    public void insert(Driver value){
		theArray.insertFirst(value);     // insert it
		  nElems++;                      // increment size
		}

	public void display() {
		for(int j=0; j<nElems; j++)    // for each element,
			System.out.print(theArray.BuscarPorPosicion(j) + " ");  // display it
			System.out.println("");
		}

	// A utility function to get maximum value in arr[]
	private double getMax(){
		double mx = Double.parseDouble(theArray.BuscarPorPosicion(0).GetDato(columna));
		for (int i = 1; i < nElems; i++)
		{
			double n = Double.parseDouble(theArray.BuscarPorPosicion(i).GetDato(columna));
			comp++;
			if ( n > mx)
				mx = n;
		}

		return mx;
	}

	// A function to do counting sort of arr[] according to
	// the digit represented by exp.
	public void countSort(int exp, int tipo){
		DoublyLinkedList<Driver> out = new DoublyLinkedList<Driver>();
		int i;
		for(i=0;i< nElems;i++)
		{
			out.insertLast(null);
		}
		long[] count = new long[10];
		Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < nElems; i++)
		{
			double num = Double.parseDouble(theArray.BuscarPorPosicion(i).GetDato(columna));
			count[(int) ((num / exp) % 10)]++;
		}
		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			{
				cambios++;
				count[i] += count[i - 1];
			}
			

		// Build the output array
		for (i = nElems - 1; i >= 0; i--) {
			Driver Dri = theArray.BuscarPorPosicion(i);
			double num = Double.parseDouble(Dri.GetDato(columna));
			out.CambiarPorPosicion((int) (count[(int) ((num / exp) % 10)] - 1), Dri);
			count[(int) ((num / exp) % 10)]--;
		}
		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to current digit
		for(int x=0;x<out.GetSize();x++)
		{
			theArray.CambiarPorPosicion(x, out.BuscarPorPosicion(x));
		}

		
	}

	//Recibe la lista de nodos a ordenar y Un entero del 2 al 3 que siginifica el tipo de ordenamiento que deseas
    //2-Del Menor al Mayor
    //3-Del Mayor al Menor
    //(Las opciones 2 y 3 solo pueden ser usadas para valores unicamente numericos)
	// The main function to that sorts arr[] of size n using
	// Radix Sort
	public void sort(int tipo){
		// Find the maximum number to know number of digits
		if(tipo == 1|| tipo == 0)
		{
			System.out.println("No se puede hacer Ordenamiento Alfabetico para el RadixSort");
			return;
		}
		try
		{
			Double.parseDouble(theArray.BuscarPorPosicion(2).GetDato(columna));
		}catch(Exception e)
		{
			System.out.println("Tipo de Dato no valido- Radix");
			return;
		}

		long startTime = System.currentTimeMillis();
		double m = getMax();

		// Do counting sort for every digit. Note that
		// instead of passing digit number, exp is passed.
		// exp is 10^i where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(exp, tipo);

		long endTime = System.currentTimeMillis();
		tiempoEjecucion = endTime - startTime;

		if(tipo == 3)
		{
			DoublyLinkedList<Driver> out = new DoublyLinkedList<Driver>();
			for(int x=0;x<theArray.GetSize();x++)
			{
				out.insertLast(theArray.BuscarPorPosicion(x));
			}
			for(int x=0;x<out.GetSize();x++)
			{
				theArray.CambiarPorPosicion(x, out.BuscarPorPosicion((out.GetSize() - 1) - x));
			}
		}
		

		
	}
}
/* This code is contributed by Devesh Agrawal */

