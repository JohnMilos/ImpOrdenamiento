public class MergeSort{
   private DoublyLinkedList<Driver> theArray;          // ref to array theArray
   private int nElems;               // number of data items
   private int columna = 0;
   private int cambios =0;
   private int comp =0;
   private long tiempoEjecucion = 0;

   public MergeSort(DoublyLinkedList<Driver> list)   {
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

   public void mergeSort(int tipo) {
      if(tipo < 0 || tipo > 3)
      {
          System.out.println("Tipo de ordenamiento no valido- Merge");
          return;
      }
      if(tipo > 1)
      {
          try
          {
              Double.parseDouble(theArray.BuscarPorPosicion(0).GetDato(columna));
          }catch(Exception e)
          {
              System.out.println("Tipo de Dato no concordante con el tipo ordenamiento- Merge");
              return;
          }
         }

      long startTime = System.currentTimeMillis();
      comp = 0;
      cambios = 0;

      Driver[] workSpace = new Driver[nElems];
      recMergeSort(workSpace, 0, nElems-1,tipo);

      long endTime = System.currentTimeMillis();
      tiempoEjecucion = endTime - startTime;
   }

   private void recMergeSort(Driver[] workSpace, int lowerBound, int upperBound, int tipo){
      comp++;
      if(lowerBound == upperBound)            // if range is 1,
         return;                              // no use sorting
      else {                                    
         int mid = (lowerBound+upperBound) / 2; // find midpoint        
         recMergeSort(workSpace, lowerBound, mid, tipo); // sort low half
         recMergeSort(workSpace, mid+1, upperBound, tipo); // sort high half
         merge(workSpace, lowerBound, mid+1, upperBound, tipo); // merge them
      }  // end else
   }  // end recMergeSort()

   private void merge(Driver[] workSpace, int lowPtr, int highPtr, int upperBound, int tipo) {
      int j = 0;                             // workspace index
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       // # of items


      while(lowPtr <= mid && highPtr <= upperBound)
         {
            comp++;
            cambios++;
            if(condicion(tipo, theArray.BuscarPorPosicion(lowPtr).GetDato(columna), theArray.BuscarPorPosicion(highPtr).GetDato(columna)))
               workSpace[j++] = theArray.BuscarPorPosicion(lowPtr++);
            else
               workSpace[j++] = theArray.BuscarPorPosicion(highPtr++);
         }

      while(lowPtr <= mid)
      {
         workSpace[j++] = theArray.BuscarPorPosicion(lowPtr++);
         cambios++;
      }
         

      while(highPtr <= upperBound)
      {
         comp++;
         cambios++;
         workSpace[j++] = theArray.BuscarPorPosicion(highPtr++);
      }


      for(j=0; j<n; j++)
      {
         comp++;
         cambios++;
         theArray.CambiarPorPosicion(lowerBound + j, workSpace[j]);
      }
         
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
}