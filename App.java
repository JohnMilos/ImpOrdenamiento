import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    /**
    * Tipos de Ordenamiento
    *0-Alfabeticamente
    *1-Alfabeticamente Inverso
    *2-Del Menor al Mayor
    *3-Del Mayor al Menor
    *(Las opciones 2 y 3 solo pueden ser usadas para valores unicamente numericos)
    */

    public static void main(String args[])
    {
        try
        {
                String inputFile = args[0];
                File file = new File(inputFile);
                Scanner scanner = new Scanner(file);
                DoublyLinkedList<Driver> lista = LeerCSV.ObtenerLista(0, file);
                DoublyLinkedList<Driver> columnas = LeerCSV.ObtenerColumnas(file);
                
                System.out.println("Bienvenido, Favor de Escojer una Columna");
                Driver NomCol = columnas.BuscarPorPosicion(0);
                for(int x=0;x<NomCol.GetMax();x++)
                {       
                    String type = "(String)";
                     if( isNumeric(lista.BuscarPorPosicion(0).GetDato(x)))
                     {
                        type = "(Numerico)";
                     }
                    System.out.println((x+1) +"- "+NomCol.GetDato(x) + type);
                }
                int col, tipo;
                Scanner s = new Scanner(System.in);  
               do
               {
                    System.out.print("Columna: ");
                    col = s.nextInt();
                    System.out.println();
               }while( col<=0 || col > NomCol.GetMax());
                            
                col -= 1;

                System.out.println("Ahora escoje tu ordenamiento: ");
                System.out.println("Tipos de Ordenamiento\n"+
                "0-Alfabeticamente\n" +
                "1-Alfabeticamente Inverso\n"+
                "2-Del Menor al Mayor\n"+
                "3-Del Mayor al Menor\n" +
                "(Las opciones 2 y 3 solo pueden ser usadas para valores unicamente numericos)\n" +
                "(RadixSort solo Permite las opciones 2 y 3)");
                do
                {
                     System.out.print("Tipo: ");
                     tipo = s.nextInt();
                     System.out.println();
                }while( tipo<=0 || tipo > NomCol.GetMax());
                s.close();

                displayForward(lista, col);
       
                List<Integer> metricas = new ArrayList<>();
                List<Long> metTime = new ArrayList<>();

                BinaryInsertionSort bis = new BinaryInsertionSort();
                bis.binaryInsertionSort(lista,tipo,col);
                LeerCSV.Guardar("BinaryInsertionSort_ordenado.csv", file,lista);
                metTime.add(bis.GetTiempo());
                metricas.add(bis.GetComparaciones());
                metricas.add(bis.GetCambios());

                
                MergeSort arr = new MergeSort(lista); 
                arr.SetColumna(col);
                arr.mergeSort(tipo);
                LeerCSV.Guardar("MergeSort_ordenado.csv", file,lista);
                metTime.add(arr.GetTiempo());
                metricas.add(arr.GetComparaciones());
                metricas.add(arr.GetCambios());


                QuickSort qSort = new QuickSort(lista);
                qSort.SetColumna(col);
                qSort.StartSort(0,lista.GetSize()-1, tipo);
                LeerCSV.Guardar("QuickSort_ordenado.csv", file,lista);
                metTime.add(qSort.GetTiempo());
                metricas.add(qSort.GetComparaciones());
                metricas.add(qSort.GetCambios());

                RadixSort radix = new RadixSort(lista);
                radix.SetColumna(col);
                radix.sort(tipo);
                LeerCSV.Guardar("RadixSort_ordenado.csv", file,lista);
                metTime.add(radix.GetTiempo());
                metricas.add(radix.GetComparaciones());
                metricas.add(radix.GetCambios());


                LeerCSV.ExportMet("Metricas.txt", metTime, metricas);
                displayForward(lista, col);
                scanner.close();
            
        }catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
    
    }
/**
 * 
 * 
 * @param DoublyLinkedList<Driver> Recibe una lista.
 */
    public static void displayForward(DoublyLinkedList<Driver> lista, int columna) {
        System.out.print("List (first-->last): ");
        DoublyLink<Driver> current = lista.GetFirst();

        while(current != null) {
            System.out.print(current.dData.GetDato(columna)+", ");
            current = current.next; 
        }
        System.out.println("\n");
    }

    private static Boolean isNumeric(String x)
    {
        try
        {
            Double y = Double.parseDouble(x);
            y = y/1;
            return true;

        }catch(Exception e)
        {
            return false;
        }
    }
}
