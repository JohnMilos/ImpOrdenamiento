public class QuickSort{
    private DoublyLinkedList<Driver> theArray;          // ref to array theArray
    private int nElems;               // number of data items
    private int columna = 0;
    private int cambios =0;
    private int comp =0;
    private long tiempoEjecucion = 0;

    public QuickSort(DoublyLinkedList<Driver> list)   {
      theArray = list;      // create array
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

    public void insert(Driver value){
    theArray.insertFirst(value);     // insert it
      nElems++;                      // increment size
    }

    public void display() {
      for(int j=0; j<nElems; j++)    // for each element,
         System.out.print(theArray.BuscarPorPosicion(j) + " ");  // display it
      System.out.println("");
    }

    void swap(int i, int j){
        Driver temp = theArray.BuscarPorPosicion(i);
        theArray.CambiarPorPosicion(i, theArray.BuscarPorPosicion(j));
        theArray.CambiarPorPosicion(j, temp);
        cambios++;
    }
    
    /* toma el ultimo elemento como pivote, 
    coloca el pivote en su posicion correcta del arreglo ordenado,
    coloca todos los valores mas pequeños (menores a los pivotes)
    a la izquierda del pivote y coloca todos los valores mas grandes 
    (mayores a los pivotes) a la derecha del pivote 
    */
    int partition(int low, int high, int tipo){
        
        Driver pivot = theArray.BuscarPorPosicion(high);
        
        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);
    
        for(int j = low; j <= high - 1; j++){
            // If current element is smaller
            // than the pivot
            comp++;
            if (condicion(tipo,  theArray.BuscarPorPosicion(j).GetDato(columna),pivot.GetDato(columna))){
                // Increment index of
                // smaller element
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    private boolean condicion(int tipo, String low, String high)
    {
        switch(tipo)
        {
            case 1:
                return (low.compareTo(high) > 0);
            case 0:
                return (low.compareTo(high) < 0);
            case 3:
                return (Double.parseDouble(low) > Double.parseDouble(high));
            case 2:
                return(Double.parseDouble(low) < Double.parseDouble(high));
            default:
                return false;
        }
    }
    
    /* The main function that implements QuickSort
            arr[] --> Array to be sorted,
            low --> Starting index,
            high --> Ending index
    */
    void StartSort(int low, int high, int tipo)
    {
        comp = 0;
        cambios = 0;
        if(tipo >1)
        {
            try
            {
                Double.parseDouble(theArray.BuscarPorPosicion(2).GetDato(columna));
            }catch(Exception e)
            {
                System.out.println("Tipo de Dato no valido- Quick");
                return;
            }
        }
        long startTime = System.currentTimeMillis();

        recQSort(low, high, tipo);

        long endTime = System.currentTimeMillis();
       tiempoEjecucion = endTime - startTime;


    }
    private void recQSort(int low, int high, int tipo){
        comp++;
        if (low < high){
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(low, high, tipo);
    
            // Separately sort elements before
            // partition and after partition
            recQSort(low, pi - 1, tipo);
            recQSort(pi + 1, high, tipo);
        }

    }
}