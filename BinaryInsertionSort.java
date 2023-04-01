/** 
 * 
 * Esta clase es la del Binary Insertion Sort
 * 
*/

public class BinaryInsertionSort {
    private int cambios =0;
    private int comp =0;
    private long tiempoEjecucion = 0;

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
 
/**
 * Implementacion iterativa.
 * Hace la busqueda binaria.
*/
    public int binarySearch(DoublyLinkedList<Driver> list, Driver item, int low, int high, int tipo, int columna){
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comp++;
            if (item == list.BuscarPorPosicion(mid))
                return mid + 1;
            else 
            {
                comp++;
                if (condicion(tipo, item.GetDato(columna), list.BuscarPorPosicion(mid).GetDato(columna)))
                   low = mid + 1;
               else
                   high = mid - 1;
            }
        }
        return low;
    }

    private boolean condicion(int tipo, String item, String list)
    {
        switch(tipo)
        {
            case 0:
                return (item.compareTo(list) > 0);
            case 1:
                return (item.compareTo(list) < 0);
            case 2:
                return (Double.parseDouble(item) > Double.parseDouble(list));
            case 3:
                return(Double.parseDouble(item) < Double.parseDouble(list));
            default:
                return false;
        }

        
    }

    /**
    *
    *Recibe la lista de nodos a ordenar y Un entero del 0 al 3 que siginifica el tipo de ordenamiento que deseas
    *0-Alfabeticamente
    *1-Alfabeticamente Inverso
    *2-Del Menor al Mayor
    *3-Del Mayor al Menor
    *(Las opciones 2 y 3 solo pueden ser usadas para valores unicamente numericos)
    */
    public void binaryInsertionSort(DoublyLinkedList<Driver> list, int tipo, int columna) {
        int i, loc, j;
        Driver selected;
        
        if(tipo < 0 || tipo > 3)
        {
            System.out.println("Tipo de ordenamiento no valido- BIS");
            return;
        }
        if(tipo > 1)
        {
            try
            {
                Double.parseDouble(list.BuscarPorPosicion(0).GetDato(columna));
            }catch(Exception e)
            {
                System.out.println("Tipo de Dato no concordante con el tipo ordenamiento- BIS");
                return;
            }
        }

        long startTime = System.currentTimeMillis();
        for (i = 1; i < list.GetSize(); ++i) {
            j = i - 1;
            selected = list.BuscarPorPosicion(i);

            /**
             * 
             *encuentra la posicion donde debe ser insertado el elemento
            */ 
            loc = binarySearch(list, selected, 0, j, tipo, columna);
            /**
            *Hace un corrimiento a la derecha de los datos
            */
            while (j >= loc) {
                comp++;
                list.CambiarPorPosicion(j+1, list.BuscarPorPosicion(j));
                cambios++;
                j--;
            }
            list.CambiarPorPosicion(j+1, selected);
            cambios++;
        }
        
        long endTime = System.currentTimeMillis();
        tiempoEjecucion = endTime - startTime;
    }
}