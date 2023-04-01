import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

public class LeerCSV {

    //Recibe el numero de la columna a la cual deseamos sacarle los datos, empezando desde 0 hasta al numero maximo de columnas menos 1
    //Devuelve un Arreglo con Todos los Datos de esa columna 
    public static DoublyLinkedList<Driver> ObtenerLista(int columna, File archivoCSV) {

        String linea = "";
        String separador = ";";

        DoublyLinkedList<Driver> lista2 = new DoublyLinkedList<Driver>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) 
        {
            linea = br.readLine();
            String[] datos = linea.split(separador);

            while ((linea = br.readLine()) != null) {
                datos = linea.split(separador);
                Driver dd = new Driver(datos.length);
                for(int i = 0;i<datos.length;i++)
                {
                    dd.SetDato(i, datos[i]);
                }
                lista2.insertFirst(dd);
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        return lista2;
    }

    public static DoublyLinkedList<Driver> ObtenerColumnas(File archivoCSV) {

        String linea = "";
        String separador = ";";

        DoublyLinkedList<Driver> lista2 = new DoublyLinkedList<Driver>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) 
        {
            linea = br.readLine();
            String[] datos = linea.split(separador);
            Driver dd = new Driver(datos.length);
            for(int i = 0;i<datos.length;i++)
            {
                dd.SetDato(i, datos[i]);
            }
            lista2.insertFirst(dd);

        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        return lista2;
    }
    
    public static void Guardar(String archivo, File anterior, DoublyLinkedList<Driver> list) throws IOException
    {
        try
        {
            FileWriter csvWriter = new FileWriter(archivo);
            BufferedReader br = new BufferedReader(new FileReader(anterior));
            String linea = br.readLine();
            csvWriter.append(linea);
            DoublyLink<Driver> current = list.GetFirst();
            while(current != null) {
                linea = "\n" + current.dData.GetDato(0);
                for(int x=1;x<current.dData.GetMax();x++)
                {
                    linea = linea + ";"+ current.dData.GetDato(x);
                }
                csvWriter.append(linea);
                current = current.next; 
            }
            csvWriter.close();
            br.close();

        }catch(Exception e)
        {
            System.out.println("Error al guardar: " + e);
        }

    }

    public static void ExportMet(String archivo, List<Long> time, List<Integer> met) throws IOException
    {
        try
        {
            FileWriter csvWriter = new FileWriter(archivo);
            String linea;
            
            int x = 0;
            linea = "Binary Insertion Sort\n"
        +"Tiempo de Ejecucion: " + time.get(x)+"ms\n" 
        + "Numero de comparaciones: "+ met.get(x+x)+"\n"
        +"Numero de Intercambios: "+met.get(x+x+1)+"\n";
        csvWriter.append(linea);

        x+=1;
        linea = "\nMergeSort\n"
        +"Tiempo de Ejecucion: " + time.get(x)+"ms\n" 
        + "Numero de comparaciones: "+ met.get(x+x)+"\n"
        +"Numero de Intercambios: "+met.get(x+x+1)+"\n";
        csvWriter.append(linea);

        x+=1;
        linea = "\nQuickSort\n"
        +"Tiempo de Ejecucion: " + time.get(x)+"ms\n" 
        + "Numero de comparaciones: "+ met.get(x+x)+"\n"
        +"Numero de Intercambios: "+met.get(x+x+1)+"\n";
        csvWriter.append(linea);

        x+=1;
        linea = "\nRadixSort\n"
        +"Tiempo de Ejecucion: " + time.get(x)+"ms\n" 
        + "Numero de comparaciones: "+ met.get(x+x)+"\n"
        +"Numero de Intercambios: "+met.get(x+x+1)+"\n";
        csvWriter.append(linea);
        csvWriter.close();

        }catch(Exception e)
        {
            System.out.println("Error al guardar: " + e);
        }

    }

}