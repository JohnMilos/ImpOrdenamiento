public class Driver {
    private String[] datos;
    private int maxdata;

    public Driver(int max)
    {
        maxdata = max;
        datos = new String[max];
    }

    public void SetDato(int i, String dato)
    {
        datos[i] = dato;
    }

    public String GetDato(int i)
    {
        return datos[i];
    }

    public int GetMax()
    {
        return maxdata;
    }
}
